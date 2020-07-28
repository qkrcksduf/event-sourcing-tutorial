package io.wisoft.tutorial.account;

import io.wisoft.tutorial.account.dto.AccountCreateDto;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/accounts")
public class AccountCommandController {


  private final AccountCommandService accountCommandService;

  public AccountCommandController(AccountCommandService accountCommandService) {
    this.accountCommandService = accountCommandService;
  }

  @PostMapping
  public CompletableFuture<String> createAccount(@RequestBody final AccountCreateDto accountCreateDto) {
    return accountCommandService.createAccount(accountCreateDto);
  }

  @PutMapping("/credits/{accountNumber}")
  public CompletableFuture<String> createMoneyToAccount(@PathVariable("accountNumber") final String accountNumber,
                                                        @RequestBody final MoneyCreditDto moneyCreditDto) {
    return accountCommandService.creditMoneyToAccount(accountNumber, moneyCreditDto);
  }

  @PutMapping("/debits/{accountNumber}")
  public CompletableFuture<String> debitMoneyFromAccount(@PathVariable("accountNumber") final String accountNumber,
                                                         @RequestBody final MoneyDebitDto moneyDebitDto) {
    return accountCommandService.debitMoneyFromAccount(accountNumber, moneyDebitDto);
  }
}
