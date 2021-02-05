package ua.com.alevel.alevelbank.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.com.alevel.alevelbank.persistence.entity.token.Token;
import ua.com.alevel.alevelbank.persistence.entity.user.User;
import ua.com.alevel.alevelbank.persistence.repository.token.TokenRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityConfig extends OncePerRequestFilter {

    private final TokenRepository tokenRepository;

    public SecurityConfig(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Request URI is: " + httpServletRequest.getRequestURI());

        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            httpServletResponse.sendError(401, "unauthorized");
            return;
        }
        Token tokenData = tokenRepository.findByTokenUUID(token);
        if (tokenData == null) {
            httpServletResponse.sendError(401, "unauthorized");
            return;
        }

        User user = tokenData.getUser();
        httpServletRequest.setAttribute("user", user);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Bean
    public FilterRegistrationBean<SecurityConfig> filter() {
        FilterRegistrationBean<SecurityConfig> bean = new FilterRegistrationBean<>();

        bean.setFilter(new SecurityConfig(tokenRepository));
        bean.addUrlPatterns("/api/*");

        return bean;
    }
}
