package com.ecomm.app.token;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {
	
	@Autowired
	private ConfirmationRepository confirmationRepository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationRepository.save(token);
	}

	public ConfirmationToken getToken(String token) {
		return confirmationRepository.findByToken(token);
	}
	
	public void confirmedAt(String token) {
     ConfirmationToken confirmToken = getToken(token);
     confirmToken.setConfrimedAt(LocalDateTime.now());

	}
	
}
