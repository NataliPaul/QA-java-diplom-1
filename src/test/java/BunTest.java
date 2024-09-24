import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.*;

public class BunTest {

    private Bun bun;

    @Before
    public void setUp() {
        // Создаем объект булочки перед каждым тестом
        bun = new Bun(Constants.BUN_NAME, Constants.BUN_PRICE);
    }

    @Test
    @Description("Проверка, что метод getName возвращает правильное имя булочки")
    public void testGetName() {
        // Проверяем, что метод getName возвращает правильное имя
        assertEquals("Ожидаем, что имя булочки будет 'Бриошь'", Constants.BUN_NAME, bun.getName());
    }

    @Test
    @Description("Проверка, что метод getPrice возвращает правильную цену булочки")
    public void testGetPrice() {
        // Проверяем, что метод getPrice возвращает правильную цену
        assertEquals("Ожидаем, что цена булочки будет 2.50f", Constants.BUN_PRICE, bun.getPrice(), 0.0f);
    }

    @Test
    public void testConstructor() {
        // Проверяем, что конструктор правильно устанавливает поля
        assertNotNull("Ожидаем, что объект булочки не будет null", bun);
        assertEquals("Ожидаем, что имя булочки будет 'Бриошь'", Constants.BUN_NAME, bun.name);
        assertEquals("Ожидаем, что цена булочки будет 2.50f", Constants.BUN_PRICE, bun.price, 0.0f);
    }
}
