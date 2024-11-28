package com.barista.maker.numbersortingapp.utility;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import com.barista.maker.numbersortingapp.entity.Person;
import com.barista.maker.numbersortingapp.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String ...args){
        Faker faker = new Faker();
        List<Person> list = new ArrayList<>();

        for(int i = 0; i < 10000; i++){
            Person person = new Person();
            person.setFirstName(faker.name().firstName());
            person.setLastName(faker.name().lastName());
            person.setNumber(faker.number().numberBetween(87010000000L, 87779999999L));
            list.add(person);
        }

        personRepository.saveAll(list);
    }
}
