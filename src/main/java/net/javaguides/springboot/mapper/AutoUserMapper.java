package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//This Interface Is Part Of MapStruct Implementation
@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    //in case if the name is diff between jpa and dto we can use annotation to spicify the source name and the target name
    //@Mapping(source = "email",target = "emailAddress")
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
