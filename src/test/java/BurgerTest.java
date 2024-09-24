import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    // Объявляем переменные для тестируемого объекта и его зависимостей
    private Burger burger; // Бургер, который будем тестировать

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void init() {

        // Создаем экземпляр бургера и устанавливаем для него булочку
        burger = new Burger();
        burger.setBuns(bun);

        // Настраиваем моки, определяя поведение методов
        when(bun.getName()).thenReturn(Constants.BUN_NAME); // Устанавливаем имя булочки
        when(bun.getPrice()).thenReturn(Constants.BUN_PRICE); // Устанавливаем цену булочки

        when(ingredient1.getName()).thenReturn(Constants.INGREDIENT1_NAME); // Устанавливаем имя первого ингредиента
        when(ingredient1.getPrice()).thenReturn(Constants.INGREDIENT1_PRICE); // Устанавливаем цену первого ингредиента
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING); // Устанавливаем тип для ingredient1

        when(ingredient2.getName()).thenReturn(Constants.INGREDIENT2_NAME); // Устанавливаем имя второго ингредиента
        when(ingredient2.getPrice()).thenReturn(Constants.INGREDIENT2_PRICE); // Устанавливаем цену второго ингредиента
        when(ingredient2.getType()).thenReturn(IngredientType.SAUCE); // Устанавливаем тип для ingredient1
    }


    // Метод для добавления ингредиентов в бургер
    private void addIngredients() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
    }

    private void assertIngredientNames() {
        assertEquals("Первый ингредиент должен быть " + Constants.INGREDIENT1_NAME,
                Constants.INGREDIENT1_NAME, burger.ingredients.get(0).getName());
        assertEquals("Второй ингредиент должен быть " + Constants.INGREDIENT2_NAME,
                Constants.INGREDIENT2_NAME, burger.ingredients.get(1).getName());
    }

    @Test
    public void testAddIngredient() {
        // Добавление ингредиентов в бургер
        addIngredients();

        // Проверяем, что количество ингредиентов в бургере равно 2
        assertEquals("Ингредиенты должны добавляться в бургер", 2, burger.ingredients.size());
        // Проверяем, что первый ингредиент - это "Сыр"
        assertIngredientNames();

    }

    @Test
    public void testRemoveIngredient() {
        // Добавление ингредиентов в бургер перед удалением
        addIngredients();
        // Удаляем первый ингредиент
        burger.removeIngredient(0);
        // Проверяем, что остался только один ингредиент
        assertEquals("Ингредиент должен удаляться по индексу", 1, burger.ingredients.size());
        // Проверяем, что оставшийся ингредиент - это "Томатный"
        assertEquals("Оставшийся ингредиент должен быть " + Constants.INGREDIENT2_NAME, Constants.INGREDIENT2_NAME, burger.ingredients.get(0).getName());
    }

    @Test
    public void testMoveIngredient() {
        // Добавление ингредиентов в бургер
        addIngredients();

        // Перемещаем первый ингредиент на вторую позицию
        burger.moveIngredient(0, 1);

        // Проверяем, что первый ингредиент теперь "Томатный"
        assertEquals("Ингредиент должен быть перемещен на новую позицию", Constants.INGREDIENT2_NAME, burger.ingredients.get(0).getName());
    }

    @Test
    public void testGetPrice() {
        // Добавление ингредиентов в бургер
        addIngredients();

        // Рассчитываем ожидаемую цену
        float expectedPrice = bun.getPrice() * 2 + ingredient1.getPrice() + ingredient2.getPrice();
        // Проверяем, что цена бургера рассчитывается правильно
        assertEquals("Цена бургера должна корректно рассчитываться", expectedPrice, burger.getPrice(), 0.01f);

        // Проверяем, что метод getPrice() у булочки был вызван два раза
        verify(bun, times(2)).getPrice();

        // Проверяем, что методы getPrice() для ингредиентов были вызваны
        verify(ingredient1, atLeastOnce()).getPrice();
        verify(ingredient2, atLeastOnce()).getPrice();
    }

    @Test
    public void testGetReceipt() {
        // Добавление ингредиентов в бургер
        addIngredients();

        // Получение чека
        String receipt = burger.getReceipt();
        // Проверка, что чек содержит название булочки
        assertTrue("Чек должен содержать название булочки", receipt.contains(bun.getName()));
        // Проверка, что чек содержит ингредиент "Сыр"
        assertTrue("Чек должен содержать ингредиент " + Constants.INGREDIENT1_NAME, receipt.contains(ingredient1.getName()));
        // Проверка, что чек содержит ингредиент "Томатный"
        assertTrue("Чек должен содержать ингредиент " + Constants.INGREDIENT2_NAME, receipt.contains(ingredient2.getName()));
        // Проверка, что чек содержит цену
        assertTrue("Чек должен содержать цену", receipt.contains(String.format("Price: %f", burger.getPrice())));

        // Проверяем, что getName() у булочки был вызван для получения чека
        verify(bun, atLeastOnce()).getName();

    }

    @Test
    public void testIngredientTypes() {

        addIngredients();
        String receipt = burger.getReceipt();
        // Проверка типов ингредиентов
        assertTrue("Чек должен содержать тип ингредиента " + ingredient1.getType().toString().toLowerCase(),
                receipt.contains(ingredient1.getType().toString().toLowerCase()));
        assertTrue("Чек должен содержать тип ингредиента " + ingredient2.getType().toString().toLowerCase(),
                receipt.contains(ingredient2.getType().toString().toLowerCase()));
    }

    @Test
    public void testAddSameIngredientSeveralTimes() {

        // Добавляем один и тот же ингредиент три раза
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient1);

        // Проверяем, что количество ингредиентов в бургере равно 3
        assertEquals("Ингредиент " + Constants.INGREDIENT1_NAME + " добавляться в бургер три раза", 3, burger.ingredients.size());

        // Рассчитываем ожидаемую цену
        float expectedPrice = bun.getPrice() * 2 + ingredient1.getPrice() * 3; // Если булочка добавляется 2 раза
        // Проверяем, что цена бургера рассчитывается правильно
        assertEquals("Цена бургера должна корректно рассчитываться", expectedPrice, burger.getPrice(), 0.01f);

        // Проверка, что чек содержит ингредиент три раза
        String receipt = burger.getReceipt();
        assertEquals("Чек должен содержать ингредиент " + Constants.INGREDIENT1_NAME + " три раза",
                3, (int) Arrays.stream(receipt.split(ingredient1.getName())).count() - 1); // Проверка, что ингредиент добавлен 3 раза
    }
}


