package com.dairyproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword {
	private String emailId;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
}
