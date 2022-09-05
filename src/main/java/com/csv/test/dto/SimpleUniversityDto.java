package com.csv.test.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleUniversityDto {

    @CsvBindByName(column = "State")
    private String state;

    private String name;

    private String institutionType;

    private String phoneNumber;

    private String website;

}
