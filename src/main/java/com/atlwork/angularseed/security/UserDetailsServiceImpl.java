package com.atlwork.angularseed.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atlwork.angularseed.app.domain.Authority;
import com.atlwork.angularseed.app.repository.UserRepository;
import com.atlwork.angularseed.security.exceptions.UserNotActivatedException;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

	String lowercaseLogin = login.toLowerCase();
	com.atlwork.angularseed.app.domain.User userFromDatabase = userRepository.findOneByLogin(lowercaseLogin);
	if (userFromDatabase == null) {
	    throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
	} else if (!userFromDatabase.isActivated()) {
	    throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
	}

	Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	for (Authority authority : userFromDatabase.getAuthorities()) {
	    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
	    grantedAuthorities.add(grantedAuthority);
	}
	return new User(lowercaseLogin, userFromDatabase.getPassword(), grantedAuthorities);
    }
}
