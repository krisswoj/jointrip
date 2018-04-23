package pl.jointrip.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.jointrip.dao.IAccountRepository;
import pl.jointrip.domain.Account;
import pl.jointrip.services.AccountService;

@RestController
public class HomeApi {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Account> getAllUsers() {
        // This returns a JSON or XML with the users
        return accountService.findAll();
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUndead(@RequestBody Account acc) {
        accountService.addAccount(acc);
    }

}
