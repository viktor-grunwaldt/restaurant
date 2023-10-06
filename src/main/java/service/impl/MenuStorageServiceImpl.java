package service.impl;

import domain.DietType;
import domain.eto.Meal;
import domain.eto.Produce;

import java.util.List;

public class MenuStorageServiceImpl extends MenuServiceImpl{
    private boolean canMealBePreparedFromProductsInCommonStorage(){
        // Not Implemented
        return false;

    }
    // Nadpisz wszystkie metody z MenuServiceImpl, i w ciałach tych metod
    // wywołaj ich metody z superklasy (słowo kluczowe super),
    // a następnie sprawdź czy wyfiltrowana lista może być przygotowana,
    // używając canMealBePreparedFromProductsInCommonStorage.
    // Napisz Testy jednostkowe używając pomocniczo metody z adnotacją @beforeeach.
    // W tej metodzie uzupełnij CommonStorage odpowiednimi produktami.
    /**
     * For a given List of meals, find all meals that are of dietType = {@link DietType#VEGETARIAN}.
     * @param meals list of meals to search from
     * @return list of found meals
     */
    @Override
    public List<Meal> findVegetarianFood (List<Meal> meals) {
    }

    /**
     * For a given List of meals, find all meals that are of certain dietType
     * @param meals list of meals to search from
     * @param diet diet type to filter with
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodByType(List<Meal> meals, DietType diet) {
    }

    /**
     * For a given List of meals return meals that cost less than given parameter.
     * @param meals list of meals to search from
     * @param price upper boundary of the prices of found meals
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodCheaperThan(List<Meal> meals, Integer price) {
    }

    /**
     * For a given List of meals return meals that have calorie intake between two values
     * @param meals list of meals to search from
     * @param minCalories lower boundary of the prices of found meals
     * @param maxCalories upper boundary of the prices of found meals
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodWithCalories(List<Meal> meals, Integer minCalories, Integer maxCalories) {
    }

    /**
     * For a given List of meals return meals that name starts with a String. This method ignores character case when
     * searching.
     * @param meals list of meals to search from
     * @param name  - can be a partial String
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodStartingWithName(List<Meal> meals, String name) {
    }

    /**
     * For a given List of meals return meals that does contain certain product
     * @param meals list of meals to search from
     * @param product product that is in the meal
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodContaining(List<Meal> meals, Produce product) {
    }

    /**
     * For a given List of meals return meals that does not contain any of the products
     *
     * @param meals list of meals to search from
     * @param products that should be excluded from the menu
     * @return list of found meals
     */
    @Override
    public List<Meal> findFoodExcludingAll(List<Meal> meals, List<Produce> products) {
    }
}
