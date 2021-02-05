package ua.com.alevel.alevelbank.config;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ua.com.alevel.alevelbank.persistence.entity.user.User;

import javax.servlet.http.HttpServletRequest;

@Component
@Scope(value = "session",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestProvider {

    private final HttpServletRequest httpServletRequest;

    public RequestProvider(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public User selectClient(){
        return (User) httpServletRequest.getAttribute("user");
    }
}
