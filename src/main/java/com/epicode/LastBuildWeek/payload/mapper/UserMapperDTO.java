package com.epicode.LastBuildWeek.payload.mapper;

import com.epicode.LastBuildWeek.model.Role;
import com.epicode.LastBuildWeek.model.User;
import com.epicode.LastBuildWeek.payload.UserDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Component
public class UserMapperDTO {

    public UserDTO toDto(User entity){
        UserDTO dto = new UserDTO();
        dto.setAvatar(entity.getAvatar());
        dto.setNome(entity.getNome());
        dto.setRoles(entity.getRoles());
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
        entity.setRoles(dto.getRoles());
        entity.setCognome(dto.getCognome());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        return entity;
    }

    public User updateUser(UserDTO userDTO, User user){
        if (userDTO.getAvatar() != null){
            user.setAvatar(userDTO.getAvatar());
        }
        if (userDTO.getNome() != null){
            user.setNome(userDTO.getNome());
        }
        if (userDTO.getRoles() != null){
            user.setRoles(userDTO.getRoles());
        }
        if (userDTO.getCognome() != null){
            user.setCognome(userDTO.getCognome());
        }
        if (userDTO.getEmail() != null){
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null){
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getUsername() != null){
            user.setUsername(userDTO.getUsername());
        }
        return user;
    }

}
