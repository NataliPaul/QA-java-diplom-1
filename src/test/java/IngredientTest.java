import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    @Parameterized.Parameter(0)
    public IngredientType ingredientType;

    @Parameterized.Parameter(1)
    public String name;

    @Parameterized.Parameter(2)
    public float price;

    private Ingredient ingredient;

    // Метод для предоставления параметров (включает разные типы ингредиентов)
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, Constants.INGREDIENT2_NAME, Constants.INGREDIENT2_PRICE},
                {IngredientType.FILLING, Constants.INGREDIENT1_NAME, Constants.INGREDIENT1_PRICE},
        });

    }

    @Before
    public void setUp() {
        // Создаем объект ингредиента перед каждым тестом с переданными параметрами
        ingredient = new Ingredient(ingredientType, name, price);
    }

    @Test
    public void testGetName() {
        assertEquals("Ожидаем правильное имя ингредиента", name, ingredient.getName());
    }

    // Тестирует, что цена ингредиента возвращается корректно
    @Test
    public void testGetPrice() {
        assertEquals("Ожидаем правильную цену ингредиента", price, ingredient.getPrice(), 0.0f);
    }

    // Тестирует, что тип ингредиента возвращается корректно
    @Test
    public void testGetType() {
        assertEquals("Ожидаем правильный тип ингредиента", ingredientType, ingredient.getType());
    }

    @Test
    public void testConstructor() {
        // Проверяем, что конструктор правильно устанавливает поля
        assertNotNull("Объект ингредиента не должен быть null", ingredient);
        assertEquals("Ожидаем, что имя ингредиента будет корректное", name, ingredient.getName());
        assertEquals("Ожидаем, что цена ингредиента будет корректная", price, ingredient.getPrice(), 0.0f);
        assertEquals("Ожидаем, что тип ингредиента будет корректный", ingredientType, ingredient.getType());
    }

}
