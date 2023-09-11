package net.javaguides.springboot.web;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.time.LocalDateTime;
import java.util.Properties;

@Controller
public class MainController {
	private UserRepository userRepository;

	public MainController(UserRepository userRepository) {
		super();
		this.userRepository=userRepository;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home()
	{
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(currentUsername);
		user.setLastlogin(LocalDateTime.now());
		userRepository.save(user);
		return "index";
	}
}
