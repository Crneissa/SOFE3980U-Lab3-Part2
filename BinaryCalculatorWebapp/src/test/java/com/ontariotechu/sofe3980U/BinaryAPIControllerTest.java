package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }



// Test cases for Binary API Service

    // Test adding small numbers with plain text API
    @Test
    public void addSmallNumbers() throws Exception {
        this.mvc.perform(get("/add").param("operand1","1").param("operand2","1")) // perform a GET request to the /add endpoint with operand1=1 and operand2=1
            .andExpect(status().isOk()) // expect the HTTP status to be 200 OK
            .andExpect(content().string("10")); // 1 + 1 = 10 in binary
    }

    // Test adding larger numbers with JSON API
    @Test
    public void addLargeNumbersJson() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","1010").param("operand2","1101")) // perform a GET request to the /add_json endpoint with operand1=1010 and operand2=1101
            .andExpect(status().isOk()) // expect the HTTP status to be 200 OK
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1010)) // expect the JSON response to have operand1 equal to 1010
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1101)) // expect the JSON response to have operand2 equal to 1101
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10111)) // expect the JSON response to have result equal to 10111 binary
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add")); // expect the JSON response to have operator equal to "add"
    }

    // Test adding zero using plain text API
    @Test
    public void addWithZero() throws Exception {
        this.mvc.perform(get("/add").param("operand1","101").param("operand2","0")) // perform a GET request to the /add endpoint with operand1=101 and operand2=0
            .andExpect(status().isOk()) // expect the HTTP status to be 200 OK
            .andExpect(content().string("101")); // 5 + 0 = 5 in binary
    }
















// Test Cases for Multiply, AND, OR operations


    // Test multiplying two binary numbers, 101 * 10 = 1010 in binary
    @Test
    public void multiplyNormal() throws Exception { // tests multiplying two binary numbers, 101 * 10 = 1010 in binary
        this.mvc.perform(get("/multiply")
            .param("operand1","101")
            .param("operand2","10"))
            .andExpect(status().isOk())
            .andExpect(content().string("1010"));
    }

    // Test multiplying a binary number by zero, 101 * 0 = 0 in binary
    @Test
    public void multiplyByZero() throws Exception { // tests multiplying a binary number by zero, 101 * 0 = 0 in binary
        this.mvc.perform(get("/multiply")
            .param("operand1","101")
            .param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // Test the AND operation on two binary numbers, 1100 & 1010 = 1000 in binary
    @Test
    public void andNormal() throws Exception { // tests the AND operation on two binary numbers, 1100 & 1010 = 1000 in binary
        this.mvc.perform(get("/and")
            .param("operand1","1100")
            .param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1000"));
    }

    // Test the AND operation on two binary numbers of different lengths, 1 & 1010 = 0 in binary
    @Test
    public void andDifferentLength() throws Exception { // tests the AND operation on two binary numbers of different lengths, 1 & 1010 = 0 in binary
        this.mvc.perform(get("/and")
            .param("operand1","1")
            .param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // Test the OR operation on two binary numbers, 1100 | 1010 = 1110 in binary
    @Test
    public void orNormal() throws Exception { // tests the OR operation on two binary numbers, 1100 | 1010 = 1110 in binary
        this.mvc.perform(get("/or")
            .param("operand1","1100")
            .param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1110"));
    }

    // Test the OR operation on a binary number and zero, 1010 | 0 = 1010 in binary
    @Test
    public void orWithZero() throws Exception { // tests the OR operation on a binary number and zero, 1010 | 0 = 1010 in binary
        this.mvc.perform(get("/or")
            .param("operand1","1010")
            .param("operand2","0"))
            .andExpect(status().isOk())
            .andExpect(content().string("1010"));
    }

    // Test multiplying two binary numbers where one input is invalid, abc * 101 = 0 in binary (invalid input is treated as zero)
    @Test
    public void multiplyInvalidInput() throws Exception { // tests multiplying two binary numbers where one input is invalid, abc * 101 = 0 in binary (invalid input is treated as zero)
        this.mvc.perform(get("/multiply")
            .param("operand1","abc")
            .param("operand2","101"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }


}