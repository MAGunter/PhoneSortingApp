package com.barista.maker.numbersortingapp.controller;

import lombok.RequiredArgsConstructor;
import com.barista.maker.numbersortingapp.entity.Person;
import com.barista.maker.numbersortingapp.repository.PersonRepository;
import com.barista.maker.numbersortingapp.sort.RadixSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/sorting")
@RequiredArgsConstructor
public class SortingController {
    private final RadixSort radixSort;
    private final PersonRepository personRepository;

    @GetMapping("/radix")
    public String radixSort(Model model){
        List<Person> list = personRepository.findAll();
        int[] numbers = list.stream().mapToInt(person -> person.getNumber().intValue()).toArray();
        radixSort.executeRadixSort(numbers);
        model.addAttribute("persons", list.stream()
                .sorted((p1, p2) -> Long.compare(p1.getNumber(), p2.getNumber()))
                .toList());

        return "radix";
    }
}
