package praktikum;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель бургера.
 * Бургер состоит из булочек и ингредиентов (начинка или соус).
 * Ингредиенты можно перемещать и удалять.
 * Можно распечать чек с информацией о бургере.
 */
public class Burger {

    public Bun bun;
    public List<Ingredient> ingredients = new ArrayList<>();

    public void setBuns(Bun bun) {
        this.bun = bun;
    }

    //Метод добавляет ингредиент в бургер.
    //Принимает объект класса Ingredient и добавляет его в список ingredients
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    //Метод Удаляет ингредиент из бургера по индексу.
    //Удаляет элемент из списка ingredients по указанному индексу.
    public void removeIngredient(int index) {
        ingredients.remove(index);
    }

    //Метод изменяет индекс ингредиента в списке
    public void moveIngredient(int index, int newIndex) {
        ingredients.add(newIndex, ingredients.remove(index));
    }

    //Метод рассчитывает и возвращает общую цену бургера
    public float getPrice() {
        float price = bun.getPrice() * 2;

        for (Ingredient ingredient : ingredients) {
            price += ingredient.getPrice();
        }

        return price;
    }

    //Метод формирует и возвращает строку с чеком на бургер
    public String getReceipt() {
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));

        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", getPrice()));

        return receipt.toString();
    }

}