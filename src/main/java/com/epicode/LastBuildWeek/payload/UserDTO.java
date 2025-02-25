package com.epicode.LastBuildWeek.payload;

import com.epicode.LastBuildWeek.model.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {


    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;
    private Set<Role> roles;

}
