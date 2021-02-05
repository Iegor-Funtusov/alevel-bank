package ua.com.alevel.alevelbank.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.alevelbank.config.RequestProvider;
import ua.com.alevel.alevelbank.persistence.entity.balance.Balance;
import ua.com.alevel.alevelbank.persistence.entity.user.Personal;
import ua.com.alevel.alevelbank.persistence.entity.user.User;
import ua.com.alevel.alevelbank.service.balance.BalanceService;
import ua.com.alevel.alevelbank.web.dto.TransferData;

import java.util.List;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    private final BalanceService balanceService;
    private final RequestProvider requestProvider;

    public BalanceController(BalanceService balanceService, RequestProvider requestProvider) {
        this.balanceService = balanceService;
        this.requestProvider = requestProvider;
    }

    @GetMapping
    public ResponseEntity<List<Balance>> getBalance() {
        User user = requestProvider.selectClient();
        if (user == null) {
            throw new RuntimeException("user not found");
        }
        Personal personal = (Personal) user;
        return ResponseEntity.ok(balanceService.findByPersonal(personal));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Boolean> transfer(@RequestBody TransferData data) {
        User user = requestProvider.selectClient();
        if (user == null) {
            throw new RuntimeException("user not found");
        }
        Personal personal = (Personal) user;
        balanceService.transfer(data.getSum(), personal, data.getTo());
        return ResponseEntity.status(201).body(true);
    }
}
