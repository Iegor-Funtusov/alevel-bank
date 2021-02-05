package ua.com.alevel.alevelbank.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.alevelbank.persistence.entity.user.Personal;
import ua.com.alevel.alevelbank.service.user.PersonalService;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping
    public ResponseEntity<Personal> getPersonal() {
        return ResponseEntity.ok(new Personal());
    }
}
