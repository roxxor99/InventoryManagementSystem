package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Jed Gunderson
 */

//Abstract class
public abstract class Part {
    
    //Variables 
    private IntegerProperty partID = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty inStock = new SimpleIntegerProperty();
    private final IntegerProperty min = new SimpleIntegerProperty();
    private final IntegerProperty max = new SimpleIntegerProperty();

    //constructor
    public Part(){
        partID = new SimpleIntegerProperty(Inventory.allParts.size()+1);
        name = new SimpleStringProperty("");
        price = new SimpleDoubleProperty();
        inStock = new SimpleIntegerProperty();
        Inventory.partCounter ++;
    }

    //Getters - Returns the current value
    public String getPartName() {
        return name.get();
    }
    
    public StringProperty partNameProperty() {
        return name;
    }

    public double getPartPrice() {
        return price.get();
    }
    
    public DoubleProperty partPriceProperty() {
        return price;
    }

    public int getPartInStock() {
        return inStock.get();
    }
    
    public IntegerProperty partInStockProperty() {
        return inStock;
    }
    
    public int getPartMin() {
        return min.get();
    }

    public int getPartMax() {
        return max.get();
    }

    public Integer getPartID() {
        return partID.get();
    }
    
    public IntegerProperty partIDProperty(){
        return partID;
    }
   
    

    //Setters - Allows a caller to set the value
    public void setPartName(String name) {
        this.name.set(name);
    }

    public void setPartPrice(double price) {
        this.price.set(price);
    }

    public void setPartInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public void setPartMin(int min) {
        this.min.set(min);
    }

    public void setPartMax(int max) {
        this.max.set(max);
    }

    public void setPartID(int partID) {
        this.partID.set(partID);
    }
}
