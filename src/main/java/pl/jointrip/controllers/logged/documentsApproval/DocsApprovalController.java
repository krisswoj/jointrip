package pl.jointrip.controllers.logged.documentsApproval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.viewModels.documents.DocumentsApprovalViewModel;
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
        modelAndView.addObject("doc", documentsService.findUserDocuments(userService.getLoggedUser()));
        modelAndView.setViewName("user/docsApproval");
        return modelAndView;
    }

    @PostMapping(value = "/user/docsApproval")
    public ModelAndView handleFileUpload(DocumentsApprovalViewModel viewModel) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("doc", new DocumentsApprovalViewModel());
        viewModel.setLoggedUser(userService.getLoggedUser());
        boolean result = documentsService.saveDocument(viewModel);
        modelAndView.setViewName("user/docsApproval");
        return modelAndView;
    }
}
