package com.riwi.service;

import com.riwi.model.CoffeeOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManager {

    public final ArrayList<CoffeeOrder> orderList = new ArrayList<>();
    final HashMap<String, CoffeeOrder> orderQueue  = new HashMap<>();

    public Map<String, Integer> catalog = Map.of("Latte", 4000, "Perico", 2000, "Matcha", 10000);

    public void addOrder(CoffeeOrder coffeeOrder){
        orderList.add(coffeeOrder);
        orderQueue.put(coffeeOrder.getId(), coffeeOrder);
    }

    public CoffeeOrder serveNextOrder(){
        var nextOrder = orderList.getFirst();
        orderList.removeFirst();
        orderQueue.remove(nextOrder.getId());
        return nextOrder;
    }

    public CoffeeOrder showLastOrder(){
        return orderList.getLast();
    }

    public List<CoffeeOrder> getReverseList(){
        return orderList.reversed();
    }

    public boolean removeByCustomerId(String id){
        orderQueue.remove(id);
        return orderList.removeIf(order -> order.getId().equals(id));
    }

}
