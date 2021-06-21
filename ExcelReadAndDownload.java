package com.alexli.photoexport;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ExcelReadAndDownload {

    public static void main(String[] args) {
        //Use poi tools to read data from excel
        ImportParams params = new ImportParams();
        params.setImportFields (new String[]{"name"});
        params.setNeedVerfiy(true);
            try {
                //get file location
                File file = new File("D:\\folderName\\fileName.xlsx");
                BufferedInputStream fileIPS = new BufferedInputStream(new FileInputStream(file));
                ExcelImportResult<ExcelDTO> result = ExcelImportUtil.importExcelMore(fileIPS, ExcelDTO.class, params);
                List<ExcelDTO> list = result.getList();
                System.out.println("dataSize"+ list.size());
                System.out.println("excel read complete");
                int i = 2;
                for (ExcelDTO excelDTO : list) {
                    String urlWithTrash = excelDTO.getUrl();
                    if ("".equals(urlWithTrash)|| urlWithTrash == null){
                        System.out.println(i++ + "--------------------------------------> this is null");
                        continue;
                    }
                    String[] split = urlWithTrash.split(",");
                    String url = split[0];

//                    String name = excelDTO.getName();
                    String phoneNumber = excelDTO.getPhoneNumber();
//                    String staffId = excelDTO.getStaffId();
                    String picName = phoneNumber + ".jpg";
                    System.out.println("start to download"+picName +"number of rows"+ i++);
                    if (url.contains(".png")){
                        System.out.println(picName + "---------------------------------------------This is png file");
                    }
                    //output file location
                    downImages("D:\\folderName\\folderName", url, picName);
                    System.out.println("Download successfully");
                }
                System.out.println("======================COMPELTE DOWNLOADING===========================");
                fileIPS.close();
            }catch (Exception e){
                e.printStackTrace();
            }


    }
    private static void downImages(String filePath, String imgUrl, String fileName) {

        File file = new File(filePath +"\\" + fileName);//file output path
        InputStream inStream;
        try {
            URL url = new URL(imgUrl);//  construct a URL
            URLConnection con = url.openConnection();// open url
            inStream = con.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();//put photo data into output stream
            byte[] buf = new byte[1024];
            int len;
            while ((len = inStream.read(buf)) != -1) {
                outStream.write(buf, 0, len);
            }
            inStream.close();
            outStream.close();
            FileOutputStream op = new FileOutputStream(file);// photo's location
            op.write(outStream.toByteArray());
            op.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
