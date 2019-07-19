package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PetsRepository PetsRepository;

    @RequestMapping("/")
    public String list(Model model){
        model.addAttribute("pets", PetsRepository.findAll());
        return "list";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/add")
    public String petsForm(Model model){
        model.addAttribute("pet", new Pet ());
        return "petsform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Pet pet,
                              BindingResult result, Model model){
        if (result.hasErrors()){
            return "petsform";
        }
        PetsRepository.save(pet);
        return "redirect:/ ";

    }
    @RequestMapping ("/detail/{id}")
    public String showPets (@PathVariable("id") long id, Model model)

    {
        model.addAttribute("pet", PetsRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updatePets(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("pet", PetsRepository.findById(id).get());
        return "petsform";
    }
    @RequestMapping("/delete/{id}")
    public String delNotes(@PathVariable("id") long id){
        PetsRepository.deleteById(id);

        return "redirect:/";

    }
}
