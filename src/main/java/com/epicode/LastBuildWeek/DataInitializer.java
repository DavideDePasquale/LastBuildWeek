package com.epicode.LastBuildWeek;


import com.epicode.LastBuildWeek.enumeration.UserRole;
import com.epicode.LastBuildWeek.model.Role;
import com.epicode.LastBuildWeek.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName(UserRole.ADMIN);
            roleRepository.save(userRole);
            Role adminRole = new Role();
            adminRole.setName(UserRole.USER);
            roleRepository.save(adminRole);
        }
    }
}
