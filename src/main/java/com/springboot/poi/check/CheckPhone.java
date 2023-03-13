package com.springboot.poi.check;

import com.springboot.poi.dto.ResultCell;
import com.springboot.poi.dto.ResultRow;
import com.springboot.poi.filter.AbstractValidator;
import com.springboot.pojo.Teacher;

/**
 * 手机号过滤
 */
public class CheckPhone extends AbstractValidator<Teacher, ResultRow> {

    @Override
    public ResultRow validate(Teacher t) {
        ResultRow row = new ResultRow();
        ResultCell cell = new ResultCell();
        if (t.getPhone().length()>5) {
            //手机号长度超过5
            row.setIndex(t.getId()+"");
            cell.setError("手机号格式有误");
            row.setResultCells(cell);
            return row;
        }
        return null;
    }
}
