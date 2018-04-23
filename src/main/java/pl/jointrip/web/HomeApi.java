package pl.jointrip.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.jointrip.dao.IAccountRepository;
import pl.jointrip.domain.Account;

@RestController
public class HomeApi {

    @Autowired
    private IAccountRepository accountRepository;

    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Account> getAllUsers() {
        // This returns a JSON or XML with the users
        return accountRepository.findAll();
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUndead(@RequestBody Account acc) {
        accountRepository.save(acc);
    }

}
