package com.example.parkingmanagementsystem.adminAccount;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository= accountRepository;
    }

    @Override
    public String createAccount(Account account) {
      accountRepository.save(account);
        return "successfully created";
    }

    @Override
    public String updateAccount(Account account) {
       accountRepository.save(account);
        return "successfully updated";
    }

    @Override
    public String deleteAccount(String userName) {
        accountRepository.deleteById(userName);
        return "successfully deleted";
    }

    @SneakyThrows
    @Override
    public Account getAccount(String userName)  {
        if(accountRepository.findById(userName).isEmpty())
            throw new AccountNotFoundException("Account detail doesn't exist");

        return  accountRepository.findById(userName).get();
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }
}

