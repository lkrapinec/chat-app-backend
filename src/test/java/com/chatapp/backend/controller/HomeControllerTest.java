package com.chatapp.backend.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.chatapp.backend.config.SecurityConfig;
import com.chatapp.backend.service.impl.TokenServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest({HomeController.class, AuthController.class})
@Import({SecurityConfig.class, TokenServiceImpl.class})
class HomeControllerTest {
  @Autowired
  MockMvc mvc;

  @Test
  void rootWhenUnauthenticatedThen401() throws Exception {
    mvc.perform(get("/home"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void rootWhenAuthenticatedThen200() throws Exception {
    MvcResult result = mvc.perform(post("/token").with(httpBasic("user", "password")))
        .andExpect(status().isOk()).andReturn();

    String token = result.getResponse().getContentAsString();

    mvc.perform(get("/home").header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void rootWithMockUserStatusIsOK() throws Exception {
    mvc.perform(get("/home"))
        .andExpect(status().isOk());
  }
}