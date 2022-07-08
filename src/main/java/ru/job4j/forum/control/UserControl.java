package ru.job4j.forum.control;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.MemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@ThreadSafe
@Controller
public class UserControl {
    private  final MemService users;

    public UserControl(MemService users) {
        this.users = users;
    }

    @GetMapping("/reg")
    public String formRegUser(Model model) {
        model.addAttribute("user", new User());
        return "/reg";
    }

    @PostMapping("/saveUser")
    public String createUser(@ModelAttribute User user) {
       Optional<User> regUser = users.findUserByEmail(user.geteMail());
        if (regUser.isPresent()) {
            return "redirect:/fail";
        }
        users.addUser(user);
        return "redirect:/success";
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        model.addAttribute("message", "Такой e-mail уже зарегистрирован");
        return "fail";
    }

    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("message", "Новый пользователь зарегистрирован");
        return "success";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "loginPage";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest request) {
       Optional<User> rsl = users.findUserByEmail(user.geteMail());
        if (rsl.isEmpty() || !rsl.get().getPassword().equals(user.getPassword())) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", rsl.get());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
