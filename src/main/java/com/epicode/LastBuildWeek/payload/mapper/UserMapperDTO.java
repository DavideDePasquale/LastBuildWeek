package com.epicode.LastBuildWeek.payload.mapper;

import com.epicode.LastBuildWeek.model.Role;
import com.epicode.LastBuildWeek.model.User;
import com.epicode.LastBuildWeek.payload.UserDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Component
public class UserMapperDTO {

    public UserDTO toDto(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setAvatar(entity.getAvatar());
        dto.setNome(entity.getNome());
        dto.setCognome(entity.getCognome());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setUsername(entity.getUsername());

        // Convert Set<Role> to Set<String>
        Set<String> roleNames = entity.getRoles().stream()
                .map(Role::getName)
                .map(Enum::name)  // Convert UserRole to String
                .collect(Collectors.toSet());
        dto.setRoles(roleNames);

        return dto;
    }

    public User toEntity(UserDTO dto, Set<Role> availableRoles) {
        User entity = new User();
        entity.setAvatar(dto.getAvatar());
        entity.setNome(dto.getNome());
        entity.setCognome(dto.getCognome());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());

        //Checking `null` before `stream()`
        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            Set<Role> roles = dto.getRoles().stream()
                    .map(roleName -> availableRoles.stream()
                            .filter(role -> role.getName().name().equalsIgnoreCase(roleName))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Ruolo non trovato: " + roleName))
                    )
                    .collect(Collectors.toSet());
            entity.setRoles(roles);
        } else {
            // If `roles` has no value, set the `USER` role as the default.
            Role defaultRole = availableRoles.stream()
                    .filter(role -> role.getName().name().equalsIgnoreCase("USER"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Ruolo USER non trovato nel database!"));
            entity.setRoles(Collections.singleton(defaultRole));
        }

        return entity;
    }


    public User updateUser(UserDTO userDTO, User user, Set<Role> availableRoles) {
        if (userDTO.getAvatar() != null) {
            user.setAvatar(userDTO.getAvatar());
        }
        if (userDTO.getNome() != null) {
            user.setNome(userDTO.getNome());
        }
        if (userDTO.getCognome() != null) {
            user.setCognome(userDTO.getCognome());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }

        if (userDTO.getRoles() != null) {
            // Convert `Set<String>` to `Set<Role>`
            Set<Role> roles = userDTO.getRoles().stream()
                    .map(roleName -> availableRoles.stream()
                            .filter(role -> role.getName().name().equalsIgnoreCase(roleName))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Ruolo non trovato: " + roleName))
                    )
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        return user;
    }
}
