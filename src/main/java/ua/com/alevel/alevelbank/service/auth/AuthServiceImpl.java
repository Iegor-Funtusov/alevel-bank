package ua.com.alevel.alevelbank.service.auth;

import org.springframework.stereotype.Service;
import ua.com.alevel.alevelbank.persistence.entity.balance.Balance;
import ua.com.alevel.alevelbank.persistence.entity.token.Token;
import ua.com.alevel.alevelbank.persistence.entity.user.Personal;
import ua.com.alevel.alevelbank.persistence.entity.user.User;
import ua.com.alevel.alevelbank.persistence.repository.balance.BalanceRepository;
import ua.com.alevel.alevelbank.persistence.repository.token.TokenRepository;
import ua.com.alevel.alevelbank.persistence.repository.user.PersonalRepository;
import ua.com.alevel.alevelbank.persistence.repository.user.UserRepository;
import ua.com.alevel.alevelbank.web.dto.AuthData;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PersonalRepository personalRepository;
    private final TokenRepository tokenRepository;
    private final BalanceRepository balanceRepository;

    public AuthServiceImpl(UserRepository userRepository, PersonalRepository personalRepository, TokenRepository tokenRepository, BalanceRepository balanceRepository) {
        this.userRepository = userRepository;
        this.personalRepository = personalRepository;
        this.tokenRepository = tokenRepository;
        this.balanceRepository = balanceRepository;
    }

    @Override
    public void registration(AuthData data) {
        User user = userRepository.findByEmail(data.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeException("error");
        }
        Personal personal = new Personal();
        personal.setEmail(data.getEmail());

        String securePassword = getSecurePassword(data.getPassword());
        System.out.println(securePassword);
        personal.setPassword(securePassword);
        personal = personalRepository.save(personal);
        Balance balance = new Balance();
        balance.setPersonal(personal);
        balance.setBalanceValue(new BigDecimal("1000.00"));
        balanceRepository.save(balance);
    }

    @Override
    public String login(AuthData data) {
        User user = userRepository.findByEmail(data.getEmail()).orElse(null);
        if (user == null) {
            throw new RuntimeException("error");
        }

        String securePassword = getSecurePassword(data.getPassword());
        System.out.println("securePassword = " + securePassword);
        System.out.println("user.getPassword = " + user.getPassword());
        if (securePassword.equals(user.getPassword())) {
            String uuid = UUID.randomUUID().toString();
            String token = getToken(uuid);
            Token tokenData = new Token();
            tokenData.setUser(user);
            tokenData.setTokenUUID(token);
            tokenRepository.save(tokenData);
            return token;
        }

        throw new RuntimeException("error");
    }

    private String getToken(String token) {
        Token user = tokenRepository.findByTokenUUID(token);
        if (user != null) {
            String uuid = UUID.randomUUID().toString();
            return getToken(uuid);
        }
        return token;
    }

    private String getSecurePassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
