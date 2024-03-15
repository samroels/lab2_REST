package GNG.lab2_REST.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
@Service
public class AccountService {
    private final ConcurrentHashMap<String, Double> accountBalances = new ConcurrentHashMap<>();

    public double getBalance(String accountID) {
        return accountBalances.getOrDefault(accountID, 0.0);
    }

    public void deposit(String accountID, double amount) {
        accountBalances.compute(accountID, (id, balance) -> (balance == null) ? amount : balance + amount);
    }

    public void withdraw(String accountID, double amount) {
        accountBalances.computeIfPresent(accountID, (id, balance) -> {
            if (balance >= amount) {
                return balance - amount;
            }
            else{
                throw new IllegalArgumentException("Insufficient funds");
            }
        });
    }

    public void remove(String accountID, double amount) {
        accountBalances.computeIfPresent(accountID, (id, balance) -> {
            if (balance >= amount) {
                return balance - amount;
            }
            else{
                throw new IllegalArgumentException("Insufficient funds");
            }
        });
    }
}
