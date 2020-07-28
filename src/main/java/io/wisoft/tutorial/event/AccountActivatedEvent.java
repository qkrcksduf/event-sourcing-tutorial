package io.wisoft.tutorial.event;

public class AccountActivatedEvent extends BaseEvent<String> {

  public final Status status;

  public AccountActivatedEvent(String id, Status status) {
    super(id);
    this.status = status;
  }
}
