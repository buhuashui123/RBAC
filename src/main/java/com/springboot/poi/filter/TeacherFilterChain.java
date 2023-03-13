package com.springboot.poi.filter;

import com.springboot.poi.check.CheckAge;
import com.springboot.poi.check.CheckPhone;
import com.springboot.pojo.Teacher;

public class TeacherFilterChain extends AbstractFilterChain{

    public TeacherFilterChain() {
        into();
    }

    private void into() {
        this.filterList.add(new CheckAge());
        this.filterList.add(new CheckPhone());
    }
}
