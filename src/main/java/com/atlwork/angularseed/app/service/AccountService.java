package com.atlwork.angularseed.app.service;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlwork.angularseed.app.domain.User;
import com.atlwork.angularseed.app.dto.UserDTO;
import com.atlwork.angularseed.app.exceptions.BadPasswordException;
import com.atlwork.angularseed.app.exceptions.DataNotFoundException;
import com.atlwork.angularseed.app.exceptions.EmailNotRegisteredException;
import com.atlwork.angularseed.app.exceptions.UserNotActivatedException;
import com.atlwork.angularseed.app.repository.AuthorityRepository;
import com.atlwork.angularseed.app.repository.UserRepository;
import com.atlwork.angularseed.security.utils.SecurityUtils;

@Service
@Transactional
public class AccountService {

    private static final int DEF_COUNT = 20;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public void activateRegistration(String key) {

	User user = userRepository.findOneByActivationKey(key);
	if (user == null) {
	    throw new DataNotFoundException();
	}

	user.setActivated(true);
	user.setActivationKey(null);
	userRepository.save(user);
    }

    public boolean checkIsSamePassword(String password) {
	User currentUser = userRepository.findOneByLogin(SecurityUtils.getCurrentLogin());
	if (currentUser == null) {
	    throw new DataNotFoundException();
	}
	return password != null && passwordEncoder.matches(password, currentUser.getPassword());
    }

    public void changePassword(String password) {

	User currentUser = userRepository.findOneByLogin(SecurityUtils.getCurrentLogin());
	currentUser.setPassword(passwordEncoder.encode(password));
	userRepository.save(currentUser);
    }

    public void requestPasswordReset(String mail, String baseUrl) {

	User user = userRepository.findOneByEmail(mail);
	if (user == null) {
	    throw new EmailNotRegisteredException();
	}
	if (!user.isActivated()) {
	    throw new UserNotActivatedException();
	}

	user.setResetKey(RandomStringUtils.randomNumeric(DEF_COUNT));
	user.setResetDate(DateTime.now());
	userRepository.save(user);

	mailService.sendPasswordResetMail(user, baseUrl);

    }

    public void finishPasswordReset(String newPassword, String key) {

	if (!checkPasswordLength(newPassword)) {
	    throw new BadPasswordException();
	}

	User user = userRepository.findOneByResetKey(key);
	if (user == null) {
	    throw new DataNotFoundException();
	}
	DateTime oneDayAgo = DateTime.now().minusHours(24);
	if (user != null && user.isActivated()) {
	    if (user.getResetDate().isAfter(oneDayAgo.toInstant().getMillis())) {
		user.setPassword(passwordEncoder.encode(newPassword));
		user.setResetKey(null);
		user.setResetDate(null);
		userRepository.save(user);
	    }
	}
    }

    private boolean checkPasswordLength(String password) {

	return (!StringUtils.isEmpty(password) && password.length() >= UserDTO.PASSWORD_MIN_LENGTH && password.length() <= UserDTO.PASSWORD_MAX_LENGTH);
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {

	List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(new DateTime().minusDays(3));
	for (User user : users) {
	    userRepository.delete(user);
	}
    }
}
