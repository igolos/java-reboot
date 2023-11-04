
import org.junit.jupiter.api.Test;
import ru.sberbank.edu.GCD;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 *  Проверяем, что фактический результат реализации вычисления наибольшего делителя совпадает c ожидаемым числом
 */
public class EvklidTest {
    GCD gcd;
    @Test
    public void getMinDividerPositive(){
        gcd=new GCD();
        assertEquals(4,gcd.getDivisor(128,52));

    }
    @Test
    public void getMinDividerNegative(){
        gcd=new GCD();
        assertEquals(-4,gcd.getDivisor(-128,-52));

    }
}
