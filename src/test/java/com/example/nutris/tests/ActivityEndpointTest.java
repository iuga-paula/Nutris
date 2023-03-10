package com.example.nutris.tests;

import com.example.nutris.food.controller.FoodController;
import com.example.nutris.physicalActivity.PhysicalActivityController;
import com.example.nutris.physicalActivity.PhysicalActivityDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

import static com.example.nutris.tests.TestUtil.asJsonString;
import static com.example.nutris.tests.TestUtil.toQueryParams;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class ActivityEndpointTest {

    private final String endpoint = "/api/v1/activity";
    @Autowired
    private PhysicalActivityController activityController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
    }

    @Test
    public void addActivityWrongDateFormatReturnsFail() throws Exception {
        PhysicalActivityDTO activityDTO = new PhysicalActivityDTO("name", "2021.02.20", 30, 24f);
        System.out.println(asJsonString(activityDTO));
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint).content(asJsonString(activityDTO)).contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest());
    }


    @Test
    public void getActivityNoIdFoundReturnsFail() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("id", "1"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint).params(paramsMap).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteActivityNoIdFoundReturnsFail() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("id", "1"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint).params(paramsMap).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void getCaloriesReturnsOk() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("startDate", "2022-01-09"), Pair.of("endDate", "2022-01-12"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint).params(paramsMap).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
