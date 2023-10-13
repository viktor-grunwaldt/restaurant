package service.impl;

import domain.eto.Meal;
import domain.eto.Storage;
import helpers.VoidSupplier;
import service.exception.NoFoodFoundException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MenuStorageServiceImpl extends MenuServiceImpl{
    private void canMealBePreparedFromProductsInCommonStorage(Meal meal){
        if (meal == null){
            throw new NoFoodFoundException();
        }
        boolean result = meal
                .getProducts()
                .stream()
                .allMatch(Storage.CommonStorage::checkInStorage);
        if (!result) {
            throw new NoFoodFoundException();
        }
    }
    private boolean isThrowingAnException(VoidSupplier voidSupplier){
        try {
            voidSupplier.get();
            return true;
        } catch (Exception e){
            return false;
        }
    }
    /**
     * For a given List of meals, find all meals that satisfy the predicate fn
     * This function will also query the contents of CommonStorage to verify whether there are enough
     * ingredients to create each meal.
     * @param meals list of meals to search from
     * @param getterNonNull predicate function to check whether getter returns null
     * @param fn predicate function to filter with
     * @return list of found meals
     */
    @Override
    protected List<Meal> getAllByPredicateWithTypeChecking(List<Meal> meals, Predicate<Meal> getterNonNull , Predicate<Meal> fn){
        List<Meal> res = super.getAllByPredicateWithTypeChecking(meals, getterNonNull ,fn);
        Predicate<Meal> canMealBePrepared = (Meal m) -> isThrowingAnException(()->canMealBePreparedFromProductsInCommonStorage(m));
        List<Meal> preparableMeals =  res.stream().filter(canMealBePrepared).collect(Collectors.toList());
        if (preparableMeals.isEmpty()){
            throw new NoFoodFoundException();
        }
        return preparableMeals;
    }
    // Nadpisz wszystkie metody z MenuServiceImpl, i w ciałach tych metod
    // wywołaj ich metody z superklasy (słowo kluczowe super),
    // a następnie sprawdź czy wyfiltrowana lista może być przygotowana,
    // używając canMealBePreparedFromProductsInCommonStorage.
    // Napisz Testy jednostkowe używając pomocniczo metody z adnotacją @beforeeach.
    // W tej metodzie uzupełnij CommonStorage odpowiednimi produktami.
}
