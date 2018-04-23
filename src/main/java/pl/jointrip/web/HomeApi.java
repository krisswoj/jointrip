package pl.jointrip.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jointrip.dao.IAccountRepository;
import pl.jointrip.domain.Account;

@RestController
public class HomeApi {

    @Autowired
    private IAccountRepository accountRepository;

    @RequestMapping("/")
    public String index(){
        return "This is non rest, just checking if everything works";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Account> getAllUsers() {
        // This returns a JSON or XML with the users
        return accountRepository.findAll();
    }
}
