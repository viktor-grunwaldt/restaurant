package service.impl;

import domain.DietType;
import domain.ProductType;
import domain.eto.Meal;
import domain.eto.Produce;
import domain.eto.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.exception.NoFoodFoundException;

import java.util.Arrays;
import java.util.List;

public class StorageServiceImplTest {

    private StorageServiceImpl storageService;

    private Storage storage;

    private Meal exampleMeal;

    private Produce customProduceConstructor(String name, ProductType productType){
        Produce produce = new Produce();
        produce.setName(name);
        produce.setProductType(productType);
        return produce;
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
    @BeforeEach
    public void setup(){
        this.storageService = new StorageServiceImpl();
        this.storage = new Storage();
        Produce egg = customProduceConstructor("Chicken egg", ProductType.EGGS);
        Produce bacon = customProduceConstructor("Bacon", ProductType.MEAT);
        Produce oil = customProduceConstructor("Oil", ProductType.PLANT_BASED);
        this.exampleMeal = customMealConstructor(
                "Scrambled Eggs",
                300,
                DietType.REGULAR,
                10,
                Arrays.asList(
                        egg,bacon,oil
                )
        );
        this.storage.addNewProduct(egg);
        this.storage.setProduct(egg, 3);
        this.storage.setProduct(bacon, 2);
        this.storage.setProduct(oil, 1);

    }


    @Test
    public void canMealBePreparedFromProductsInStorageHandleNull() {
        Assertions.assertThrows(
                NoFoodFoundException.class,
                ()->storageService.canMealBePreparedFromProductsInStorage(null, null)
        );
    }
    @Test
    public void canMealBePreparedFromProductsInStorageHandleEmpty() {
        Assertions.assertThrows(
                NoFoodFoundException.class,
                ()->storageService.canMealBePreparedFromProductsInStorage(new Storage(), exampleMeal)
        );
    }

    @Test
    public void canMealBePreparedFromProducts() {
        Assertions.assertDoesNotThrow(()->storageService.canMealBePreparedFromProductsInStorage(storage, exampleMeal));
    }

    @Test
    public void canMealBePreparedFromProductsWithDuplicates() {
        Produce egg = customProduceConstructor("Chicken egg", ProductType.EGGS);
        Produce bacon = customProduceConstructor("Bacon", ProductType.MEAT);
        Produce oil = customProduceConstructor("Oil", ProductType.PLANT_BASED);
        Meal eggyEggs = customMealConstructor(
                "Scrambled eggy Eggs",
                300,
                DietType.REGULAR,
                10,
                Arrays.asList(
                        egg,egg,egg,bacon,oil
                )
        );
        Assertions.assertDoesNotThrow(()->storageService.canMealBePreparedFromProductsInStorage(storage, eggyEggs));
    }
    @Test
    public void canMealBePreparedFromProductsTooManyProducts() {
        // given
        Produce egg = customProduceConstructor("Chicken egg", ProductType.EGGS);
        Produce bacon = customProduceConstructor("Bacon", ProductType.MEAT);
        Produce oil = customProduceConstructor("Oil", ProductType.PLANT_BASED);
        Meal largeMeal = customMealConstructor(
                "Large Scrambled Eggs",
                600,
                DietType.REGULAR,
                20,
                Arrays.asList(
                        egg,bacon,oil,egg,bacon,oil
                )
        );
        // when
        // then
        Assertions.assertThrows(
                NoFoodFoundException.class,
                ()->storageService.canMealBePreparedFromProductsInStorage(storage, largeMeal)
        );
    }

    @Test
    public void canMealBePreparedFromProductsProductNotInStorage() {
        // given
        Produce egg = customProduceConstructor("Ostrich egg", ProductType.EGGS);
        Produce bacon = customProduceConstructor("Bacon", ProductType.MEAT);
        Produce oil = customProduceConstructor("Oil", ProductType.PLANT_BASED);
        Meal australianMeal = customMealConstructor(
                "Scrambled Eggs",
                300,
                DietType.REGULAR,
                10,
                Arrays.asList(
                        egg,bacon,oil
                )
        );
        // when
        // then
        Assertions.assertThrows(
                NoFoodFoundException.class,
                ()->storageService.canMealBePreparedFromProductsInStorage(storage, australianMeal)
        );
    }
}
