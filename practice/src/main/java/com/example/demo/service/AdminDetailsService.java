package com.example.demo.service;

import jakarta.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminDetailsService implements UserDetailsService {

	private final AdminRepository adminRepository;

	public AdminDetailsService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Admin admin = adminRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Adminに :" + email + ": が見つかりません。"));

		return User.builder()
				.username(admin.getEmail())
				.password(admin.getPassword())
				.roles("ADMIN")
				.build();
	}

}
