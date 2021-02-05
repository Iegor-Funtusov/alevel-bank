package ua.com.alevel.alevelbank.service.auth;

import ua.com.alevel.alevelbank.web.dto.AuthData;

public interface AuthService {

    void registration(AuthData data);
    String login(AuthData data);
}
