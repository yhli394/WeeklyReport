package com.liyuehong.weeklyreport;

import com.liyuehong.weeklyreport.configuration.WebMvcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.sql.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.lang.System.currentTimeMillis;

@SpringBootTest
class WeeklyReportApplicationTests {

    @Resource
    private WebMvcConfig webMvcConfig;

    @Test
    public void test(){
        System.out.println(Arrays.toString(webMvcConfig.getCrossLists()));
    }



    @Test void contextLoads() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        //if(!map.containsKey(1)){
        //    map.put(1,new ArrayList<>());
        //}
        //map.get(1).add(2);
        map.values();
        HashSet<Object> objects = new HashSet<>();
        map.computeIfAbsent(1,t -> new ArrayList<>());
//        IntStream.of().map()
    }
    @Test
    void password(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("123456");
        System.out.println(result);
    }
    @Test
    void decode(){
        String a ="+";
        String s ="    -420";
        String v=s.trim();
        System.out.println(Integer.parseInt("-42"));
    }
    @Test
    void password1(){
        ArrayList<Object> objects = new ArrayList<>();
        LinkedList<Object> objects1 = new LinkedList<>();
        HashMap<Object, Object> map = new HashMap<>();
    }

}













