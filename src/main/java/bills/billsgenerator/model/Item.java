package bills.billsgenerator.model;

public class Item {


    long id;
    String name;
    float price;

    //I will not model this thing now, let's suppose we don't want to see all the bills corresponding to an item
    //List<Bill> bills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
