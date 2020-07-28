package io.wisoft.tutorial.config;

import io.wisoft.tutorial.account.AccountAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

  @Bean
  EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository(final EventStore eventStore) {
    return EventSourcingRepository.builder(AccountAggregate.class).eventStore(eventStore).build();
  }
}
