package pl.jointrip.controllers.logged.userInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.Documentstore;
import pl.jointrip.models.User;
import pl.jointrip.services.documentsService.DocumentsService;
import pl.jointrip.services.userService.UserService;

@Controller
public class DocsApprovalController {
    @Autowired
    UserService userService;
    @Autowired
    DocumentsService documentsService;

    @GetMapping(value = "/user/docsApproval")
    public ModelAndView fetchUserEdit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("doc", new Documentstore());
        modelAndView.setViewName("user/docsApproval");
        return modelAndView;
    }
//    @PostMapping(value = "/user/docsApproval")
//    public ModelAndView editUser(@Valid Documentstore doc) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("doc", new Documentstore());
//        User loggedUser = userService.getLoggedUser();
//
//        modelAndView.setViewName("user/docsApproval");
//        return modelAndView;
//    }
    @PostMapping(value = "/user/docsApproval")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("doc", new Documentstore());
        User loggedUser = userService.getLoggedUser();
        byte[] content = documentsService.handleUploadFile(file);
        boolean result = documentsService.saveDocument(content, loggedUser, file.getOriginalFilename(), file.getContentType());

        modelAndView.setViewName("user/docsApproval");
        return modelAndView;
    }
}
