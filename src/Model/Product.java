
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jed Gunderson
 */
public class Product {
    
    private IntegerProperty productID = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final IntegerProperty inStock = new SimpleIntegerProperty();
    private final IntegerProperty min = new SimpleIntegerProperty();
    private final IntegerProperty max = new SimpleIntegerProperty();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    //constructor
     public Product(){
        productID = new SimpleIntegerProperty(Inventory.products.size()+1);
        name = new SimpleStringProperty("");
        Inventory.productCounter ++;
//        price = new SimpleDoubleProperty();
//        inStock = new SimpleIntegerProperty();
    }
     
    //Getters - Returns the current value
    public String getProductName() {
        return name.get();
    }
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    public StringProperty productNameProperty() {
        return name;
    }

    public double getProductPrice() {
        return price.get();
    }
    
    public DoubleProperty productPriceProperty() {
        return price;
    }

    public int getProductInStock() {
        return inStock.get();
    }
    
    public IntegerProperty productInStockProperty() {
        return inStock;
    }

    public int getProductMin() {
        return min.get();
    }

    public int getProductMax() {
        return max.get();
    }

    public int getProductID() {
        return productID.get();
    }
    
    public IntegerProperty productIDProperty() {
        return productID;
    }

    //Setters - Allows a caller to set the value
    public void setAssociatedParts(ObservableList<Part> partList) {
       this.associatedParts = partList; 
    }
    
    public void setProductName(String name) {
        this.name.set(name);
    }

    public void setProductPrice(double price) {
        this.price.set(price);
    }

    public void setProductInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public void setProductMin(int min) {
        this.min.set(min);
    }

    public void setProductMax(int max) {
        this.max.set(max);
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
    }
    
}
