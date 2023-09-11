package net.javaguides.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

import java.time.LocalDateTime;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);
	User update(UserRegistrationDto registrationDto);

}
