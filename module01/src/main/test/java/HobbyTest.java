
import org.junit.jupiter.api.Test;
import ru.sberbank.edu.GreetingImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

/***
 * Убеждаемся, что строка правда содержит слово Java
 */
public class HobbyTest {
    GreetingImpl greeting;
    @Test
    public void getHobbySuccess(){
        greeting=new GreetingImpl();
        assertTrue(greeting.getBestHobby().contains("Java"),"Должно быть упоминание Java!");

    }

}
