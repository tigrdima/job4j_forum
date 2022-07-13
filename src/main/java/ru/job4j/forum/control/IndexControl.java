package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;
import java.security.Principal;

@ThreadSafe
@Controller
public class IndexControl {
    private final PostService postService;
    private final UserService userService;
    private static final String TITLE_PAGE = "Форум";
    private static final String NAME_PAGE = TITLE_PAGE;

    public IndexControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    private void page(Model model) {
        model.addAttribute("title_page", TITLE_PAGE);
        model.addAttribute("name_page", NAME_PAGE);
    }

    @GetMapping({"/", "/index"})
    public String index(Model model, Principal user) {
        page(model);
        model.addAttribute("posts", postService.findAllPosts());
        if (user != null) {
            model.addAttribute("regUser", userService.findUser(user.getName()).get());
            return "index";
        }
        model.addAttribute("regUser", null);
        return "index";
    }
}
