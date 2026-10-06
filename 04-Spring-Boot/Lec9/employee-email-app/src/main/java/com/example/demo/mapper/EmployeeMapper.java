package com.example.demo.mapper;

import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.EmployeeWithEmailsRequestDto;
import com.example.demo.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = EmailMapper.class)
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emails", ignore = true)
    Employee toEntity(EmployeeRequestDto dto);

    @Mapping(target = "id", ignore = true)
    Employee toEntityWithEmails(EmployeeWithEmailsRequestDto dto);

    EmployeeResponseDto toDto(Employee employee);

    List<EmployeeResponseDto> toDtoList(List<Employee> employees);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emails", ignore = true)
    void updateEntity(EmployeeRequestDto dto, @MappingTarget Employee employee);
}
