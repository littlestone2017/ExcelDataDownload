package com.alexli.photoexport;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.Data;

import javax.naming.Name;

@Data
public class ExcelDTO implements IExcelModel {


//  define any column that you want...
    @Excel(name = "phoneNumber", width = 18, orderNum = "7")
    private String phoneNumber;

    @Excel(name = "photos", width = 200, orderNum ="12" )
    private String url;

    private String remark;

    private String errorMsg;
}
