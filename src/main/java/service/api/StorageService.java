package service.api;

import domain.eto.Meal;
import domain.eto.Storage;

public interface StorageService {
    /**
     * For a given Storage state, find out if it's possible to prepare a meal,
     * if it can't, throw {@link service.exception.NoFoodFoundException}
     * @param storage contains current item stock
     * @param meal the meal with a list of ingredients
     */
    void canMealBePreparedFromProductsInStorage(Storage storage, Meal meal);
}
