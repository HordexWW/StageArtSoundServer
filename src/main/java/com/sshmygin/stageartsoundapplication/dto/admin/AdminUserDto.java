package com.sshmygin.stageartsoundapplication.dto.admin;

import com.sshmygin.stageartsoundapplication.model.Status;
import com.sshmygin.stageartsoundapplication.model.User;
import com.sshmygin.stageartsoundapplication.model.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class AdminUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserRole> roles;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRoles(roles);
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setLastName(user.getLastName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setRoles(user.getRoles());
        return adminUserDto;
    }
}
