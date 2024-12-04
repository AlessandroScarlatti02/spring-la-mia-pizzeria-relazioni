package it.lessons.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.lessons.pizzeria.model.Ingredient;
import it.lessons.pizzeria.repository.IngredientRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    IngredientRepository ingredientRepo;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("ingredients", ingredientRepo.findAll());
        model.addAttribute("newIngredient", new Ingredient());

        return "ingredients/index";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute Ingredient ingredientForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttribute, Model model) {

        List<Ingredient> optionalList = ingredientRepo.findByName(ingredientForm.getName());

        if (!optionalList.isEmpty()) {
            bindingResult.addError(new ObjectError("unique", "This ingredient already exists"));
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepo.findAll());
            model.addAttribute("newIngredient", ingredientForm);
            return "ingredients/index";
        }
        ingredientRepo.save(ingredientForm);
        return "redirect:/ingredients";
    }

}
