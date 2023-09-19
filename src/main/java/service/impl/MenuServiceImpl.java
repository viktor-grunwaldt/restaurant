package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;
import service.api.MenuService;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MenuServiceImpl implements MenuService {

    private List<Meal> getAllByPredicate(List<Meal> meals, Predicate<Meal> fn){
        return meals.stream().filter(fn).collect(Collectors.toList());
    }

    /**
     * For a given List of meals, find all meals that are of dietType = {@link DietType#VEGETARIAN}.
     * Hint: You can copy the implementation from MenuExample
     *
     * @param meals
     * @return list of found meals
     */
    @Override
    public List<Meal> findVegetarianFood(List<Meal> meals) {
        return this.getAllByPredicate(meals, meal -> meal.getDietType().equals(DietType.VEGETARIAN));
    }

    /**
     * For a given List of meals, find all meals that are of certain dietType
     *
     * @param meals
     * @param diet
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
        return this.getAllByPredicate(meals, meal -> meal.getDietType().equals(diet));
    }

    /**
     * For a given List of meals return meals that cost less than given parameter.
     *
     * @param meals
     * @param price
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, Integer price) {
        return this.getAllByPredicate(meals,
                meal -> meal.getPrice() < price);
    }

    /**
     * For a given List of meals return meals that have calorie intake between two values
     *
     * @param meals
     * @param minCalories
     * @param maxCalories
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodCheaperWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
        return this.getAllByPredicate( meals,
                meal -> minCalories < meal.getCalories() && meal.getCalories() < maxCalories);
    }

    /**
     * For a given List of meals return meals that name starts with a String. This method ignores character case when
     * searching.
     *
     * @param meals
     * @param name  - can be a partial String
     * @return list of found meals
     */
    // TODO: check if name was wrong in the example
    @Override
    public List<Meal> findFoodCheaperThanPrice(List<Meal> meals, String name) {
        return this.getAllByPredicate(meals, meal -> meal.getName().startsWith(name));
    }

    /**
     * For a given List of meals return meals that does contain certain product
     *
     * @param meals
     * @param product product that is in the meal
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Produce product) {
        return this.getAllByPredicate(meals, meal -> meal.getProducts().contains(product));
    }

    /**
     * For a given List of meals return meals that does not contain any of the products
     *
     * @param meals
     * @param products that should be excluded from the menu
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products) {
        // could use Collections.disjoint
        return this.getAllByPredicate(meals, meal -> meal.getProducts().stream().anyMatch(products::contains));
    }
}
