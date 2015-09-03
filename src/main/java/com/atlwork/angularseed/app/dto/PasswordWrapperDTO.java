package com.atlwork.angularseed.app.dto;

import javax.validation.constraints.Size;

public class PasswordWrapperDTO {

    @Size(min = 5, max = 100)
    private String newPassword;
    @Size(min = 5, max = 100)
    private String currentPassword;

    public String getNewPassword() {
	return newPassword;
    }

    public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
	return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
	this.currentPassword = currentPassword;
    }

}
