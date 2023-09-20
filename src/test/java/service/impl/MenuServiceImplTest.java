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
    private void setup(){
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
        int expectedSize = 1;
        //HINT: write what result you expect after the method was called
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedSize, result.size());
        Assertions.assertEquals("Salad", result.get(0).getName());
    }
    @Test
    public void throwIfNoVegetarianFoodFound() {
        // see createTestMeals for more context
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
    // TODO: impl me
    @Test
    public void findFoodByType() {
        List<Meal> meals = createTestMeals();
    
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

    // TODO: impl me
    @Test
    public void findFoodStartingWithName() {

    }
    @Test
    public void findFoodCheaperWithCaloriesWhenInputWasEmpty() {
        //given
        List<Meal> meals = new ArrayList<>();
        // when
        // then
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodCheaperWithCalories(meals, 0, 500));
    }
    @Test
    public void findFoodCheaperWithCaloriesHandleNullAsInput() {
        Assertions.assertThrows(NoFoodFoundException.class, ()->menuService.findFoodCheaperWithCalories(null, 0, 500));
    }

    // TODO: impl me
    @Test
    public void findFoodCheaperWithCalories() {
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

    // TODO: impl me
    @Test
    public void findFoodContaining() {
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

    // TODO: impl me
    @Test
    public void findFoodExcludingAll() {
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
                        customProduceConstructor("Chicker Eggs", ProductType.EGGS)
                )
            ));
        meals.add(customMealConstructor(
                "Salad",
                250,
                DietType.VEGETARIAN,
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