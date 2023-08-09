package br.com.postman.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity


public class numero {


    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable = false, unique = true)
    private Long id;

    @Getter
    @Setter
    @Column(name = "numeros")
    private Double numero;


}
