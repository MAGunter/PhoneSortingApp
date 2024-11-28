package com.barista.maker.numbersortingapp.repository;

import com.barista.maker.numbersortingapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
