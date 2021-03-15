package models;

public class CarPart {
    String partName;
    int quantity;

    public CarPart(String partName, int quantity) {
        this.partName = partName;
        this.quantity = quantity;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
