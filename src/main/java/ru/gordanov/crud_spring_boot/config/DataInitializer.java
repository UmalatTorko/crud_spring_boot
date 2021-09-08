package ru.gordanov.crud_spring_boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gordanov.crud_spring_boot.entity.Role;
import ru.gordanov.crud_spring_boot.entity.User;
import ru.gordanov.crud_spring_boot.service.RoleService;
import ru.gordanov.crud_spring_boot.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));

        User user = new User();
        user.setName("Admin");
        user.setSurname("Adminov");
        user.setEmail("admin@gmail.com");
        user.setUsername("admin");
        user.setPassword("admin");
        user.setRoles(new String[]{"ROLE_ADMIN", "ROLE USER"});
        userService.saveOrUpdate(user);

        User user2 = new User();
        user2.setName("User");
        user2.setSurname("Userov");
        user2.setEmail("user@gmail.com");
        user2.setUsername("user");
        user2.setPassword("user");
        user2.setRoles(new String[]{"ROLE_USER"});
        userService.saveOrUpdate(user2);
    }
}
