package com.springboot.poi;

import com.springboot.poi.dto.ResultCell;
import com.springboot.poi.dto.ResultRow;
import com.springboot.poi.filter.TeacherFilterChain;
import com.springboot.poi.helper.ClassGeneration;
import com.springboot.poi.utils.ExcelsUtil;
import com.springboot.pojo.Teacher;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {

        String path = "123.txt";
        int i = path.lastIndexOf(".");
        System.out.println(i);

        System.out.println(path.substring(i+1));


        System.out.println("");
    }
}
