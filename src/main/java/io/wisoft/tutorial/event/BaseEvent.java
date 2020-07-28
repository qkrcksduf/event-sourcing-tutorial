package io.wisoft.tutorial.event;

public class BaseEvent <T> {

  public final T id;


  public BaseEvent(final T id) {
    this.id = id;
  }

}
