package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

;

@Controller
public class UserController {
    private final  UserService userService;
    private final UploadService uploadService;
    private final  PasswordEncoder passwordEncoder;
    // private final UserRepository userRepository;
    public UserController(UserService userService ,UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
       this.uploadService = uploadService;
       this.passwordEncoder = passwordEncoder;
    } // dependency injection

    // @RequestMapping("/")
    // public String getHomePage(Model model) {
    //     List<User> arrUser = this.userService.getAllUser();
    //     System.out.println(arrUser);
    //     model.addAttribute("duyhai", new User());
    //     return "hello";
    //     // return "admin/user/table-user";
    // }
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUser();
        model.addAttribute("users1", users);
        return "admin/user/show"; 
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model,@PathVariable long id) {
        model.addAttribute("id", id);
        User user = this.userService.getUserById(id);
        model.addAttribute("user",user);
        return "admin/user/detail";
    }
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model,@PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @GetMapping("/admin/user/create") //GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create"; // Trả về một giao diện kh cần đuôi jsp vì đã cấu hình
    }

    @PostMapping(value = "/admin/user/create")
    public String createUser1(Model model, @ModelAttribute("newUser") User duyhai, @RequestParam("hoidanitFile") MultipartFile file) {
        // return "hello"; // Trả về một giao diện kh cần đuôi jsp vì đã cấu hình

        String avatar = this.uploadService.handleSaveUploadFile(file,"avatar");
        String hashPassword = this.passwordEncoder.encode(duyhai.getPassword());
        duyhai.setAvatar(avatar);
        duyhai.setPassword(hashPassword);
        duyhai.setRole(this.userService.getRoleByName(duyhai.getRole().getName()));
        this.userService.handleSaveUser(duyhai);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/update") //GET
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User duyhai) {
        User currentUser = this.userService.getUserById(duyhai.getId());
        if(currentUser != null){
            currentUser.setFullName(duyhai.getFullName());
            currentUser.setAddress(duyhai.getAddress());
            currentUser.setPhone(duyhai.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user"; // Trả về một giao diện kh cần đuôi jsp vì đã cấu hình
    }

    @GetMapping("/admin/user/delete/{id}")
    public String deleteUserPage(Model model,@PathVariable long id) {
        model.addAttribute("id",id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model,@ModelAttribute("newUser") User duyhai) {
        this.userService.deleteAUser(duyhai.getId());
        return "redirect:/admin/user"; 
    }

  
    
}

