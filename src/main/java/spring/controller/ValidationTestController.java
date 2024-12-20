package spring.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.domain.ValidationDTO;
import spring.service.ValidationService;

@RestController

public class ValidationTestController {
    @PostMapping("/valid")
    public Object addItem(@RequestBody @Validated ValidationService form, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return form;
    }
}
