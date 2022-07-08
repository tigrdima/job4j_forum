package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.MemService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class IndexControl {
    private final MemService posts;

    public IndexControl(MemService posts) {
        this.posts = posts;
    }

    private void userSession(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
    }

    @GetMapping({"/", "/index"})
    public String index(Model model, HttpSession session) {
        userSession(model, session);
        model.addAttribute("posts", posts.getAllPosts());
        return "index";
    }
}
