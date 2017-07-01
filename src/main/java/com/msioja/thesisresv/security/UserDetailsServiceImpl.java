package com.msioja.thesisresv.security;

import com.msioja.thesisresv.model.Role;
import com.msioja.thesisresv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.msioja.thesisresv.model.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.msioja.thesisresv.model.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), true, true,
                true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.toString()));
    }
}
