package com.csv.test.service;

import com.csv.test.dao.University;
import com.csv.test.dto.SimpleUniversityDto;
import com.csv.test.mapper.UniversityMapper;
import com.csv.test.repository.UniversityRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private UniversityMapper universityMapper;

    @Override
    public ResponseEntity uploadCsvFile(MultipartFile csvFile) throws IOException {

        InputStreamReader input = new InputStreamReader(csvFile.getInputStream());
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(';').parse(input);

        List<University> universities = new ArrayList<>();

        List<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {

            SimpleUniversityDto universityDto = SimpleUniversityDto.builder()
                    .state(csvRecord.get("State"))
                    .name(csvRecord.get("Name"))
                    .institutionType(csvRecord.get("Institution Type"))
                    .phoneNumber(csvRecord.get("Phone Number"))
                    .website(csvRecord.get("Website"))
                    .build();

            University university = universityMapper.toUniversity(universityDto);

            universities.add(university);

        }

        universityRepository.saveAll(universities);

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    @Override
    public List<SimpleUniversityDto> showAllUniversities() {

        List<University> savedUniversity = universityRepository.findAll();

        List<SimpleUniversityDto> mappedUniversity = savedUniversity.stream()
                .map(dao -> universityMapper.toSimpleUniversityDto(dao))
                .collect(Collectors.toList());

        return mappedUniversity;

    }
}
