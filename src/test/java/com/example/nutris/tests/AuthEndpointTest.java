package com.example.nutris.tests;

import com.example.nutris.errorMessage.ResponseMessage;
import com.example.nutris.user.AuthController;
import com.example.nutris.user.AuthService;
import com.example.nutris.user.UserRepository;
import com.example.nutris.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

import static com.example.nutris.tests.TestUtil.toQueryParams;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthEndpointTest {

    private final String endpoint = "/api/v1/auth";
    @Autowired
    private AuthController authController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }


    @Test
    public void givenValidDataRegisterReturnOk() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("firstName", "Test"), Pair.of("lastName", "Test"), Pair.of("email", "test@mail.com"), Pair.of("password", "03902930013"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint + "/register").params(paramsMap).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void givenExistingEmailReturnFail() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("firstName", "Test"), Pair.of("lastName", "Test"), Pair.of("email", "test@mail.com"), Pair.of("password", "03902930013"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint + "/register").params(paramsMap).contentType(MediaType.APPLICATION_JSON));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(endpoint + "/register").params(paramsMap).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
        Assertions.assertTrue(result.getResponse().getContentAsString().contains("Email already used!"));
    }

    @Test
    public void giveWrongLoginReturnFail() throws Exception {
        List<Pair<String, String>> params = List.of(Pair.of("email", "test32@mail.com"), Pair.of("password", "03902930013"));
        LinkedMultiValueMap<String, String> paramsMap = toQueryParams(params);
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint + "/login").params(paramsMap).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
    }

}
