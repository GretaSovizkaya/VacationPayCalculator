package ru.neoflex.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.service.CalculatorService;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CalculatorController {

    CalculatorService calculatorService;

    @GetMapping
    public double calculate(
            @RequestParam double averageSalary,
            @RequestParam(required = false) Integer vacationDays,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        if (start != null && end != null) {
            return calculatorService.calculateWithDates(
                    averageSalary,
                    LocalDate.parse(start),
                    LocalDate.parse(end),
                    getRussianHolidays()
            );
        }

        if (vacationDays == null) {
            throw new IllegalArgumentException("vacationDays is required if no dates provided");
        }

        return calculatorService.calculateVacationPay(averageSalary, vacationDays);
    }

    private Set<LocalDate> getRussianHolidays() {
        return Set.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 1, 7),
                LocalDate.of(2024, 2, 23),
                LocalDate.of(2024, 3, 8),
                LocalDate.of(2024, 5, 1),
                LocalDate.of(2024, 5, 9),
                LocalDate.of(2024, 6, 12),
                LocalDate.of(2024, 11, 4)
        );
    }
}
