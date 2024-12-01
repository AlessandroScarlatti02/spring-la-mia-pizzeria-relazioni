package it.lessons.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.lessons.pizzeria.model.SpecialOffer;
import it.lessons.pizzeria.repository.SpecialOfferRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/specialOffers")
public class SpecialOffersController {

    @Autowired
    SpecialOfferRepository specialOfferRepo;

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOfferForm,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "specialOffers/create";
        }

        specialOfferRepo.save(specialOfferForm);

        redirectAttributes.addFlashAttribute("successMessage", "Offer created");

        return "redirect:/pizze/show/" + specialOfferForm.getPizza().getId();

    }

}
