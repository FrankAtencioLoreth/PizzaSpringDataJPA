package com.platzi_pizzeria.platzi_pizzeria.service;

import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.PizzaEntity;
import com.platzi_pizzeria.platzi_pizzeria.persistence.repository.PizzaPageSortRepository;
import com.platzi_pizzeria.platzi_pizzeria.persistence.repository.PizzaRepository;
import com.platzi_pizzeria.platzi_pizzeria.service.dto.UpdatePizzaPriceDto;
import com.platzi_pizzeria.platzi_pizzeria.service.exception.EmailAPiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPageSortRepository pizzaPageSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPageSortRepository pizzaPageSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPageSortRepository = pizzaPageSortRepository;
    }

    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }

    public Page<PizzaEntity> getAllPaginated(int page, int elementsQty) {
        Pageable pageRequest = PageRequest.of(page, elementsQty);
        return this.pizzaPageSortRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getAvailable(int page, int elementsQty, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elementsQty, sort);
        return this.pizzaPageSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElse(null);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public List<PizzaEntity> getWith(String description) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getWithout(String description) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(description);
    }

    public PizzaEntity getById(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return this.pizzaRepository.save(pizzaEntity);
    }

    public boolean existById(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    @Transactional(
            noRollbackFor = EmailAPiException.class
    )
    public void updatePrice(UpdatePizzaPriceDto updatePizzaPriceDto) {
        System.out.println("UPDATE INFO: " + updatePizzaPriceDto);
        this.pizzaRepository.updatePrice(updatePizzaPriceDto);
        this.sendEmail();
    }

    private void sendEmail() {
        throw new EmailAPiException();
    }
}
