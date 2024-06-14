package com.platzi_pizzeria.platzi_pizzeria.web.controller;

import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.PizzaEntity;
import com.platzi_pizzeria.platzi_pizzeria.service.PizzaService;
import com.platzi_pizzeria.platzi_pizzeria.service.dto.UpdatePizzaPriceDto;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {


    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<PizzaEntity>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int elementsQty) {
        return ResponseEntity.ok(this.pizzaService.getAllPaginated(page, elementsQty));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int idPizza) {
        if(this.pizzaService.getById(idPizza)!=null) {
            return ResponseEntity.ok(this.pizzaService.getById(idPizza));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pizza NO existe");
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elementsQty,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elementsQty, sortBy, sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        PizzaEntity pizzaEntity = this.pizzaService.getByName(name);
        if(pizzaEntity!=null) {
            return ResponseEntity.ok(pizzaEntity);
        }
        return ResponseEntity.status(404).body("La pizza NO existe");
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<?> getCheapestPizza(@PathVariable("price") double price) {
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<?> getWith(@PathVariable("ingredient") String ingredient) {
        List<PizzaEntity>  listPizza = this.pizzaService.getWith(ingredient);
        if(!listPizza.isEmpty()) {
            return ResponseEntity.ok(listPizza);
        }
        return ResponseEntity.status(404).body("La pizza NO existe");
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<?> getWithout(@PathVariable("ingredient") String ingredient) {
        List<PizzaEntity>  listPizza = this.pizzaService.getWithout(ingredient);
        if(!listPizza.isEmpty()) {
            return ResponseEntity.ok(listPizza);
        }
        return ResponseEntity.status(404).body("La pizza NO existe");
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PizzaEntity pizzaEntity) {
        try {
            if (pizzaEntity.getIdPizza() == null || !this.pizzaService.existById(pizzaEntity.getIdPizza())) {
                return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La pizza existe");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PizzaEntity pizzaEntity) {
        try {
            if (pizzaEntity.getIdPizza() != null || this.pizzaService.existById(pizzaEntity.getIdPizza())) {
                return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pizza NO existe");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto pizzaPriceDto) {
        try {
            if (this.pizzaService.existById(pizzaPriceDto.getIdPizza())) {
                this.pizzaService.updatePrice(pizzaPriceDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int idPizza) {
        try {
            if(this.pizzaService.existById(idPizza)) {
                this.pizzaService.delete(idPizza);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("La pizza ha sido eliminada");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pizza NO existe");
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
