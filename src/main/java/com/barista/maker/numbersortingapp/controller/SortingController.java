package com.barista.maker.numbersortingapp.controller;

import com.barista.maker.numbersortingapp.sort.QuickSort;
import lombok.RequiredArgsConstructor;
import com.barista.maker.numbersortingapp.entity.Person;
import com.barista.maker.numbersortingapp.repository.PersonRepository;
import com.barista.maker.numbersortingapp.sort.RadixSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Controller
@RequestMapping("/api/sorting")
@RequiredArgsConstructor
public class SortingController {
    private final RadixSort radixSort;
    private final PersonRepository personRepository;
    private final QuickSort quickSort;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/radix")
    public String radixSort(Model model){
        return sort(model, radixSort::executeRadixSort, "radix");
    }

    @GetMapping("/quick")
    public String quickSort(Model model){
        return sort(model, quickSort::executeQuickSort, "quick");
    }

    private String sort(Model model, Consumer<int[]> sortMethod, String viewName) {
        List<Person> list = personRepository.findAll();
        long startTime = System.currentTimeMillis();
        int[] numbers = list.stream().mapToInt(person -> person.getNumber().intValue()).toArray();
        sortMethod.accept(numbers);
        List<Person> sortedPersons = Arrays.stream(numbers)
                .mapToObj(number -> list.stream()
                        .filter(person -> person.getNumber().intValue() == number)
                        .findFirst()
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        model.addAttribute("duration", duration);
        model.addAttribute("persons", sortedPersons);

        return viewName;
    }
}
