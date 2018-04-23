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

    @Autowired
    IAccountRepository accountRepository;

    public List<Account> findAll() {
        List<Account> retList = new ArrayList<>();
        for(Account acc : accountRepository.findAll()){
            retList.add(acc);
        }
        return retList;
    }

    public void addAccount(Account acc){
        accountRepository.save(acc);
    }

    public Account getById(long id){
        Account acc = accountRepository.findById(id);
        return acc;
    }

}
