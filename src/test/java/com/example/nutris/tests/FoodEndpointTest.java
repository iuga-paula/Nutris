package com.example.nutris.tests;

import com.example.nutris.food.FoodDTO;
import com.example.nutris.food.controller.FoodController;
import com.example.nutris.food.controller.SearchFoodController;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

import static com.example.nutris.tests.TestUtil.asJsonString;
import static com.example.nutris.tests.TestUtil.toQueryParams;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class FoodEndpointTest {
    private final String endpoint = "/api/v1/food";
    @Autowired
    private FoodController foodController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(foodController).build();
    }

    @Test
    public void getFoodNoParametersReturnFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void getFoodReturnOk() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("name", "apple"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void PostFoodReturnsOk() throws Exception {
        FoodDTO food = new FoodDTO("test", 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,0f,0f,0f,0f,0f,0f,"test", "test");
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint).content(asJsonString(food)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void PatchFoodReturnsOk() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("id", "1"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        FoodDTO food = new FoodDTO("test", 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,0f,0f,0f,0f,0f,0f,"test", "test");
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint).params(paramsMap).content(asJsonString(food)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


}
