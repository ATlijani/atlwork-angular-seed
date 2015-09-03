package com.atlwork.angularseed.app.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlwork.angularseed.app.domain.Authority;
import com.atlwork.angularseed.app.domain.User;
import com.atlwork.angularseed.app.dto.UserDTO;
import com.atlwork.angularseed.app.exceptions.AlreadyUsedEmailException;
import com.atlwork.angularseed.app.exceptions.AlreadyUsedLoginException;
import com.atlwork.angularseed.app.exceptions.DataNotFoundException;
import com.atlwork.angularseed.app.repository.AuthorityRepository;
import com.atlwork.angularseed.app.repository.UserRepository;
import com.atlwork.angularseed.security.utils.SecurityUtils;

@Service
@Transactional
public class UserService {

    private static final int DEF_COUNT = 20;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public void createUser(UserDTO userDTO, String baseUrl) throws AlreadyUsedLoginException, AlreadyUsedEmailException {

	String login = userDTO.getLogin();
	if (userRepository.findOneByLogin(login) != null)
	    throw new AlreadyUsedLoginException();

	String email = userDTO.getEmail().toLowerCase();
	if (userRepository.findOneByEmail(email) != null)
	    throw new AlreadyUsedEmailException();

	User newUser = new User();
	Authority authority = authorityRepository.findOneByName("ROLE_USER");
	Set<Authority> authorities = new HashSet<>();
	String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
	newUser.setLogin(login);
	newUser.setPassword(encryptedPassword);
	newUser.setFirstName(userDTO.getFirstName());
	newUser.setLastName(userDTO.getLastName());
	newUser.setEmail(email);
	newUser.setActivated(false);
	newUser.setActivationKey(RandomStringUtils.randomNumeric(DEF_COUNT));
	authorities.add(authority);
	newUser.setAuthorities(authorities);
	userRepository.save(newUser);

	mailService.sendActivationEmail(newUser, baseUrl);
    }

    public void updateUser(UserDTO userDTO) throws AlreadyUsedLoginException {

	String login = userDTO.getLogin();
	User userWithSameLogin = userRepository.findOneByLogin(login);
	if (userWithSameLogin != null && !userWithSameLogin.getLogin().equals(SecurityUtils.getCurrentLogin()))
	    throw new AlreadyUsedLoginException();

	User userWithSameEmail = userRepository.findOneByEmail(userDTO.getEmail());
	if (userWithSameEmail != null && !userWithSameEmail.getLogin().equals(SecurityUtils.getCurrentLogin()))
	    throw new AlreadyUsedEmailException();

	User currentUser = getCurrentUser();
	currentUser.setFirstName(userDTO.getFirstName());
	currentUser.setLastName(userDTO.getLastName());
	currentUser.setEmail(userDTO.getEmail());

	userRepository.save(currentUser);
    }

    @Transactional(readOnly = true)
    public User getCurrentUserWithAuthorities() {

	User currentUser = getCurrentUser();
	currentUser.getAuthorities().size();
	return currentUser;
    }

    public User getCurrentUser() {
	User currentUser = userRepository.findOneByLogin(SecurityUtils.getCurrentLogin());
	if (currentUser == null) {
	    throw new DataNotFoundException();
	}
	return currentUser;
    }

}
