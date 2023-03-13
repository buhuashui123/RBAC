package com.springboot.poi.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelsUtil implements Serializable {


    private static final long serialVersionUID = -5166506529843674084L;

    /**
     * 获取输出流
     * @param path 文件路径
     * @return 返回输出流
     */
    public static InputStream getInputStream(String path){
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return in;
    }

    /**
     * 获取工作簿
     * @param in 文件输入流
     * @return 返回一个工作簿
     */
    public static Workbook getWorkbook(InputStream in){
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 获取单元格内容
     * @param cell  单元格
     * @return
     */
    public static Object getCellValues(Cell cell){
        if (cell == null){
            return  "";
        }

        Object cellValue = null;
        switch (cell.getCellType()){
            case NUMERIC :
                if (DateUtil.isCellDateFormatted(cell)){
                    Date dateCellValue = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    cellValue = sdf.format(dateCellValue);
                }else{
                    cellValue = (int)cell.getNumericCellValue();
                }
                break;
            case BLANK:
                cellValue = "null";
                break;
            case ERROR:
                cellValue = Byte.toString(cell.getErrorCellValue());
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellValue = Boolean.toString(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "null";
        }
        return cellValue;
    }

    /**
     * 判断读取的内容是否为空
     * @param objects 整行excel数据
     * @return 读取excel 一整行是否数据
     */
    public static boolean isAllElemEmpty(Object[] objects){
        //传过来的数组是空无需再做判断
        if(ArrayUtils.isEmpty(objects)){
            return false;
        }
        //设置开关
        boolean flag = true;
        for (Object object : objects) {
            if (StringUtils.isNotEmpty(ObjectUtils.toString(object))){
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 获取excel表里的数据
     * @param in 文件流
     * @return 返回一个Map<String, List<Object[]>> map<某一页，某一页的数据></>
     */
    public static Map<String, List<Object[]>> getMap(InputStream in){
        //获取工作簿
        Workbook workbook = getWorkbook(in);
        //获取工作簿有多少张表
        int sheets = workbook.getNumberOfSheets();
        //声明一个Map集合 以键值对的形式存储excel表格数据
        Map<String, List<Object[]>> map = new HashMap<>();
        //遍历表
        for (int i = 0;i<sheets;i++){
            //获取表
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet==null){
                continue;
            }
            List<Object[]> list = new ArrayList<>();
            //获取当前表的第一行的下标，下标从0开始
            int firstRowNum = sheet.getFirstRowNum();
            //获取当前表的最后一行的下标，下标从0开始
            int lastRowNum = sheet.getLastRowNum();
            //遍历行
            for (int j=firstRowNum;j<=lastRowNum;j++){
                //获取行
                Row row = sheet.getRow(j);
                if (row==null){
                    continue;
                }
                //获取第一个有内容的单元格,下标从0开始
                int firstCellNum = row.getFirstCellNum();
                //获取最后一个有内容的单元格,下标从1开始
                int lastCellNum = row.getLastCellNum();
                //获取数组的长度
                int len = lastCellNum - firstCellNum;
                //声明一个数组用来存储单元格里面的数据
                Object[] obj = new Object[len];
                //声明一个变量为数组的下标
                int index = 0;
                //遍历单元格
                for (int k = firstCellNum;k<lastCellNum;k++){
                    //获取单元格
                    Cell cell = row.getCell(k);
                    //调用工具类的转换方法，获取单元格的数据
                    Object cellValues = getCellValues(cell);
                    //如果没有数据，默认给null
                    if (cellValues == "") {
                        cellValues = null;
                    }
                    //把单元格的数据存入数组中
                    obj[index] = cellValues;
                    index++;
                }
                //把数组存入集合中
                if (!isAllElemEmpty(obj)){
                    list.add(obj);
                }
            }
            //把集合存入map集合中，key写sheet的页名
            map.put(sheet.getSheetName().trim(),list);
        }
        return map;
    }
}
