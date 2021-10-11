package project.nextleveltechnology.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    protected boolean isLogged(HttpServletRequest request){

        var userId = request.getSession().getAttribute("userId");

        return userId !=null;

    }
}
