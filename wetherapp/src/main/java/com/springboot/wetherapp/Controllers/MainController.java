package com.springboot.wetherapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.wetherapp.Mappers.WetherDataMapper;
import com.springboot.wetherapp.Services.MainService;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/home")
    public String home(@RequestParam(value = "location",required = false,defaultValue = "") String location ,Model model){

        System.out.println("home");
        WetherDataMapper data=mainService.getWetherReport(location);
        System.out.println(data);
        if(data==null){
            model.addAttribute("errorCode", 500);
            model.addAttribute("errorMessage", "Internal Server Error");
            return "error";
        }
        if(data.getError()!=null){
            model.addAttribute("errorCode", 400);
            model.addAttribute("errorMessage",data.getError());
            return "error";
        }
        model.addAttribute("data", data);
        return "home";
    }
    @GetMapping("/error")
    public String errorPage(Model model,@RequestParam(value="code",required = false,defaultValue = "400") int code,@RequestParam(value = "message",required = false,defaultValue = "Bad Request") String message){
        System.out.println(message+" "+code);
        model.addAttribute("errorCode", code);
        model.addAttribute("errorMessage", message);
        return "error";
    }
}
