import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeValues() {
        // Проверяем, что перечисление содержит два значения: SAUCE и FILLING
        IngredientType[] expectedValues = {IngredientType.SAUCE, IngredientType.FILLING};
        assertArrayEquals("Ожидаем, что перечисление содержит SAUCE и FILLING", expectedValues, IngredientType.values());
    }

    @Test
    public void testValueOfSauce() {
        // Проверяем, что значение SAUCE можно получить через valueOf
        IngredientType sauce = IngredientType.valueOf("SAUCE");
        assertEquals("Ожидаем, что тип будет SAUCE", IngredientType.SAUCE, sauce);
    }

    @Test
    public void testValueOfFilling() {
        // Проверяем, что значение FILLING можно получить через valueOf
        IngredientType filling = IngredientType.valueOf("FILLING");
        assertEquals("Ожидаем, что тип будет FILLING", IngredientType.FILLING, filling);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValueOf() {
        // Проверяем, что при попытке получить несуществующее значение выбрасывается исключение
        IngredientType.valueOf("INVALID");
    }
}