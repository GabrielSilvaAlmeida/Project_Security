package com.clone.crunchroll.model.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteDetailService implements UserDetailsService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> cliente = repository.findByUsername(username);

        if (cliente.isPresent()) {
            var userObj = cliente.get();
            return User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .roles(getRoles(userObj))
                .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(Cliente userObj) {
        if (userObj.getRole() == null) {
            return new String[]{"USER"};
        }
        return userObj.getRole().split(",");
    }
}