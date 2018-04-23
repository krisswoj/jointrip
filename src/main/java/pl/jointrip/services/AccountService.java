package pl.jointrip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jointrip.dao.IAccountRepository;
import pl.jointrip.domain.Account;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountService {


    private IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAll() {

        List<Account> accountList = new ArrayList<>();

        for (Account account : accountRepository.findAll()) {
            accountList.add(account);
        }
        return accountList;
    }
}
