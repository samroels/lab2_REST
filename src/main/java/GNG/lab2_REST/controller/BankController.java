package GNG.lab2_REST.controller;

import GNG.lab2_REST.client.Client;
import GNG.lab2_REST.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/balance/{accountID}")
    public double getBalance(@PathVariable String accountID) {
        return accountService.getBalance(accountID);
    }

    /*
    @PostMapping("/deposit/{accountID}/{amount}")
    public double deposit(@PathVariable String accountID, @PathVariable double amount) {
        accountService.deposit(accountID, amount);
        return accountService.getBalance(accountID);
    }


    @PutMapping("/withdraw/{accountID}/{amount}")
    public double withdraw(@PathVariable String accountID, @PathVariable double amount) {
        accountService.withdraw(accountID, amount);
        return accountService.getBalance(accountID);
    }
    */

    // Deposit and withdraw using JSON body in PostMan instead of URL parameters

    @PostMapping("/deposit")
    public  double deposit(@RequestBody Client request) {
        accountService.deposit(request.getAccountID(), request.getAmount());
        return accountService.getBalance(request.getAccountID());
    }

    @PutMapping("/withdraw")
    public double withdraw(@RequestBody Client request) {
        accountService.withdraw(request.getAccountID(), request.getAmount());
        return accountService.getBalance(request.getAccountID());
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("Bank is up and running");
    }

    /*
    SAME AS WITHDRAW METHOD ???
    @PostMapping("/remove/{accountID}/{amount}")
    public double remove(@PathVariable String accountID, @PathVariable double amount) {
        accountService.remove(accountID, amount);
        return accountService.getBalance(accountID);
    }
     */
}
