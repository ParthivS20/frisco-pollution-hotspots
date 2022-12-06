package Lab00_ShoppingCart;

import java.util.ArrayList;

class ShoppingCart {
    private ArrayList<ItemOrder> orders;

    ShoppingCart() {
        this.orders = new ArrayList<>();
    }

    void add(ItemOrder newOrder) {
        for(int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getItem().equals(newOrder.getItem())) {
                orders.set(i, newOrder);
                return;
            }
        }

        orders.add(newOrder);
    }

    double getTotal() {
        double total = 0;

        for(ItemOrder order : orders) {
            total += order.getPrice();
        }

        return total;
    }
}
