package bg.sotfuni.cookies.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {
    public static final String LANG_COOKIE_NAME = "lang";

    @GetMapping("/cookies")
    public String cookies(@CookieValue(
            name=LANG_COOKIE_NAME,
            defaultValue = "en") String langFromCookie, Model model){

        model.addAttribute("langTL", langFromCookie);

        return "cookies";
    }

    @PostMapping("/cookies")
    public String submitCookies(
            @RequestParam (name="langHTML") String langFromHTMLForm,
                                HttpServletResponse response){
        Cookie langCookie = new Cookie(LANG_COOKIE_NAME, langFromHTMLForm);
        response.addCookie(langCookie);

        System.out.println("User choose: "+ langFromHTMLForm);
        return "redirect:/cookies";
    }

}
