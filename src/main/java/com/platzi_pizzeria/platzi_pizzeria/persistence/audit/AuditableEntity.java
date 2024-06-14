package com.platzi_pizzeria.platzi_pizzeria.persistence.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@MappedSuperclass
public class AuditableEntity {

    @Column(name = "created_date")
    @CreationTimestamp
    private Instant createdDate;

    @Column(name = "modified_date")
    @UpdateTimestamp
    private Instant modifiedDate;

}
