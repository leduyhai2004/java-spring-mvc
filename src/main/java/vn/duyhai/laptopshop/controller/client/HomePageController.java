package vn.duyhai.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import vn.duyhai.laptopshop.domain.Product;
import vn.duyhai.laptopshop.domain.User;
import vn.duyhai.laptopshop.domain.dto.RegisterDTO;
import vn.duyhai.laptopshop.service.ProductService;
import vn.duyhai.laptopshop.service.UserService;

@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public HomePageController(ProductService productService,UserService userService,PasswordEncoder passwordEncoder){
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = productService.fetchProducts();
        model.addAttribute("products",products);
        return "client/homepage/show";
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }
    @PostMapping("/register")
    public String handleRegisterPage(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "client/auth/register";
        }
        
        User user = this.userService.registerDTOtoUser(registerDTO);
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        // save
        this.userService.handleSaveUser(user);
        return "client/auth/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        
        return "client/auth/login";
    }

    @GetMapping("/access-deny")
    public String getDenyPage(Model model) {
        
        return "client/auth/deny";
    }

}
