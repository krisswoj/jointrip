package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import pl.jointrip.domain.Account;

public interface IAccountRepository extends CrudRepository<Account, Integer> {

}