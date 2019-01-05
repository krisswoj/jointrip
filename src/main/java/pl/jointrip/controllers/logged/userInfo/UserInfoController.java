package pl.jointrip.controllers.logged.userInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.DocumentsRepository;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.models.viewModels.documents.DocumentsApprovalViewModel;
import pl.jointrip.services.documentsService.DocumentsService;
import pl.jointrip.services.userService.UserService;

import javax.validation.Valid;

@Controller
public class UserInfoController {

    private UserService userService;
    private DocumentsRepository documentsRepository;
    private DocumentsService documentsService;

    @Autowired
    public UserInfoController(UserService userService, DocumentsRepository documentsRepository, DocumentsService documentsService) {
        this.userService = userService;
        this.documentsRepository = documentsRepository;
        this.documentsService = documentsService;
    }

    @GetMapping(value = "/user/edit")
    public ModelAndView fetchUserEdit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userEdit", new User());
        modelAndView.setViewName("user/edit");
        return modelAndView;
    }

    @PostMapping(value = "/user/edit")
    public ModelAndView editUser(@Valid User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userEdit", new User());
        User userToEdit = userService.getLoggedUser();
        userToEdit.setName(user.getName());
        userToEdit.setLastName(user.getLastName());
        modelAndView.setViewName("user/edit");
        return modelAndView;
    }

    @GetMapping(value = "/user/info")
    public ModelAndView fetchUserInfo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.setViewName("user/user-profile-info");
        return modelAndView;
    }

    @GetMapping(value = "/user/password")
    public ModelAndView changeUserPassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.setViewName("user/user-profile-password");
        return modelAndView;
    }

    @PostMapping(value = "/user/password")
    public ModelAndView changeUserPasswordForm(@ModelAttribute("old-password") String oldPassword,
                                               @ModelAttribute("new-password") String newPassword,
                                               @ModelAttribute("new-password-2") String newPasswordTwo) {
        ModelAndView modelAndView = new ModelAndView();
        userService.changeUserPassword(oldPassword, newPassword, newPasswordTwo);
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.setViewName("user/user-profile-password");
        return modelAndView;
    }

    @GetMapping(value = "/user/files")
    public ModelAndView uploadUserFiles() {
        ModelAndView modelAndView = userInfoUserFilesFileForm();
        modelAndView.setViewName("user/user-profile-upload-files");
        return modelAndView;
    }

    @PostMapping(value = "user/files")
    public ModelAndView uploadFileUserForm(@ModelAttribute("fileForm") DocumentsApprovalViewModel documentsApprovalViewModel) {
        documentsApprovalViewModel.setLoggedUser(userService.getLoggedUser());
        documentsApprovalViewModel.setStatus(1);
        ModelAndView modelAndView = userInfoUserFilesFileForm();
        modelAndView.addObject("message", documentsService.saveDocument(documentsApprovalViewModel));
        modelAndView.setViewName("user/user-profile-upload-files");
        return modelAndView;
    }

    private ModelAndView userInfoUserFilesFileForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfo", userService.getLoggedUser());
        modelAndView.addObject("userFiles", documentsRepository.findAllByUserIdAndFilestatus(userService.getLoggedUser(), 1));
        modelAndView.addObject("fileForm", new DocumentsApprovalViewModel());
        return modelAndView;
    }
}
