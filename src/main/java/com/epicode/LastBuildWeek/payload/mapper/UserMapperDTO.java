package com.epicode.LastBuildWeek.payload.mapper;

import com.epicode.LastBuildWeek.model.Role;
import com.epicode.LastBuildWeek.model.User;
import com.epicode.LastBuildWeek.payload.UserDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapperDTO {

    public UserDTO toDto(User entity){
        UserDTO dto = new UserDTO();
        dto.setAvatar(entity.getAvatar());
        dto.setNome(entity.getNome());
        dto.setRoles(entity.getRoles().stream().map(Role::getName).toString());
        dto.setCognome(entity.getCognome());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setUsername(entity.getUsername());
        return dto;
    }

    public User toEntity(UserDTO dto){
        User entity = new User();
        entity.setAvatar(dto.getAvatar());
        entity.setNome(dto.getNome());
        dto.setRoles(entity.getRoles().stream()
                .map(Role::getName).toString());
        entity.setCognome(dto.getCognome());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        return entity;
    }

}
