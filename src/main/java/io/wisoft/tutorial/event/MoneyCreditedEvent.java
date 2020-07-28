package io.wisoft.tutorial.event;

public class MoneyCreditedEvent extends BaseEvent<String> {

  public final double creditAmount;
  private final String currency;


  public MoneyCreditedEvent(String id, double creditAmount, String currency) {
    super(id);
    this.creditAmount = creditAmount;
    this.currency = currency;
  }
}
