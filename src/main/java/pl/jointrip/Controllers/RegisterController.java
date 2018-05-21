package pl.jointrip.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.jointrip.domain.Account;
import pl.jointrip.services.AccountService;

@Controller
@SessionAttributes("employee")
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm() {
        return "regggg";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String verifyRegisterData(@ModelAttribute("employee") BindingResult result, SessionStatus status, Account account) {

        //Validation code start
        boolean error = false;

        System.out.println(account); //Verifying if information is same as input by user

        if(account.getName().isEmpty()){
            result.rejectValue("userNick", "error.userNick");
            error = true;
        }

        if(account.getEmail().isEmpty()){
            result.rejectValue("userEmail", "error.userEmail");
            error = true;
        }

        if(account.getPassword().isEmpty()){
            result.rejectValue("userPassword", "error.userPassword");
            error = true;
        }

        if(error) {
            return "addUser";
        }

        status.setComplete();
        return "redirect:/success";

    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(Model model)
    {
        return "addSuccess";
    }


}