
import org.junit.jupiter.api.Test;
import ru.neoflex.service.CalculatorService;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class VacationCalculatorServiceTest {

    CalculatorService service = new CalculatorService();

    @Test
    void testSimpleCalculation() {
        double result = service.calculateVacationPay(50000, 10);
        assertEquals(17064.85, result, 0.01);
    }

    @Test
    void testWithHolidays() {
        double result = service.calculateWithDates(
                65000,
                LocalDate.of(2024, 5, 1),
                LocalDate.of(2024, 5, 10),
                Set.of(LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 9))
        );
        assertEquals(13310.58, result, 0.01); // 8 рабочих дней
    }
}
