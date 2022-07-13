package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import java.security.Principal;

@ThreadSafe
@Controller
public class CommentControl {
    private final PostService postService;
    private static final String TITLE_PAGE = "Форум";
    private static final String NAME_PAGE = TITLE_PAGE;
    private static final String NAME2_PAGE = "Новый комментарий";

    public CommentControl(PostService postService) {
        this.postService = postService;
    }

    private void page(Model model) {
        model.addAttribute("title_page", TITLE_PAGE);
        model.addAttribute("name_page", NAME_PAGE);
    }

    @GetMapping("/addCommToPost/{postId}")
    public String addCommToPost(@PathVariable("postId") int id, Model model) {
        page(model);
        model.addAttribute("name2_page", NAME2_PAGE);
        model.addAttribute("comment", new Comment());
        model.addAttribute("post", postService.findPostById(id));
        return "/addCommToPost";
    }

    @PostMapping("/saveCommentToPost/{postId}")
    public String saveCommentToPost(@PathVariable("postId") int id, @ModelAttribute Comment comment, Principal user) {
        Post post = postService.findPostById(id);
        postService.addCommentToPost(post, comment, user);
        return "redirect:/viewPost/{postId}";
    }
}
