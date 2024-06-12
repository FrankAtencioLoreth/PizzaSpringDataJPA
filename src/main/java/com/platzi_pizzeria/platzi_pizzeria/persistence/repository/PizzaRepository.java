package com.platzi_pizzeria.platzi_pizzeria.persistence.repository;

import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    //QueryMethods
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    //PizzaEntity findFirstByAvailableTrueAndNameIgnoreCase(String name);
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    int countByVeganTrue();
}
