package com.platzi_pizzeria.platzi_pizzeria.persistence.repository;

import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPageSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer>{
}
