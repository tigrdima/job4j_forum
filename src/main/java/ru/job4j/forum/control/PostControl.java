package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;
import java.security.Principal;

@ThreadSafe
@Controller
public class PostControl {
    private final PostService postService;
    private final UserService userService;
    private static final String TITLE_PAGE = "Форум";
    private static final String NAME_PAGE = TITLE_PAGE;
    private static final String NAME2_PAGE = "Новая тема";
    private static final String NAME3_PAGE = "Редактирование темы";

    public PostControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    private void page(Model model) {
        model.addAttribute("title_page", TITLE_PAGE);
        model.addAttribute("name_page", NAME_PAGE);
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model) {
        page(model);
        model.addAttribute("name2_page", NAME2_PAGE);
        model.addAttribute("post", new Post());
        return "/addPost";
    }

    @PostMapping({"/save", "/updatePost"})
    public String save(@ModelAttribute Post post, Principal user) {
        postService.savePost(post, user);
        return "redirect:/";
    }

    @GetMapping("/viewPost/{postId}")
    public String viewPost(Model model, @PathVariable("postId") int id, Principal user) {
        page(model);
        model.addAttribute("post", postService.findPostById(id));
        if (user != null) {
            model.addAttribute("regUser", userService.findUser(user.getName()).get());
            return "/viewPost";
        }
        model.addAttribute("regUser", null);
        return "/viewPost";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id) {
        page(model);
        Post post = postService.findPostById(id);
        model.addAttribute("name3_page", NAME3_PAGE);
        model.addAttribute("post", post);
        return "/formUpdatePost";
    }
}
