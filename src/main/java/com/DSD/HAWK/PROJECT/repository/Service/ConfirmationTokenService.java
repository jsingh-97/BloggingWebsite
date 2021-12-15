package com.DSD.HAWK.PROJECT.repository.Service;

import com.DSD.HAWK.PROJECT.model.ConfirmationToken;
import com.DSD.HAWK.PROJECT.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    public ConfirmationToken getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    public void addToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.setConfirmedAt(token, LocalDateTime.now());
    }
}
