package io.wisoft.tutorial.account;

import io.wisoft.tutorial.command.CreateAccountCommand;
import io.wisoft.tutorial.command.CreditMoneyCommand;
import io.wisoft.tutorial.command.DebitMoneyCommand;
import io.wisoft.tutorial.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate //jpa entity에 해당
public class AccountAggregate {

  @AggregateIdentifier //식별자
  private String id;

  private double accountBalance;

  private String currency;

  private String status;

  public AccountAggregate() {

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(double accountBalance) {
    this.accountBalance = accountBalance;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @CommandHandler
  public AccountAggregate(final CreateAccountCommand createAccountCommand) {
    AggregateLifecycle.apply(
        new AccountCreatedEvent(
            createAccountCommand.id,
            createAccountCommand.accountBalance,
            createAccountCommand.currency
        )
    );
  }

  @EventSourcingHandler
  protected void on(final AccountCreatedEvent accountCreatedEvent) {
    this.id = accountCreatedEvent.id;
    this.accountBalance = accountCreatedEvent.accountBalance;
    this.currency = accountCreatedEvent.currency;
    this.status = String.valueOf(Status.CREATED);

    AggregateLifecycle.apply(
        new AccountActivatedEvent(this.id, Status.ACTIVATED)
    );
  }

  @EventSourcingHandler
  protected void on(final AccountActivatedEvent accountActivatedEvent) {
    this.status = String.valueOf(accountActivatedEvent.status);
  }

  @CommandHandler
  protected void on(final CreditMoneyCommand creditMoneyCommand) {
    AggregateLifecycle.apply(
        new MoneyCreditedEvent(
            creditMoneyCommand.id,
            creditMoneyCommand.creditAmount,
            creditMoneyCommand.currency
        )
    );
  }

  @EventSourcingHandler
  protected void on(final MoneyCreditedEvent moneyCreditedEvent) {
    if (this.accountBalance < 0 && (this.accountBalance + moneyCreditedEvent.creditAmount) >= 0) {
      AggregateLifecycle.apply(
          new AccountActivatedEvent(
              moneyCreditedEvent.id, Status.ACTIVATED
          )
      );
    }

    this.accountBalance += moneyCreditedEvent.creditAmount;
  }

  @CommandHandler
  protected void on(final DebitMoneyCommand debitMoneyCommand) {
    AggregateLifecycle.apply(
        new MoneyDebitedEvent(
            debitMoneyCommand.id,
            debitMoneyCommand.debitAmount,
            debitMoneyCommand.currency
        )
    );
  }

  @EventSourcingHandler
  protected void on(final MoneyDebitedEvent moneyDebitedEvent) {
    if (this.accountBalance >= 0 && (this.accountBalance - moneyDebitedEvent.debitAmount) < 0) {
      AggregateLifecycle.apply(
          new AccountHeldEvent(this.id, Status.HELD)
      );

    }

    this.accountBalance -= moneyDebitedEvent.debitAmount;
  }

  @EventSourcingHandler
  protected void on(final AccountHeldEvent accountHeldEvent) {
    this.status = String.valueOf(accountHeldEvent.status);
  }
}
