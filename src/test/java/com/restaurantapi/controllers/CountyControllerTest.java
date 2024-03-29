package com.restaurantapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapi.models.entity.City;
import com.restaurantapi.models.entity.County;
import com.restaurantapi.repositories.CountyRepository;
import com.restaurantapi.services.CountyService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = CountyController.class)
public class CountyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CountyService countyService;

    @MockBean
    CountyRepository countyRepository;

    String mapToJson(Object o) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }

    @Test
    public void add() throws Exception {

        County county = this.generateCounty();

        String URI = "/county";
        String inputJson = this.mapToJson(county);

        Mockito.when(countyService.update(Mockito.any(County.class))).thenReturn(county);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat("").isEqualTo(outputJson);
    }

    @Test
    public void getAll() throws Exception {

        List<County> countyList = new ArrayList<>();
        countyList.add(this.generateCounty());

        String URI = "/county";
        String inputJson = this.mapToJson(countyList);

        Mockito.when(countyService.getAll()).thenReturn(countyList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat("").isEqualTo(outputJson);
    }

    @Test
    public void getById() throws Exception {

        County county = this.generateCounty();

        String URI = "/county/1";
        String inputJson = this.mapToJson(county);

        Mockito.when(countyService.getById(Mockito.anyLong())).thenReturn(county);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat("").isEqualTo(outputJson);
    }

    @Test
    public void update() throws Exception {

        County county = this.generateCounty();

        String URI = "/county";
        String inputJson = this.mapToJson(county);

        Mockito.when(countyService.update(Mockito.any(County.class))).thenReturn(county);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat("").isEqualTo(outputJson);
    }

    @Test
    public void delete() throws Exception {

        County county = this.generateCounty();

        String URI = "/county/22";

        Mockito.when(countyService.delete(Mockito.anyLong())).thenReturn("success");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputJson = response.getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat("").isEqualTo(outputJson);
    }

    private County generateCounty() {
        return County.builder()
                .name("Pendik")
                .city(City.builder().name("İstanbul").build())
                .build();
    }
}