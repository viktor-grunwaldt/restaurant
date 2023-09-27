package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.exception.NoFoodFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MenuServiceImplTest {
    private MenuServiceImpl menuService;
    private List<Meal> meals;

    // I'd rather use @BeforeAll, but that would require me to define lifetime of this test class
    @BeforeEach
    public void setup(){
        this.menuService = new MenuServiceImpl();
        this.meals = createTestMeals();
    }
    @Test
    public void findVegetarianFoodWhenInputWasEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        // when
        // then
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findVegetarianFood(meals));
    }
    @Test
    public void findVegetarianFoodHandleNullAsInput() {
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findVegetarianFood(null));
    }
    
    @Test
    public void findVegetarianFood() {
        // when
        List<Meal> result = menuService.findVegetarianFood(this.meals);
        // then
        int expectedSize = 2;
        //HINT: write what result you expect after the method was called
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
    }
    @Test
    public void throwIfNoVegetarianFoodFound() {
        // see createTestMeals for more context
        this.meals.remove(1);
        this.meals.remove(1);
        // when
        Assertions.assertThrows(NoFoodFoundException.class, ()-> menuService.findVegetarianFood(this.meals));
    }
    @Test
    public void findFoodByTypeWhenInputWasEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        // when
        // then
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodByType(meals, DietType.REGULAR));
    }
    @Test
    public void findFoodByTypeHandleNullAsInput() {
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodByType(null, DietType.REGULAR));
    }
    @Test
    public void findFoodByType() {
        // when
        List<Meal> result = menuService.findFoodByType(this.meals, DietType.REGULAR);
        // then
        int expectedSize = 1;
        // expect
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Scrambled Eggs", result.get(0).getName());
    }
    @Test
    public void findFoodByTypeVegetarian() {
        // when
        List<Meal> result = menuService.findFoodByType(this.meals, DietType.VEGETARIAN);
        // then
        int expectedSize = 2;
        // expect
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
    }
    @Test
    public void findFoodByTypeVegan() {
        // when
        List<Meal> result = menuService.findFoodByType(this.meals, DietType.VEGAN);
        // then
        int expectedSize = 1;
        // expect
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Salad", result.get(0).getName());
    }
    @Test
    public void findFoodStartingWithNameWhenInputWasEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        // when
        // then
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodStartingWithName(meals, "Salad"));
    }
    @Test
    public void findFoodStartingWithNameHandleNullAsInput() {
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodStartingWithName(null, "Salad"));
    }

    @Test
    public void findFoodStartingWithName() {
        // when
        List<Meal> result = menuService.findFoodStartingWithName(this.meals, "Sala");
        // then
        int expectedSize = 1;
        //HINT: write what result you expect after the method was called
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Salad", result.get(0).getName());
    }

    @Test
    public void findFoodCheaperThanWhenInputWasEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        // when
        // then
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodCheaperThan(meals, 0));
    }
    @Test
    public void findFoodCheaperThanHandleNullAsInput() {
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodCheaperThan(null, 0));
    }

    @Test
    public void findFoodCheaperThan() {
        // when
        List<Meal> result = menuService.findFoodCheaperThan(this.meals, 11);
        // then
        int expectedSize = 1;
        // expect
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Scrambled Eggs", result.get(0).getName());
    }
    @Test
    public void findFoodWithCaloriesWhenInputWasEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        // when
        // then
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodWithCalories(meals, 0, 500));
    }
    @Test
    public void findFoodWithCaloriesHandleNullAsInput() {
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodWithCalories(null, 0, 500));
    }

    @Test
    public void findFoodWithCalories() {
        // when
        List<Meal> result = menuService.findFoodWithCalories(this.meals, 0,251);
        // then
        int expectedSize = 1;
        //HINT: write what result you expect after the method was called
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Salad", result.get(0).getName());
    }
    @Test
    public void findFoodContainingWhenInputWasEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        Produce produce = customProduceConstructor("Lettuce", ProductType.VEGETABLE);
        // when
        // then
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodContaining(meals, produce));
    }
    @Test
    public void findFoodContainingHandleNullAsInput() {
        Produce produce = customProduceConstructor("Lettuce", ProductType.VEGETABLE);
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodContaining(null, produce));
    }

    @Test
    public void findFoodContaining() {
        // when
        Produce egg = customProduceConstructor("Oil", ProductType.PLANT_BASED);
        List<Meal> result = menuService.findFoodContaining(this.meals, egg );
        // then
        int expectedSize = 1;
        //HINT: write what result you expect after the method was called
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Scrambled Eggs", result.get(0).getName());
    }
    @Test
    public void findFoodExcludingAllWhenInputWasEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        List<Produce> productBlacklist = new ArrayList<>();
        // when
        // then
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodExcludingAll(meals, productBlacklist));
    }
    @Test
    public void findFoodExcludingAllHandleNullAsInput() {
        List<Produce> productBlacklist = new ArrayList<>();
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodExcludingAll(null, productBlacklist));
    }

    @Test
    public void findFoodExcludingAll() {
        // when
        Produce egg = customProduceConstructor("Chicken Eggs", ProductType.EGGS);
        List<Produce> blacklist = new ArrayList<>();
        blacklist.add(egg);
        List<Meal> result = menuService.findFoodExcludingAll(this.meals, blacklist);
        // then
        int expectedSize = 1;
        //HINT: write what result you expect after the method was called
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Salad", result.get(0).getName());
    }
    private Meal customMealConstructor(String name, int calories, DietType dietType, int price, List<Produce> products){
        Meal meal = new Meal();
        meal.setName(name);
        meal.setCalories(calories);
        meal.setDietType(dietType);
        meal.setPrice(price);
        meal.setProducts(products);
        return meal;
    }
    private Produce customProduceConstructor(String name, ProductType productType){
        Produce produce = new Produce();
        produce.setName(name);
        produce.setProductType(productType);
        return produce;
    }
    private List<Meal> createTestMeals() {
        List<Meal> meals = new ArrayList<>();
        meals.add(customMealConstructor(
                "Scrambled Eggs",
                300,
                DietType.REGULAR,
                10,
                Arrays.asList(
                        customProduceConstructor("Oil", ProductType.PLANT_BASED),
                        customProduceConstructor("Chicken Eggs", ProductType.EGGS),
                        customProduceConstructor("Bacon", ProductType.MEAT)
                )
            ));
        meals.add(customMealConstructor(
                "Cake",
                500,
                DietType.VEGETARIAN,
                50,
                Arrays.asList(
                        customProduceConstructor("Flour", ProductType.GRAIN),
                        customProduceConstructor("Chicken Eggs", ProductType.EGGS),
                        customProduceConstructor("Milk", ProductType.DAIRY)
                )
        ));
        meals.add(customMealConstructor(
                "Salad",
                250,
                DietType.VEGAN,
                25,
                Arrays.asList(
                        customProduceConstructor("Lettuce", ProductType.VEGETABLE),
                        customProduceConstructor("Tomato", ProductType.VEGETABLE),
                        customProduceConstructor("Cucumber", ProductType.VEGETABLE)
                )
        ));
        return meals;
    }


}