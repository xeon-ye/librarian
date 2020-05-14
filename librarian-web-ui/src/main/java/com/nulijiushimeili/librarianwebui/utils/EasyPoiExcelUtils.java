package com.nulijiushimeili.librarianwebui.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EasyPoiExcelUtils {
	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = null;
        if (StringUtils.contains(fileName, ".xlsx")) {
            exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        } else {
            exportParams = new ExportParams(title, sheetName, ExcelType.HSSF);
        }
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,String fileName, HttpServletResponse response){
        //根据fileNme后缀导出相应类型的excel文件
	    if (StringUtils.contains(fileName, ".xlsx")) {
            ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
           // exportParams.setStyle(ExcelExportStylerColorImpl.class);//设置样式，可自定义
            defaultExport(list, pojoClass, fileName, response, exportParams);
        } else {
            defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName, ExcelType.HSSF));
        }

    }
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,pojoClass,list);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * excel导出模板版
     * @param map
     * @param params
     * @param fileName
     * @param response
     */
    public static void exportTemplateExcel(Map<String, Object> map, TemplateExportParams params, String fileName, HttpServletResponse response){
        Workbook workbook = ExcelExportUtil.exportExcel(params,map);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            workbook.write(response.getOutputStream());
        } catch (IOException e) {
//            throw new BusinessException(e.getMessage(), false);
        }
    }
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }

    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
//            throw new BusinessException("模板不能为空",false);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new BusinessException(e.getMessage(), false);
        }
        return list;
    }
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
//            throw new BusinessException("excel文件不能为空",false);
        } catch (Exception e) {
//            throw new BusinessException(e.getMessage(),false);
        }
        return list;
    }

}
