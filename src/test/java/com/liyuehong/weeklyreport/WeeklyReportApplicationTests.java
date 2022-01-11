package com.liyuehong.weeklyreport;

import com.sun.istack.internal.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

import static java.lang.System.currentTimeMillis;

@SpringBootTest
class WeeklyReportApplicationTests {

    @Test void contextLoads() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        //if(!map.containsKey(1)){
        //    map.put(1,new ArrayList<>());
        //}
        //map.get(1).add(2);
        map.computeIfAbsent(1,t -> new ArrayList<>());
    }
    @Test
    void password(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("123456");
        System.out.println(result);
    }

}
