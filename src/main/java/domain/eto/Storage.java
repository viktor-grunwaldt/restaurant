package domain.eto;

import service.exception.NoFoodFoundException;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private final Map<Produce, Integer> storage;

    public Storage(){
        this.storage = new HashMap<>();
    }

    public int getProductCount(Produce query){
        return this.storage.getOrDefault(query, 0);
    }
    public void addNewProduct(Produce query){
        this.storage.putIfAbsent(query,1);
    }
    public int incrementProduct(Produce query){
        Integer val = this.storage.get(query);
        if (val == null){
            this.addNewProduct(query);
            return 1;
        }
        this.storage.put(query, val+1);
        return val+1;
    }

    public void setProduct(Produce query, Integer value) {
        if(query == null || value == null){
            throw new NoFoodFoundException();
        }
        if (value < 1){
            throw new IllegalArgumentException();
        }
        this.storage.put(query, value);
    }

    public boolean containsEnoughProduct(Map.Entry<Produce, Long> query){
        return this.storage.getOrDefault(query.getKey(),0) >= query.getValue();
    }
}
