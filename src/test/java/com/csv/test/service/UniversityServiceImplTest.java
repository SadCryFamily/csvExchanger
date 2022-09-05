package com.csv.test.service;

import com.csv.test.dao.University;
import com.csv.test.dto.SimpleUniversityDto;
import com.csv.test.mapper.UniversityMapper;
import com.csv.test.repository.UniversityRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UniversityServiceImplTest {

    @MockBean
    private UniversityRepository universityRepository;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private UniversityMapper universityMapper;

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
    public void uploadCsvFile() throws IOException {

        List<University> expectableUniversityList = List.of(universityMapper.toUniversity(dto));

        Mockito.when(universityRepository.saveAll(expectableUniversityList))
                .thenReturn(expectableUniversityList);

        MultipartFile multipartFile =
                new MockMultipartFile("university.csv",
                        new FileInputStream("/Users/admin/Desktop/test/src/test/csv/university.csv"));

        var uploadCsvFileResult = universityService.uploadCsvFile(multipartFile);

        assertEquals(uploadCsvFileResult.getStatusCode(), HttpStatus.ACCEPTED);

    }

    @Test
    public void showAllUniversities() {

        List<University> expectableUniversityList = List.of(universityMapper.toUniversity(dto));
        List<SimpleUniversityDto> expectableSimpleDto = List.of(dto);

        Mockito.when(universityRepository.findAll()).thenReturn(expectableUniversityList);

        var showAllUniversitiesResult = universityService.showAllUniversities();

        Assert.assertEquals(showAllUniversitiesResult.get(0), expectableSimpleDto.get(0));

    }
}