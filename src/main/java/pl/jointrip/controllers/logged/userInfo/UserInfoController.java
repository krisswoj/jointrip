package pl.jointrip.controllers.logged.userInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.jointrip.dao.DocumentsRepository;
import pl.jointrip.models.entities.user.Role;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.models.system.SystemNotification;
import pl.jointrip.models.viewModels.documents.DocumentsApprovalViewModel;
import pl.jointrip.services.documentsService.DocumentsService;
import pl.jointrip.services.userService.UserService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class UserInfoController {

    private UserService userService;
    private DocumentsRepository documentsRepository;
    private DocumentsService documentsService;

    @Value("${USER_ACCEPT_POSITIVE}")
    private String acceptUserPositive;

    @Value("${USER_ACCEPT_NEGATIVE}")
    private String acceptUserNegative;

    @Autowired
    public UserInfoController(UserService userService, DocumentsRepository documentsRepository, DocumentsService documentsService) {
        this.userService = userService;
        this.documentsRepository = documentsRepository;
        this.documentsService = documentsService;
    }

    @GetMapping(value = "/user/edit")
    public ModelAndView fetchUserEdit() {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getLoggedUser();
        modelAndView.addObject("userEdit", user);
        modelAndView.setViewName("user/edit");
        return modelAndView;
    }

    @PostMapping(value = "/user/edit")
    public ModelAndView editUser(@Valid User user) {
        User userToEdit = userService.getLoggedUser();
        userToEdit.setName(user.getName());
        userToEdit.setLastName(user.getLastName());
        userService.editUser(userToEdit);
        ModelAndView modelAndView = new ModelAndView("redirect:/user/info");
        return modelAndView;
    }

    @GetMapping(value = "/user/info")
    public ModelAndView fetchUserInfo() {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getLoggedUser();
        Role role = new ArrayList<Role>(user.getRoles()).get(0);
        String roleName;
        if(role.getRole().equals("NOTVERIFIEDUSER")){
            roleName = "Użytkownik niezweryfikowany";
        } else if(role.getRole().equals("USER")){
            roleName = "Użytkownik";
        } else {
            roleName = "Administrator";
        }
        modelAndView.addObject("userInfo", user);
        modelAndView.addObject("role", roleName);
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

    private ModelAndView adminUserInfoUserFilesFileForm(int id) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(id);
        modelAndView.addObject("userInfo", user);
        modelAndView.addObject("userFiles", documentsRepository.findAllByUserIdAndFilestatus(user, 1));
        return modelAndView;
    }

    @GetMapping(value = "/admin/user-info", params = "id")
    public ModelAndView fetchUser(@RequestParam("id") int userId) {
        ModelAndView modelAndView = adminUserInfoUserFilesFileForm(userId);
        modelAndView.setViewName("admin/user-info");
        return modelAndView;
    }

    @GetMapping(value = "/admin/user-info/accept", params = "id")
    public ModelAndView acceptUser(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(id);
        boolean result = userService.changeUserRole(id, "USER");
        SystemNotification systemNotification = result ? new SystemNotification("true", acceptUserPositive) : new SystemNotification("fail", acceptUserNegative);
        modelAndView.addObject("message", systemNotification);
        modelAndView.addObject("userInfo", user);
        modelAndView.addObject("userFiles", documentsRepository.findAllByUserIdAndFilestatus(user, 1));
        modelAndView.setViewName("admin/user-info");
        return modelAndView;
    }


}
