package ru.gordanov.crud_spring_boot.service;

import org.springframework.stereotype.Service;
import ru.gordanov.crud_spring_boot.entity.Role;
import ru.gordanov.crud_spring_boot.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name) {
       return roleRepository.findByRole(name);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
