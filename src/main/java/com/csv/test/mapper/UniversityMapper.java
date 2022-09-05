package com.csv.test.mapper;

import com.csv.test.dao.University;
import com.csv.test.dto.SimpleUniversityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UniversityMapper {

    UniversityMapper INSTANCE = Mappers.getMapper(UniversityMapper.class);

    University toUniversity(SimpleUniversityDto universityDto);

    SimpleUniversityDto toSimpleUniversityDto(University university);
}
