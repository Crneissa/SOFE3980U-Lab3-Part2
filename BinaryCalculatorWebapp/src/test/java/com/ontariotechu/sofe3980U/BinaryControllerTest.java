package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }



// Test cases for Binary Web Application------------------------------------------

    // tests adding two small numbers, 1 + 1 = 10 in binary
    @Test
    public void postAdditionSmallNumbers() throws Exception {
        this.mvc.perform(post("/").param("operand1","1").param("operator","+").param("operand2","1")) // performs a POST request with the specified parameters
            .andExpect(status().isOk()) // the server responded successfully
            .andExpect(view().name("result")) // the page we expected was returned
            .andExpect(model().attribute("result", "10")) // 1 + 1 = 2 in binary, the math looks right
            .andExpect(model().attribute("operand1", "1")); // the input value is preserved
    }

    // tests adding two larger numbers, 1010 + 1101 = 10111 in binary
    @Test
    public void postAdditionLargeNumbers() throws Exception {
        this.mvc.perform(post("/").param("operand1","1010").param("operator","+").param("operand2","1101")) // performs a POST request with the specified parameters
            .andExpect(status().isOk()) // the server responded successfully
            .andExpect(view().name("result")) // the correct view was rendered
            .andExpect(model().attribute("result", "10111")) // 10 + 13 = 23 in binary, the result matches
            .andExpect(model().attribute("operand1", "1010")); // the input value is preserved
    }

    // tests adding a number to zero, 101 + 0 = 101 in binary
    @Test
    public void postAdditionWithZero() throws Exception {
        this.mvc.perform(post("/").param("operand1","101").param("operator","+").param("operand2","0")) // performs a POST request with the specified parameters
            .andExpect(status().isOk()) // the server responded successfully
            .andExpect(view().name("result")) // the result page was returned
            .andExpect(model().attribute("result", "101")) // 5 + 0 = 5, adding zero works as expected
            .andExpect(model().attribute("operand1", "101")); // the input value is preserved
    }

// ---------------------------------------------------------------------------------------------------------------------------
















// Test Cases for Multiply, AND, OR operations--------------------------------------------

// tests multiplying two binary numbers, 101 * 10 = 1010 in binary
@Test
public void postMultiplyNormal() throws Exception { // tests multiplying two binary numbers, 101 * 10 = 1010 in binary
    this.mvc.perform(post("/")
        .param("operand1","101")
        .param("operator","*")
        .param("operand2","10"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result","1010"));
}

// tests multiplying a binary number by zero, 101 * 0 = 0 in binary
@Test
public void postMultiplyByZero() throws Exception { // tests multiplying a binary number by zero, 101 * 0 = 0 in binary
    this.mvc.perform(post("/")
        .param("operand1","101")
        .param("operator","*")
        .param("operand2","0"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result","0"));
}

// tests the AND operation on two binary numbers, 1100 & 1010 = 1000 in binary
@Test
public void postAndNormal() throws Exception { // tests bitwise AND of two binary numbers, 1100 & 1010 = 1000 in binary
    this.mvc.perform(post("/")
        .param("operand1","1100")
        .param("operator","&")
        .param("operand2","1010"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result","1000"));
}

// tests the AND operation on two binary numbers of different lengths, 1 & 1010 = 0 in binary
@Test
public void postAndDifferentLength() throws Exception { // tests bitwise AND of two binary numbers of different lengths, 1 & 1010 = 0 in binary
    this.mvc.perform(post("/")
        .param("operand1","1")
        .param("operator","&")
        .param("operand2","1010"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result","0"));
}

// tests the OR operation on two binary numbers, 1100 | 1010 = 1110 in binary
@Test
public void postOrNormal() throws Exception { // tests bitwise OR of two binary numbers, 1100 | 1010 = 1110 in binary
    this.mvc.perform(post("/")
        .param("operand1","1100")
        .param("operator","|")
        .param("operand2","1010"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result","1110"));
}

// tests the OR operation on a binary number and zero, 1010 | 0 = 1010 in binary
@Test
public void postOrWithZero() throws Exception { // tests bitwise OR of a binary number with zero, 1010 | 0 = 1010 in binary
    this.mvc.perform(post("/")
        .param("operand1","1010")
        .param("operator","|")
        .param("operand2","0"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result","1010"));
}
// ---------------------------------------------------------------------------------------------------------------------------





}