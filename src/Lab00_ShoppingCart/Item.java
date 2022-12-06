package Lab00_ShoppingCart;

class Item {
    private String name;
    private double price;
    private int bulkQty;
    private double bulkPrice;

    Item (String name, double price) {
        this(name, price, 0, 0);
    }

    Item (String name, double price, int bulkQty, double bulkPrice) {
        if(price < 0 || bulkQty < 0 || bulkPrice < 0) throw new IllegalArgumentException("error");

        this.name = name;
        this.price = price;
        this.bulkQty = bulkQty;
        this.bulkPrice = bulkPrice;
    }

    double priceFor(int quantity) {
        if(bulkQty > 0 && quantity >= bulkQty) {
            return quantity * bulkPrice;
        }
        return quantity * price;
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        Item other = (Item) obj;
        return other.getName().equals(this.getName());
    }

    @Override
    public String toString() {
        String x = getName() + " " + "$" + priceFormatter(price);
        if(bulkQty > 0) {
            x += " (" + bulkQty + " for $" + priceFormatter(bulkPrice) + ")";
        }

        return x;
    }

    private String priceFormatter(double price) {
        return String. format("%.2f", Math.round(price * 100) / 100.0);
    }
}