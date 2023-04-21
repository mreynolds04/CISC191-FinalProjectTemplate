import edu.sdccd.cisc191.template.Income;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncomeTest {

    @Test
    public void testTransaction() {
        Income income = new Income();
        income.transaction(1000.0);
        assertEquals(1, income.income.length);
        assertEquals(1000.0, income.income[0], 0.0);
        income.transaction(2000.0);
        assertEquals(2, income.income.length);
        assertEquals(1000.0, income.income[0], 0.0);
        assertEquals(2000.0, income.income[1], 0.0);
    }

    @Test
    public void testGetTotalIncome() {
        Income income = new Income();
        assertEquals(0.0, income.getTotalIncome(), 0.0);
        income.transaction(1000.0);
        assertEquals(1000.0, income.getTotalIncome(), 0.0);
        income.transaction(2000.0);
        assertEquals(3000.0, income.getTotalIncome(), 0.0);
    }
}
