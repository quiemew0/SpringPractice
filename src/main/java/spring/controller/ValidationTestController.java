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
//@RequiredArgsConstructor어노테이션은 클래스에 선언된 final 변수들, 필드들을 매개변수로 하는 생성자를 자동으로 생성해주는 어노테이션
//@RequiredArgsConstructor //초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줌

public class ValidationTestController {
    @PostMapping("/valid")
    public Object addItem(@RequestBody @Validated ValidationService form, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return form;
    }
}
