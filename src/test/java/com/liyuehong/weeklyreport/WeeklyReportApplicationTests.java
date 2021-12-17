package com.liyuehong.weeklyreport;

import com.sun.istack.internal.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;

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
    //default V computeIfAbsent(K key,
    //                          Function<? super K, ? extends V> mappingFunction) {
    //    Objects.requireNonNull(mappingFunction);
    //    V v;
    //    if ((v = get(key)) == null) {
    //        V newValue;
    //        if ((newValue = mappingFunction.apply(key)) != null) {
    //            put(key, newValue);
    //            return newValue;
    //        }
    //    }
    //
    //    return v;
    //}
}
