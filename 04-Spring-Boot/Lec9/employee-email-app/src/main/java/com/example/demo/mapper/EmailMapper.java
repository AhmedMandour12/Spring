package com.example.demo.mapper;

import com.example.demo.dto.EmailRequestDto;
import com.example.demo.dto.EmailResponseDto;
import com.example.demo.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    Email toEntity(EmailRequestDto dto);

    @Mapping(source = "employee.id", target = "employeeId")
    EmailResponseDto toDto(Email email);

    List<EmailResponseDto> toDtoList(List<Email> emails);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    void updateEntity(EmailRequestDto dto, @MappingTarget Email email);
}
