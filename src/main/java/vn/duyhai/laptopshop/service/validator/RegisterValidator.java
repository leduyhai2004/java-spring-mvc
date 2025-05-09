package vn.duyhai.laptopshop.service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.duyhai.laptopshop.domain.dto.RegisterDTO;
import vn.duyhai.laptopshop.service.UserService;
@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {
    private final UserService userService;
    public RegisterValidator(UserService userService){
        this.userService = userService;
    }
    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // Check if password fields match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords must match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Additional validations can be added here
        
        //check email
        if(this.userService.checkEmailExist(user.getEmail())){
            context.buildConstraintViolationWithTemplate("Email has existed")
                .addPropertyNode("email")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
