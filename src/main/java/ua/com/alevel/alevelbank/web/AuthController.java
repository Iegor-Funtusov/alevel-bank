package ua.com.alevel.alevelbank.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.alevelbank.service.auth.AuthService;
import ua.com.alevel.alevelbank.web.dto.AuthData;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Boolean> registration(@RequestBody AuthData data) {
        authService.registration(data);
        return ResponseEntity.status(201).body(Boolean.TRUE);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthData data) {
        String token = authService.login(data);
        return ResponseEntity.ok(token);
    }
}
