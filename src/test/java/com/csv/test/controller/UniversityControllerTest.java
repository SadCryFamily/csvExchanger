package com.csv.test.controller;

import com.csv.test.dto.SimpleUniversityDto;
import com.csv.test.mapper.UniversityMapper;
import com.csv.test.service.UniversityService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UniversityControllerTest {

    @MockBean
    private UniversityService universityService;

    @Autowired
    private UniversityMapper universityMapper;

    @Autowired
    private MockMvc mockMvc;

    private SimpleUniversityDto dto;

    @Before
    public void setUp() throws Exception {
        dto = SimpleUniversityDto.builder()
                .state("UA")
                .name("Kharkiv National University Of Radioelectronics")
                .institutionType("ANNH")
                .phoneNumber("(380) 774-235-12")
                .website("nure.ua").build();
    }

    @Test
    public void uploadCsvFile() throws Exception {

        MultipartFile multipartFile =
                new MockMultipartFile("university.csv",
                        new FileInputStream("/Users/admin/Desktop/test/src/test/csv/university.csv"));

        Mockito.when(universityService.uploadCsvFile(multipartFile))
                .thenReturn(new ResponseEntity(HttpStatus.ACCEPTED));

        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file("file", multipartFile.getBytes())
                .characterEncoding("UTF-8")
                        .contentType(MediaType.valueOf("application/csv")))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void showAllUniversities() throws Exception {

        Mockito.when(universityService.showAllUniversities()).thenReturn(List.of(dto));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/all"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].state", Matchers.is("UA")))
                .andExpect(jsonPath("$[0].institutionType", Matchers.is("ANNH")))
                .andExpect(jsonPath("$[0].website", Matchers.is("nure.ua")));

    }
}