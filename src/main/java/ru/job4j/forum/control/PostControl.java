package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class PostControl {
    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    private void userSession(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
    }

    private boolean ifUserNull(HttpSession session) {
        return session.getAttribute("user") == null;
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model, HttpSession session) {
        if (ifUserNull(session)) {
            return "redirect:/loginPage";
        }
        model.addAttribute("post", new Post());
        return "/addPost";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post, HttpSession session) {
        saveOrUpdate(post, session);
        return "redirect:/";
    }

    @GetMapping("/viewPost/{postId}")
    public String viewPost(Model model, @PathVariable("postId") int id, HttpSession session) {
        userSession(model, session);
        Post post = posts.findPostById(id);
        model.addAttribute("post", post);
        return "/viewPost";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id, HttpSession session) {
        if (ifUserNull(session)) {
            return "redirect:/loginPage";
        }
        Post post = posts.findPostById(id);
        model.addAttribute("post", post);
        return "/formUpdatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post, HttpSession session) {
        saveOrUpdate(post, session);
        return "redirect:/";
    }

    private void saveOrUpdate(@ModelAttribute Post post, HttpSession session) {
        posts.savePost(post, (User) session.getAttribute("user"));
    }
}
