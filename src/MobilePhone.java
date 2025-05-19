import java.io.Serializable;

enum MobileType {
    Android,
    IOS,
    Windows
}

public class MobilePhone implements Cloneable, Serializable {
    protected String model;
    protected MobileType type;
    protected int memorySize;
    protected double price;

    public MobilePhone(String model, MobileType type, int memorySize, double price) {
        this.model = model;
        this.type = type;
        this.memorySize = memorySize;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public MobileType getType() {
        return type;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String toString() {
        return "Model: " + model + " Type: " + type + " MemorySize: " + memorySize + " Price: " + price;
    }

    public void print() {
        System.out.print("Model: " + model + " Type: " + type + " MemorySize: " + memorySize + " Price: " + price);
    }

    public void priceRice(double rise) {
        price *= (1 + rise);
    }

    // Lab4
    public MobilePhone(MobilePhone mobilePhone) {
        this.model = mobilePhone.model;
        this.type = mobilePhone.type;
        this.memorySize = mobilePhone.memorySize;
        this.price = mobilePhone.price;
    }

    public MobilePhone clone() throws CloneNotSupportedException {
        return (MobilePhone) super.clone();
    }

    // Lab6
    public String toDilimatedString() {
        return model + "," + type + "," + memorySize + "," + price;
    }
}
