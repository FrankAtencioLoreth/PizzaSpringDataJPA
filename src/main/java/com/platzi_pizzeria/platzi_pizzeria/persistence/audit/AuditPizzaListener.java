package com.platzi_pizzeria.platzi_pizzeria.persistence.audit;

import com.platzi_pizzeria.platzi_pizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

public class AuditPizzaListener {

    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity pizza) {
        System.out.println("POST LOAD: ");
        this.currentValue = pizza;
        this.currentValue = SerializationUtils.clone(pizza);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizza) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + pizza.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity pizza) {
        System.out.println(pizza.toString());
    }

}
