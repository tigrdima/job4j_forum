package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorityService;
import ru.job4j.forum.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@ThreadSafe
@Controller
public class UserControl {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;
    private static final String TITLE_PAGE = "Форум";
    private static final String NAME_PAGE = TITLE_PAGE;
    private static final String NAME2_PAGE = "Регистрация пользователя";
    private static final String NAME3_PAGE = "Ошибка регистрации";
    private static final String NAME4_PAGE = "Успешно";
    private static final String NAME5_PAGE = "Ведите логин и пароль";
    private static final String MESSAGE_FAIL = "Такой e-mail уже зарегистрирован";
    private static final String MESSAGE_SUCCESS = "Новый пользователь зарегистрирован";
    private static final String MESSAGE_LOGIN_ERROR = "Логин или пароль введены не верно";
    private static final String MESSAGE_LOGOUT = "Вы успешно вышли из системы";

    public UserControl(UserService userService, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    private void page(Model model) {
        model.addAttribute("title_page", TITLE_PAGE);
        model.addAttribute("name_page", NAME_PAGE);
    }

    @GetMapping("/reg")
    public String formRegUser(Model model) {
        page(model);
        model.addAttribute("name2_page", NAME2_PAGE);
        model.addAttribute("user", new User());
        return "/reg";
    }

    @PostMapping("/saveUser")
    public String createUser(@ModelAttribute User user) {
        Optional<User> regUser = userService.findUser(user.geteMail());
        if (regUser.isEmpty()) {
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAuthority(authorityService.findAuthority("ROLE_USER").get());
            userService.addUser(user);
            return "redirect:/success";
        }
        return "redirect:/fail";
    }

    @PostMapping("/user/savePwdUser/{userId}")
    public String editPwdUser(@PathVariable("userId") int id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        Optional<User> regUser = userService.findUserById(id);
        if (regUser.isPresent() && passwordEncoder.matches(oldPassword, regUser.get().getPassword())) {
            User user = regUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.save(user);
            return "redirect:/login";
        }
        return "redirect:/user/editPwdUser/{userId}";
    }

    @PostMapping("/admin/resPwdUser/{userId}")
    public String resPwdUser(@PathVariable("userId") int id) {
        User user = userService.findUserById(id).get();
        user.setPassword("$2a$10$ACCX0je33hcWhelJ7dvdN..18QPrWp/oVIi/PA3MofN3XlMFMlz5a");
        userService.save(user);
        return "redirect:/admin/viewUser/{userId}";
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        page(model);
        model.addAttribute("name3_page", NAME3_PAGE);
        model.addAttribute("message", MESSAGE_FAIL);
        return "fail";
    }

    @GetMapping("/success")
    public String success(Model model) {
        page(model);
        model.addAttribute("name4_page", NAME4_PAGE);
        model.addAttribute("message", MESSAGE_SUCCESS);
        return "success";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = MESSAGE_LOGIN_ERROR;
        }
        if (logout != null) {
            errorMessage = MESSAGE_LOGOUT;
        }
        page(model);
        model.addAttribute("name5_page", NAME5_PAGE);
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}
