package spring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {
    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("jryang","28");
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);

        return "success";
    }
    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request) {
        Cookie[] list = request.getCookies();

        for (Cookie cookie : list) {
            if (cookie.getName().equals("jryang")) {
                return "response"+cookie.getValue();
            }
        }
        return "NO cookies";
    }
}
