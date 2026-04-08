package com.riwi;

import com.riwi.model.CoffeeOrder;
import com.riwi.service.OrderManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");

        OrderManager orderManager = new OrderManager();

        CoffeeOrder coffeeOrder = new CoffeeOrder("1", "Andrés", "Latte");
        CoffeeOrder coffeeOrder2 = new CoffeeOrder("2", "Carlos", "Latte");
        CoffeeOrder coffeeOrder3 = new CoffeeOrder("3", "Luis", "Perico");
        CoffeeOrder coffeeOrder4 = new CoffeeOrder("4", "Stiven", "Matcha");

        orderManager.addOrder(coffeeOrder);
        orderManager.addOrder(coffeeOrder2);
        orderManager.addOrder(coffeeOrder3);
        orderManager.addOrder(coffeeOrder4);

        System.out.println("lista de ordenes");
        System.out.println(orderManager.orderList);

        System.out.println("Catalogo");
        System.out.println(orderManager.catalog);

        //System.out.println("se agrega al Catalogo");
        //orderManager.catalog.put("Milk", 5000);
        //orderManager.catalog.put("Latte", 8000);

        System.out.println("Orden Actual:");
        System.out.println(orderManager.serveNextOrder());

        System.out.println("lista de ordenes");
        System.out.println(orderManager.orderList);

        System.out.println("Ultima Orden:");
        System.out.println(orderManager.showLastOrder());

        System.out.println("Lista en reverso:");
        System.out.println(orderManager.getReverseList());

        System.out.println("Eliminar de la lista el id:" + coffeeOrder4.getId());
        System.out.println(orderManager.removeByCustomerId(coffeeOrder4.getId()));

        System.out.println("lista de ordenes actualizada");
        System.out.println(orderManager.orderList);
    }
}