package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.service.AuthorityService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;
import java.security.Principal;

@ThreadSafe
@Controller
public class AdminControl {
    private final UserService userService;
    private final PostService postService;
    private final AuthorityService authorityService;
    private static final String TITLE_PAGE = "Форум";
    private static final String NAME_PAGE = "Форум / admin";

    public AdminControl(UserService userService, PostService postService, AuthorityService authorityService) {
        this.userService = userService;
        this.postService = postService;
        this.authorityService = authorityService;
    }

    private void page(Model model, Principal regUser) {
        model.addAttribute("title_page", TITLE_PAGE);
        model.addAttribute("name_page", NAME_PAGE);
        model.addAttribute("regUser", userService.findUser(regUser.getName()).get());
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model, Principal regUser) {
        page(model, regUser);
        model.addAttribute("users", userService.findAllUsers());
        return "admin/admin";
    }

    @GetMapping("/admin/viewUser/{userId}")
    public String viewUser(Model model, @PathVariable("userId") int id, Principal regUser, @ModelAttribute Authority authority) {
        page(model, regUser);
        model.addAttribute("authorities", authorityService.findAllAuthorities());
        model.addAttribute("user", userService.findUserById(id).get());
        return "/admin/viewUser";
    }

    @GetMapping("/admin/allPostsByUser/{userId}")
    public String allPostsByUser(@PathVariable("userId") int id, Model model, Principal regUser) {
        page(model, regUser);
        model.addAttribute("posts", postService.findAllPostsByUserId(id));
        return "/admin/allPostsByUser";
    }

    @GetMapping("/admin/viewPost/{postId}")
    public String viewPost(Model model, @PathVariable("postId") int id, Principal regUser) {
        page(model, regUser);
        model.addAttribute("post", postService.findPostById(id));
        return "/admin/viewPost";
    }

    @PostMapping("/admin/deletePost/{postId}")
    public String deletePost(@PathVariable("postId") int id) {
        postService.deletePost(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/enabledUser/{userId}")
    public String enabledUser(@PathVariable("userId") int id) {
        userService.enabledUser(id);
        return "redirect:/admin/viewUser/{userId}";
    }

    @PostMapping("/admin/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/editAuthorityUser/{userId}")
    public String editAuthorityUser(@PathVariable("userId") int userId, @RequestParam String authorityId) {
        userService.editAuthorityUser(userId, Integer.parseInt(authorityId));
        return "redirect:/admin/viewUser/{userId}";
    }
}
