package com.example.nutris.tests;

import com.example.nutris.diet.DietController;
import com.example.nutris.diet.DietDTO;
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
public class DietEndpointTest {
    private final String endpoint = "/api/v1/diet";
    @Autowired
        private DietController dietController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dietController).build();
    }
    @Test
    public void addDietReturnsOK() throws Exception {
        DietDTO dietDTO = new DietDTO(1L, "2020-12-12");
        System.out.println(asJsonString(dietDTO));
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint).content(asJsonString(dietDTO)).contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isBadRequest());
    }
    @Test
    public void getCaloriesReturnsOk() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("startDate", "2022-01-09"), Pair.of("endDate", "2022-01-12"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.get(endpoint).params(paramsMap).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
