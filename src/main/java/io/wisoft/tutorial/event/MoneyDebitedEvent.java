package io.wisoft.tutorial.event;

public class MoneyDebitedEvent extends BaseEvent<String> {

  public final double debitAmount;
  private final String currency;


  public MoneyDebitedEvent(String id, double debitAmount, String currency) {
    super(id);
    this.debitAmount = debitAmount;
    this.currency = currency;
  }
}
