package com.dionext.dioportal.controllers._04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "04/index";
    }

    @GetMapping("/addUserModal")
    public String addUser() {
        return "04/modal :: modal";
    }

    @GetMapping("/getFakeUser")
    public String getFakeUser(Model model) {
        var user = userRepository.createFakeUser();
        model.addAttribute("user",user);
        return "04/new-user-form :: frmNewUser";
    }

    @PostMapping("")
    public String save(@RequestBody User user, Model model) {
        // add new user
        return "04/new-user-row :: row";
    }

}
