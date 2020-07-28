package io.wisoft.tutorial.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {

  @TargetAggregateIdentifier
  public final T id;

  public BaseCommand(final T id) {
    this.id = id;
  }
}
