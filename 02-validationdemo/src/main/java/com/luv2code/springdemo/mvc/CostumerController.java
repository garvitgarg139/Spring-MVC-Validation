package com.luv2code.springdemo.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CostumerController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/showForm")
    public String showForm(Model theModel){
        theModel.addAttribute("costumer",new Costumer());
        return "costumer-form";
    }

    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("costumer") Costumer theCostumer,
            BindingResult theBindingResult){

        System.out.println("Binding Results "+theBindingResult.toString());
        if(theBindingResult.hasErrors()){
            return "costumer-form";
        }
        else{
            return"confirmation-page";
        }
    }
}
