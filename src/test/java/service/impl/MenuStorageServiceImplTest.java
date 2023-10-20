package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import domain.eto.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import service.api.MenuService;
import service.exception.NoFoodFoundException;

import java.util.ArrayList;
import java.util.List;

import static service.impl.TestHelper.*;

public class MenuStorageServiceImplTest {
    @Nested
    class MenuStorageServiceImplSuccessTests extends MenuServiceImplTest {
        @Override
        @BeforeEach
        public void setup(){
            this.menuService = new MenuStorageServiceImpl();
            this.meals = createTestMeals();
            // with these parameters, all tests should pass
            setupStorage();
        }
    }
    @Nested
    class MenuStorageServiceImplFailureTests {
        // Not Implemented
        protected MenuService menuService;
        protected List<Meal> meals;
        @BeforeEach
        public void setup(){
            this.menuService = new MenuStorageServiceImpl();
            this.meals = createTestMeals();
            // with these parameters, all tests should pass
            // setupStorage();
            Storage.CommonStorage.clearStorage();

        }

        @Test
        public void throwIfNotEnoughIngredientsToPrepareMealTest(){
            // when
            List<Produce> blacklist = new ArrayList<>();
            blacklist.add(egg());
            // Supplier<List<Meal>> errorThrower = ;
            // I can't declare this as Executable and Supplier is not okay
            Assertions.assertThrows(NoFoodFoundException.class, () -> menuService.findVegetarianFood(this.meals));
            Assertions.assertThrows(NoFoodFoundException.class, () -> menuService.findFoodByType(this.meals, DietType.REGULAR));
            Assertions.assertThrows(NoFoodFoundException.class, () -> menuService.findFoodStartingWithName(this.meals, "Salad"));
            Assertions.assertThrows(NoFoodFoundException.class, () -> menuService.findFoodCheaperThan(this.meals, 11));
            Assertions.assertThrows(NoFoodFoundException.class, () -> menuService.findFoodWithCalories(this.meals, 0, 25));
            Assertions.assertThrows(NoFoodFoundException.class, () -> menuService.findFoodContaining(this.meals, egg()));
            Assertions.assertThrows(NoFoodFoundException.class, () -> menuService.findFoodExcludingAll(this.meals, blacklist));
        }

        @Test
        public void throwIfStorageContainsOnlySomeProductsTest(){
            Meal eggs = createTestMeals().get(0);
            eggs.getProducts().stream().skip(1).forEach( p -> Storage.CommonStorage.addToStorage(p, 1));
            Assertions.assertThrows(NoFoodFoundException.class, () -> menuService.findVegetarianFood(this.meals));
        }
    }
}
