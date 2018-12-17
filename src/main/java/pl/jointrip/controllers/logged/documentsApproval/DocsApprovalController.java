package pl.jointrip.controllers.logged.documentsApproval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.models.entities.documents.Documentstore;
import pl.jointrip.models.viewModels.documents.DocumentsApprovalViewModel;
import pl.jointrip.services.documentsService.DocumentsService;
import pl.jointrip.services.userService.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        Boolean result = new Boolean(false);
        if (viewModel.getFile().getSize() > 0) {
            viewModel.setLoggedUser(userService.getLoggedUser());
            result = documentsService.saveDocument(viewModel);
        }
        modelAndView.addObject("result", result);
        modelAndView.addObject("doc", documentsService.findUserDocuments(userService.getLoggedUser()));
        modelAndView.setViewName("user/docsApproval");
        return modelAndView;
    }

    @GetMapping(value = {"/user/docsApprovalDownload"})
    public void downloadDocument(@RequestParam("id") int docId, HttpServletResponse response) throws IOException {
        Documentstore document = documentsService.findById(docId);
        response.setContentType(document.getContentType());
        response.setContentLength(document.getFile().length);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getFilename() + "\"");
        FileCopyUtils.copy(document.getFile(), response.getOutputStream());
    }
}
