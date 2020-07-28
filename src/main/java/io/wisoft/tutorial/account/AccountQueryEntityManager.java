package io.wisoft.tutorial.account;

import io.wisoft.tutorial.event.BaseEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AccountQueryEntityManager {

  private final AccountRepository accountRepository;
  private final EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository;


  public AccountQueryEntityManager(AccountRepository accountRepository,
                                   @Qualifier("accountAggregateEventSourcingRepository")
                                       EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository) {
    this.accountRepository = accountRepository;
    this.accountAggregateEventSourcingRepository = accountAggregateEventSourcingRepository;
  }

  @EventSourcingHandler
  void on(final BaseEvent baseEvent) {
    persistAccount(buildQueryAccount(getAccountFromEvent(baseEvent)));
  }

  private AccountAggregate getAccountFromEvent(BaseEvent baseEvent) {
    return accountAggregateEventSourcingRepository.load(baseEvent.id.toString())
        .getWrappedAggregate()
        .getAggregateRoot();
  }

  private AccountQueryEntity buildQueryAccount(final AccountAggregate accountAggregate) {
    final AccountQueryEntity accountQueryEntity = findExistingOrCreateQueryAccount(accountAggregate.getId());

    accountQueryEntity.setId(accountAggregate.getId());
    accountQueryEntity.setAccountBalance(accountAggregate.getAccountBalance());
    accountQueryEntity.setCurrency(accountAggregate.getCurrency());
    accountQueryEntity.setStatus(accountAggregate.getStatus());

    return accountQueryEntity;
  }

  private AccountQueryEntity findExistingOrCreateQueryAccount(String id) {
    return accountRepository.findById(id).isPresent() ? accountRepository.findById(id).get()
        : new AccountQueryEntity();
  }

  private void persistAccount(final AccountQueryEntity accountQueryEntity) {
    accountRepository.save(accountQueryEntity);
  }
}
