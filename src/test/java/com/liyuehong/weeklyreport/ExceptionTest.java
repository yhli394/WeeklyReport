package com.liyuehong.weeklyreport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@AutoConfigureMockMvc
@SpringBootTest
public class ExceptionTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void test() throws Exception {
        mockMvc.perform(post("/login?password=123456&username=admin"))
                .andReturn();
    }

    @Test
    void should_return_400_if_param_not_valid() throws Exception {
        mockMvc.perform(get("/api/illegalArgumentException"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.message").value("参数错误!"));
    }

    @Test
    void should_return_404_if_resourse_not_found() throws Exception {
        mockMvc.perform(get("/api/resourceNotFoundException"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("$.message").value("Sorry, the resourse not found!"));
    }
}
