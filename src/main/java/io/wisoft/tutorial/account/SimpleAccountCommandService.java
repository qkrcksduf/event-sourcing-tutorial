package io.wisoft.tutorial.account;

import io.wisoft.tutorial.account.dto.AccountCreateDto;
import io.wisoft.tutorial.command.CreateAccountCommand;
import io.wisoft.tutorial.command.CreditMoneyCommand;
import io.wisoft.tutorial.command.DebitMoneyCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class SimpleAccountCommandService implements AccountCommandService {

  private final CommandGateway commandGateway;

  public SimpleAccountCommandService(final CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @Override
  public CompletableFuture<String> createAccount(AccountCreateDto accountCreateDto) {
    return this.commandGateway.send(
        new CreateAccountCommand(
            UUID.randomUUID().toString(),
            accountCreateDto.getStartingBalance(),
            accountCreateDto.getCurrency()
        )
    );
  }

  @Override
  public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDto moneyCreditDto) {
    return this.commandGateway.send(
        new CreditMoneyCommand(
            accountNumber,
            moneyCreditDto.getCreditAmount(),
            moneyCreditDto.getCurrency()
        )
    );
  }

  @Override
  public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDto moneyDebitDto) {
    return this.commandGateway.send(
        new DebitMoneyCommand(
            accountNumber,
            moneyDebitDto.getDebitAmount(),
            moneyDebitDto.getCurrency()
        )
    );
  }
}
