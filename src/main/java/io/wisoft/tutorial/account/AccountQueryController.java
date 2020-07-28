package io.wisoft.tutorial.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountQueryController {

  private final AccountQueryService accountQueryService;


  public AccountQueryController(AccountQueryService accountQueryService) {
    this.accountQueryService = accountQueryService;
  }

  @GetMapping("/{accountNumber}/events")
  public List<Object> listEventForAccount(@PathVariable("accountNumber") final String accountNumber) {
    return accountQueryService.listEventsForAccount(accountNumber);
  }

  @GetMapping("/{accountNumber}")
  public AccountQueryEntity getAccount(@PathVariable("accountNumber") final String accountNumber) {
    return accountQueryService.getAccount(accountNumber);
  }
}
