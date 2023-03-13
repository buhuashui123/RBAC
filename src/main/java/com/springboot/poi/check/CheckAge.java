package com.springboot.poi.check;

import com.springboot.poi.dto.ResultCell;
import com.springboot.poi.dto.ResultRow;
import com.springboot.poi.filter.AbstractValidator;
import com.springboot.pojo.Teacher;

/**
 * 年龄过滤
 */
public class CheckAge extends AbstractValidator<Teacher, ResultRow> {

    @Override
    public ResultRow validate(Teacher t) {
        ResultRow row = new ResultRow();
        ResultCell cell = new ResultCell();
        //判断是否成年
        if (t.getAge()<18){
            row.setIndex(t.getId()+"");
            cell.setError("年龄不合法");
            row.setResultCells(cell);
            return row;
        }
        return null;
    }
}
