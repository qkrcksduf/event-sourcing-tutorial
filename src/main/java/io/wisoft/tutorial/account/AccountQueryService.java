package io.wisoft.tutorial.account;

import java.util.List;

public interface AccountQueryService {

  List<Object> listEventsForAccount(final String accountNumber);

  AccountQueryEntity getAccount(final String account);

}
