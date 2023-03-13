package com.springboot.poi.dto;

import org.apache.commons.lang.StringUtils;

/**
 * 导入EXCEL数据时，行下的单元格数据的校验结果
 * 单元格实体类
 */
public class ResultCell extends ResultValidate{

    //单元格的错误信息
    private String error;

    public ResultCell() {
    }

    public ResultCell(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultCell cell = (ResultCell) o;

        return error != null ? error.equals(cell.error) : cell.error == null;
    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResultCell{" +
                "error='" + error + '\'' +
                '}';
    }

    /**
     * 单元格数据是否合法
     * @return
     */
    @Override
    public boolean isValid() {
        //判断error是否为空，空则true
        return StringUtils.isEmpty(error);

        //如果单元格的错误信息error等于null  error.length()等于0 则没有错误信息
        //return error==null||error.length()==0;
    }
}
