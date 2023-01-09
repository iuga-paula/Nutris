package com.example.nutris.tests;

import com.example.nutris.user.AuthController;
import com.example.nutris.user.AuthService;
import com.example.nutris.user.UserRepository;
import com.example.nutris.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthEndpointTest {


    private final String endpoint = "/api/v1/auth";

    @InjectMocks

    private AuthController authController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void givenValidUserDataRegiter() {
        ResponseEntity<?> result =  authController.registerUser("Anatonia", "Ileana", "ile@mail.com", "a0dadada");
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }


    @Test
    @Disabled
    public void givenValidDataRegisterReturnOk() throws Exception {

        String params = getPostData();
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint + "/register").content(params).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    @Disabled
    public void givenExistingEmailReturnFail() throws Exception {
        String params = getPostData();
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint + "/register").content(params).
                contentType(MediaType.APPLICATION_JSON));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(endpoint + "/register").content(params).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

        Assertions.assertTrue(result.getResponse().getContentAsString().contains("Email already used!"));
    }

    @Test
    public void givenUserLogin() throws Exception {
        String params = getLoginData();
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint + "/login").content(params).
                contentType(MediaType.APPLICATION_JSON));
    }

    public String getPostData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", "Test");
        jsonObject.put("lastName", "Test");
        jsonObject.put("email", "test@mail.com");
        jsonObject.put("password", "03902930013");
        return jsonObject.toString();
    }

    public String getLoginData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "test@mail.com");
        jsonObject.put("password", "03902930013");
        return jsonObject.toString();
    }
}
