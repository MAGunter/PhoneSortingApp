package com.barista.maker.numbersortingapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Person implements Comparable<Person>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Long number;

    @Override
    public int compareTo(Person o){
        return this.number.compareTo(o.getNumber());
    }
}

