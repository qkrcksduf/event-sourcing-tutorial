package io.wisoft.tutorial.account;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleAccountQueryService implements AccountQueryService {

  private final EventStore eventStore;
  private final AccountRepository accountRepository;

  public SimpleAccountQueryService(final EventStore eventStore, AccountRepository repository) {
    this.eventStore = eventStore;
    this.accountRepository = repository;
  }

  @Override
  public List<Object> listEventsForAccount(final String accountNumber) {
    return eventStore.readEvents(accountNumber)
        .asStream()
        .map(Message::getPayload)
        .collect(Collectors.toList());
  }

  @Override
  public AccountQueryEntity getAccount(String account) {
    return accountRepository.findById(account).get();
  }
}
