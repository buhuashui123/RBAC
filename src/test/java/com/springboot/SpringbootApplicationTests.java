package com.springboot;

import com.springboot.pojo.User;
import com.springboot.utils.FTPUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1","zs","zs","123"));
        userList.add(new User("2","ls","zs","123"));
        userList.add(new User("1","ww","zs","123"));
        userList.add(new User("2","ml","zs","123"));

        userList.stream().forEach(System.out::println);


        HashMap<String, String> map = new HashMap<String, String>();
        

    }

}
