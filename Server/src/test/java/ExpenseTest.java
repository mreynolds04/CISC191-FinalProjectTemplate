import edu.sdccd.cisc191.template.Expense;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpenseTest {

    @Test
    public void testTransaction() {
        Expense expense = new Expense();
        expense.transaction(500.0);
        assertEquals(1, expense.expenses.length);
        assertEquals(500.0, expense.expenses[0], 0.0);
        expense.transaction(1000.0);
        assertEquals(2, expense.expenses.length);
        assertEquals(500.0, expense.expenses[0], 0.0);
        assertEquals(1000.0, expense.expenses[1], 0.0);
    }

    @Test
    public void testGetTotalExpenses() {
        Expense expense = new Expense();
        assertEquals(0.0, expense.getTotalExpenses(), 0.0);
        expense.transaction(500.0);
        assertEquals(500.0, expense.getTotalExpenses(), 0.0);
        expense.transaction(1000.0);
        assertEquals(1500.0, expense.getTotalExpenses(), 0.0);
    }
}
