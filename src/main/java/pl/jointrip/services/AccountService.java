package pl.jointrip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jointrip.dao.IAccountRepository;
import pl.jointrip.domain.Account;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountService {

    private Connection connection;


    /*public AccountService() throws SQLException{

        this.connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
        if (!isReady()) {
            createTables();
        }
        this.setConnection(this.connection);
    }*/

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

    public String checkNick(String nick){
        return null;
    };

    public String checkEmail(String email){
        return null;
    };

}
