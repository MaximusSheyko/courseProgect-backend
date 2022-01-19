package com.example.courseprogectbackend.mapper;

import com.example.courseprogectbackend.dto.UserProfileDto;
import com.example.courseprogectbackend.model.Role;
import com.example.courseprogectbackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", source = "name")
    @Mapping( target = "roles", source = "roles", qualifiedByName = "toRolesDto")
    UserProfileDto toDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "name", source = "username")
    @Mapping(target = "likeList", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "collections", ignore = true)
    @Mapping(target = "roles", source = "roles", qualifiedByName = "toRoles")
    User toUser(UserProfileDto userProfileDto);

    List<UserProfileDto> toUsersProfileDto(List<User> users);

    @Named("toRolesDto")
    default List<String> toRolesDto(Set<Role> roles){
        return roles.stream().map(Role::getName).collect( Collectors.toList());
    }

    @Named("toRoles")
    default Set<Role> toRoles(List<String> rolesDto){
        return rolesDto.stream().map(s -> Role.builder().name(s).build()).collect(Collectors.toSet());
    }
}
