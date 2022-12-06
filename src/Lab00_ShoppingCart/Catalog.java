package Lab00_ShoppingCart;

import java.util.ArrayList;

class Catalog {
    private String name;
    private ArrayList<Item> list;

    Catalog(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    void add(Item item) {
        list.add(item);
    }

    int size() {
        return list.size();
    }

    Item get(int index) {
        return list.get(index);
    }

    String getName() {
        return name;
    }
}
