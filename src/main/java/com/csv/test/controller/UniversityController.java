package com.csv.test.controller;

import com.csv.test.dto.SimpleUniversityDto;
import com.csv.test.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/upload")
    public ResponseEntity uploadCsvFile(@RequestParam ("file") MultipartFile csvFile) throws IOException {
        return universityService.uploadCsvFile(csvFile);
    }

    @GetMapping("/all")
    public List<SimpleUniversityDto> showAllUniversities() {
        return universityService.showAllUniversities();
    }

}
