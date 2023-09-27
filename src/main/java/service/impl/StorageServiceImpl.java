package service.impl;

import domain.eto.Meal;
import domain.eto.Storage;
import service.api.StorageService;
import service.exception.NoFoodFoundException;

import java.util.function.Function;
import java.util.stream.Collectors;

public class StorageServiceImpl implements StorageService {
    /**
     * For a given Storage state, find out if it's possible to prepare a meal,
     * if it can't, throw {@link service.exception.NoFoodFoundException}
     * @param storage contains current item stock
     * @param meal the meal with a list of ingredients
     */
    @Override
    public void canMealBePreparedFromProductsInStorage(Storage storage, Meal meal) {
        if (storage == null || meal == null){
            throw new NoFoodFoundException();
        }
        boolean result = meal
                .getProducts()
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .allMatch(storage::containsEnoughProduct);
        if (!result) {
            throw new NoFoodFoundException();
        }
    }
}
