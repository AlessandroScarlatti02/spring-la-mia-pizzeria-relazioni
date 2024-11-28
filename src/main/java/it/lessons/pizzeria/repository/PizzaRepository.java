package it.lessons.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lessons.pizzeria.model.Pizza;
import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    public List<Pizza> findByName(String name);

}
