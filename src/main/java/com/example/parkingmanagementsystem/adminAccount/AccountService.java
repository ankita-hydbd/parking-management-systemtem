package com.example.parkingmanagementsystem.adminAccount;

import java.util.List;

public interface AccountService {
    public String createAccount(Account account);

    public String updateAccount(Account account);

    public String deleteAccount(String userName);

    public Account getAccount(String userName);

    public List<Account> getAllAccount();

}