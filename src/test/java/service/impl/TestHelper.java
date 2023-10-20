package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import domain.eto.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHelper {
    static Meal customMealConstructor(String name, int calories, DietType dietType, int price, List<Produce> products){
        Meal meal = new Meal();
        meal.setName(name);
        meal.setCalories(calories);
        meal.setDietType(dietType);
        meal.setPrice(price);
        meal.setProducts(products);
        return meal;
    }
    static Produce customProduceConstructor(String name, ProductType productType){
        Produce produce = new Produce();
        produce.setName(name);
        produce.setProductType(productType);
        return produce;
    }
    protected static Produce egg(){
        return customProduceConstructor("Chicken Eggs", ProductType.EGGS);
    }
    private static Produce bacon(){
        return customProduceConstructor("Bacon", ProductType.MEAT);
    }
    private static Produce flour(){
        return customProduceConstructor("Flour", ProductType.GRAIN);
    }
    private static Produce milk(){
        return customProduceConstructor("Milk", ProductType.DAIRY);
    }
    private static Produce lettuce(){
        return customProduceConstructor("Lettuce", ProductType.VEGETABLE);
    }
    private static Produce tomato(){
        return customProduceConstructor("Tomato", ProductType.VEGETABLE);
    }
    private static Produce cucumber(){
        return customProduceConstructor("Cucumber", ProductType.VEGETABLE);
    }
    private static Produce oil(){
        return customProduceConstructor("Oil", ProductType.PLANT_BASED);
    }

    public static List<Meal> createTestMeals() {
        List<Meal> meals = new ArrayList<>();
        meals.add(customMealConstructor(
                "Scrambled Eggs",
                300,
                DietType.REGULAR,
                10,
                Arrays.asList(
                        egg(), oil(),bacon()
                )
        ));
        meals.add(customMealConstructor(
                "Cake",
                500,
                DietType.VEGETARIAN,
                50,
                Arrays.asList(
                    flour(),egg(),milk()
                )
        ));
        meals.add(customMealConstructor(
                "Salad",
                250,
                DietType.VEGAN,
                25,
                Arrays.asList(
                       lettuce(),tomato(), cucumber()
                )
        ));
        return meals;
    }
    
    public static void setupStorage(){
        Storage.CommonStorage.clearStorage();
        Storage.CommonStorage.addToStorage(egg(), 1);
        Storage.CommonStorage.addToStorage(bacon(), 1);
        Storage.CommonStorage.addToStorage(oil(), 1);
        Storage.CommonStorage.addToStorage(flour(), 1);
        Storage.CommonStorage.addToStorage(milk(), 1);
        Storage.CommonStorage.addToStorage(lettuce(), 1);
        Storage.CommonStorage.addToStorage(tomato(), 1);
        Storage.CommonStorage.addToStorage(cucumber(), 1);
    }
}
