package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.security.Principal;
import java.util.Optional;

@ThreadSafe
@Controller
public class AccountUserControl {
    private final UserService userService;
    private final PostService postService;
    private static final String TITLE_PAGE = "Форум";
    private static final String NAME_PAGE = "Форум / user account";
    private static final String NAME1_PAGE = "Изменение пароля";

    public AccountUserControl(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    private void page(Model model, Principal regUser) {
        model.addAttribute("title_page", TITLE_PAGE);
        model.addAttribute("name_page", NAME_PAGE);
        model.addAttribute("regUser", userService.findUser(regUser.getName()).get());
    }

    @GetMapping("/user/accountUser/{userId}")
    public String accountUser(@PathVariable("userId") int id,  Model model, Principal regUser) {
        page(model, regUser);
        model.addAttribute("user", userService.findUserById(id).get());
        return "/user/accountUser";
    }

    @PostMapping("/user/editEmailUser/{userId}")
    public String editEmailUser(@RequestParam String eMail, @PathVariable("userId") int id) {
       Optional<User> regUser = userService.findUser(eMail);
        if (regUser.isPresent()) {
            return "redirect:/user/accountUser/{userId}";
        }
        User user = userService.findUserById(id).get();
        user.seteMail(eMail);
        userService.save(user);
        return "redirect:/logout";
    }

    @GetMapping("/user/allPostsByUser/{regUserId}")
    public String allPostsByUser(@PathVariable("regUserId") int id, Model model, Principal regUser) {
        page(model, regUser);
        model.addAttribute("posts", postService.findAllPostsByUserId(id));
        return "/user/allPostsByUser";
    }

    @GetMapping("/user/editPwdUser/{userId}")
    public String editPwdUser(@PathVariable("userId") int id, Model model) {
        model.addAttribute("title_page", TITLE_PAGE);
        model.addAttribute("name_page", NAME_PAGE);
        model.addAttribute("name1_page", NAME1_PAGE);
        model.addAttribute("userId", id);
        return "/user/editPwdUser";
    }
}
