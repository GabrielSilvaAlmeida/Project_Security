package com.clone.crunchroll.model.cliente;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;



public class ClienteUserDetails implements UserDetails {

    
    private final Cliente cliente;

    public ClienteUserDetails(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retornar uma coleção contendo uma autoridade fixa (USER)
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }
    @Override
    public String getPassword() {
        return cliente.getPassword();
    }

    @Override
    public String getUsername() {
        return cliente.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}