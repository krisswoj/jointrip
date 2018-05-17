package pl.jointrip.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.domain.Account;
import pl.jointrip.services.AccountService;

@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String verifyRegisterData(@RequestParam String userNick, @RequestParam String userEmail){

        Account account = accountService.checkEmail(userEmail);

    }
}