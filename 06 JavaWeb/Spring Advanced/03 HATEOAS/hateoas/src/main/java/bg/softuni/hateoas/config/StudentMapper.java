package bg.softuni.hateoas.config;

import bg.softuni.hateoas.model.dto.StudentDto;
import bg.softuni.hateoas.model.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto mapEntityToDto(StudentEntity student);
}
