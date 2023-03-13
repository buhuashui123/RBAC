package com.springboot.poi.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * 导入EXCEL数据时，行下的单元格数据的校验结果
 **/
public class ResultRow extends ResultValidate{

    //行标记
    private String index;
    //该行所有单元格的校验结果
    private List<ResultCell> resultCells;

    public ResultRow() {
    }

    public ResultRow(String index, List<ResultCell> resultCells) {
        this.index = index;
        this.resultCells = resultCells;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<ResultCell> getResultCells() {
        return resultCells;
    }

    /**
     * 保存该行下的
     * @param cell
     */
    public void setResultCells(ResultCell cell) {
        if (resultCells==null||resultCells.isEmpty()){
            resultCells= new ArrayList<ResultCell>();
        }
        if (cell!=null&&!cell.isValid()){
            resultCells.add(cell);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultRow resultRow = (ResultRow) o;

        if (index != null ? !index.equals(resultRow.index) : resultRow.index != null) return false;
        return resultCells != null ? resultCells.equals(resultRow.resultCells) : resultRow.resultCells == null;
    }

    @Override
    public int hashCode() {
        int result = index != null ? index.hashCode() : 0;
        result = 31 * result + (resultCells != null ? resultCells.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResultRow{" +
                "index='" + index + '\'' +
                ", resultCells=" + resultCells +
                '}';
    }

    @Override
    public boolean isValid() {
        return CollectionUtils.isEmpty(resultCells);
    }
}
