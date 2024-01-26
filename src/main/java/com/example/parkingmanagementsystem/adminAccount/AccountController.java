package com.example.parkingmanagementsystem.adminAccount;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Account")
@Log4j2
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{userName}")
    public Account getAccount(@PathVariable("userName") String userName) {
        log.info("Received userName = {}", userName);
        return accountService.getAccount(userName);
    }

    @GetMapping()
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @PostMapping
    public String createAccount(@RequestBody Account account) {
        log.info("Received account details = {}", account);
        accountService.createAccount(account);
        return "Account created successfully";
    }

    @PutMapping
    public String updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
        return "Account updated successfully";
    }

    @DeleteMapping("{userName}")
    public String deleteAccount(@PathVariable("userName") String userName) {
        accountService.deleteAccount(userName);

        return "Account deleted successfully";
    }
}
