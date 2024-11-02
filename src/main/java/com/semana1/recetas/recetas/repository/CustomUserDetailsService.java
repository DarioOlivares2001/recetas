package com.semana1.recetas.recetas.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.semana1.recetas.recetas.model.Usuario;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
        Usuario usuario = optionalUsuario.orElseThrow(() -> 
            new UsernameNotFoundException("User not found: " + username)
        );

        return User.withUsername(usuario.getUsername())
            .password(usuario.getPassword())
            .roles("USER") // Cambia esto si tienes un campo para roles
            .build();
    }
}
