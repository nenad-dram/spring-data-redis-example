package com.endyary.springdataredis.domain;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(final User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());

        return response;
    }

    public User toEntity(final UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        return user;
    }
}
