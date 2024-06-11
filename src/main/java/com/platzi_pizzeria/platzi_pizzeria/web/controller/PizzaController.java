package com.platzi_pizzeria.platzi_pizzeria.web.controller;

import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.PizzaEntity;
import com.platzi_pizzeria.platzi_pizzeria.service.PizzaService;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int idPizza) {
        if(this.pizzaService.getById(idPizza)!=null) {
            return ResponseEntity.ok(this.pizzaService.getById(idPizza));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pizza NO existe");
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
