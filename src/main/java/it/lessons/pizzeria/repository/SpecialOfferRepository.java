package it.lessons.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lessons.pizzeria.model.SpecialOffer;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {

}
