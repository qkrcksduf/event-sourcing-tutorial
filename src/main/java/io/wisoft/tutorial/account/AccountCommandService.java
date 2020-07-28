package io.wisoft.tutorial.account;

import io.wisoft.tutorial.account.dto.AccountCreateDto;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface AccountCommandService {

  CompletableFuture<String> createAccount(final AccountCreateDto accountCreateDto);
  CompletableFuture<String> creditMoneyToAccount(final String accountNumber, final MoneyCreditDto moneyCreditDto);
  CompletableFuture<String> debitMoneyFromAccount(final String accountNumber, final MoneyDebitDto moneyDebitDto);

}
