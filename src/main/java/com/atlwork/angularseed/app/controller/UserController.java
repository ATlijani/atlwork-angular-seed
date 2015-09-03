package com.atlwork.angularseed.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atlwork.angularseed.app.domain.Authority;
import com.atlwork.angularseed.app.domain.User;
import com.atlwork.angularseed.app.dto.PasswordWrapperDTO;
import com.atlwork.angularseed.app.dto.UserDTO;
import com.atlwork.angularseed.app.exceptions.DataNotFoundException;
import com.atlwork.angularseed.app.exceptions.DoNotMatchCurrentPasswordException;
import com.atlwork.angularseed.app.service.AccountService;
import com.atlwork.angularseed.app.service.UserService;

@RestController
@RequestMapping("/api/account")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/useraccount", method = RequestMethod.GET)
    public UserDTO getUserAccount() {

	User user = userService.getCurrentUserWithAuthorities();
	if (user == null) {
	    throw new DataNotFoundException();
	}
	List<String> roles = new ArrayList<>();
	for (Authority authority : user.getAuthorities()) {
	    roles.add(authority.getName());
	}

	return new UserDTO(user.getLogin(), null, user.getFirstName(), user.getLastName(), user.getEmail(), roles);
    }

    @RequestMapping(value = "/useraccount", method = RequestMethod.POST)
    public void updateUserAccount(@RequestBody UserDTO userDTO) {
	userService.updateUser(userDTO);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUserAccount(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) {
	userService.createUser(userDTO, getEmailBaseUrl(request));
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public void activateRegistration(@RequestParam(value = "key") String key) {
	accountService.activateRegistration(key);
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public void changePassword(@Valid @RequestBody PasswordWrapperDTO passwordWrapper) {
	if (!accountService.checkIsSamePassword(passwordWrapper.getCurrentPassword())) {
	    throw new DoNotMatchCurrentPasswordException();
	}
	accountService.changePassword(passwordWrapper.getNewPassword());
    }

    @RequestMapping(value = "/reset_password/init", method = RequestMethod.POST)
    public void requestPasswordReset(@Valid @RequestBody String mail, HttpServletRequest request) {
	accountService.requestPasswordReset(mail, getEmailBaseUrl(request));
    }

    @RequestMapping(value = "/reset_password/finish", method = RequestMethod.POST)
    public void finishPasswordReset(@RequestParam(value = "key") String key, @RequestParam(value = "newPassword") String newPassword) {
	accountService.finishPasswordReset(newPassword, key);
    }

    private String getEmailBaseUrl(HttpServletRequest request) {
	return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
