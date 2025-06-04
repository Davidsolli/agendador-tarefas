package com.david.agendadortarefas.infrastructure.security;

import com.david.agendadortarefas.business.dto.UserDTO;
import com.david.agendadortarefas.infrastructure.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient userClient;

    public UserDetails loadUserByUsername(String email, String token) {
        UserDTO userDTO = userClient.findUserByEmail(email, token);
        return User
                .withUsername(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
