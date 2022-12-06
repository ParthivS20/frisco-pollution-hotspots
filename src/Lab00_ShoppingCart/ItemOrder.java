package Lab00_ShoppingCart;

class ItemOrder {
    private Item item;
    private int quantity;

    ItemOrder(Item item, int qty) {
        this.item = item;
        this.quantity = qty;
    }

    double getPrice() {
        return item.priceFor(quantity);
    }

    Item getItem() {
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
