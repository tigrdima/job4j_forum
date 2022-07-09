package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class CommentControl {
    private final PostService posts;

    public CommentControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping("/addCommToPost/{postId}")
    public String addCommToPost(@PathVariable("postId") int id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/loginPage";
        }
        model.addAttribute("comment", new Comment());
        model.addAttribute("post", posts.findPostById(id));
        return "/addCommToPost";
    }

    @PostMapping("/saveCommentToPost/{postId}")
    public String saveCommentToPost(@PathVariable("postId") int id, @ModelAttribute Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Post post = posts.findPostById(id);
        posts.addCommentToPost(post, comment, user);
        return "redirect:/viewPost/{postId}";
    }
}
