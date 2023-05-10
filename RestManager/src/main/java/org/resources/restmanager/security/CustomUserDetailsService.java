package org.resources.restmanager.security;

import org.resources.restmanager.model.entities.auth.Role;
import org.resources.restmanager.model.entities.auth.User;
import org.resources.restmanager.repositories.auth.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private AppUserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(AppUserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        user.getPassword();
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),getGrantedAuthorities(user.getRoles()));
    }


    private Collection<GrantedAuthority> getGrantedAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
