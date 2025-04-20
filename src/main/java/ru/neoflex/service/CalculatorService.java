package ru.neoflex.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class CalculatorService {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    public double calculateVacationPay(double averageSalary, int vacationDays) {
        double daily = averageSalary / AVERAGE_DAYS_IN_MONTH;
        return round(daily * vacationDays);
    }

    public double calculateWithDates(double averageSalary, LocalDate start, LocalDate end, Set<LocalDate> holidays) {
        long workingDays = start.datesUntil(end.plusDays(1))
                .filter(date -> !isWeekend(date) && !holidays.contains(date))
                .count();

        return calculateVacationPay(averageSalary, (int) workingDays);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
