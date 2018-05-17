package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.domain.Account;

@Repository
public interface IAccountRepository extends CrudRepository<Account, Integer> {

    Account findById(long id);

}