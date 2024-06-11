package com.platzi_pizzeria.platzi_pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter @Setter @NoArgsConstructor
public class CustomerEntity {

    @Id @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

}
