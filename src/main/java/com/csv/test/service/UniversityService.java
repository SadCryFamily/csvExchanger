package com.csv.test.service;

import com.csv.test.dto.SimpleUniversityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UniversityService {

    ResponseEntity uploadCsvFile(MultipartFile csvFile) throws IOException;

    List<SimpleUniversityDto> showAllUniversities();

}
