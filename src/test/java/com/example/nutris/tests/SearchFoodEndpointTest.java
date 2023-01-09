package com.example.nutris.tests;

import com.example.nutris.food.controller.SearchFoodController;
import com.example.nutris.food.service.SearchFoodService;
import com.example.nutris.user.AuthController;
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

import static com.example.nutris.tests.TestUtil.toQueryParams;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SearchFoodEndpointTest {
    private final String endpoint = "/api/v1/search_food";
    @Autowired
    private SearchFoodController searchFoodController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(searchFoodController).build();
    }

    @Test
    public void givenNoParametersReturnFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void givenParametersReturnsOK() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("calories", "0.5"), Pair.of("water", "80"), Pair.of("goodFor", "diabetes"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint).params(paramsMap).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void givenWrongParameterReturnsFail() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("color", "green"), Pair.of("water", "80"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint).params(paramsMap).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }


}
