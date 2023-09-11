package net.javaguides.springboot.service;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user=new User(registrationDto.getFirstName(),
							registrationDto.getLastName(), 
							registrationDto.getUsername(),
							passwordEncoder.encode(registrationDto.getPassword()), 
							Arrays.asList(new Role("ROLE_USER")), registrationDto.getEmail(),
				registrationDto.getFirma(), registrationDto.getAnrede());
		
		return userRepository.save(user);
	}

	@Override
	public User update(UserRegistrationDto registrationDto) {
		//SecurityConfiguration()
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		User userToUpdateUser = userRepository.findByUsername(currentUsername);

		if(!registrationDto.getFirstName().isEmpty()){
			userToUpdateUser.setFirstName(registrationDto.getFirstName());
		}
		if(!registrationDto.getLastName().isEmpty()){
			userToUpdateUser.setLastName(registrationDto.getLastName());
		}
		if(!registrationDto.getPassword().isEmpty()){
			userToUpdateUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		}
		if(!registrationDto.getEmail().isEmpty()){
			userToUpdateUser.setEmail(registrationDto.getEmail());
		}
		if(!registrationDto.getFirma().isEmpty()){
			userToUpdateUser.setFirma(registrationDto.getFirma());
		}

		return userRepository.save(userToUpdateUser);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		
		User user=userRepository.findByUsername(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("Invalid Username or password");			
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	
	}
}
