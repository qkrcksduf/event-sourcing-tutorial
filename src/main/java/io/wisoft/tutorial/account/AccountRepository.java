package io.wisoft.tutorial.account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
