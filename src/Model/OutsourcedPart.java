package Model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Jed Gunderson
 */
public class OutsourcedPart extends Part{
    private final StringProperty companyName = new SimpleStringProperty();
    
    //Constructor
    
    //Getters
    public String getPartCompanyName(){
        return companyName.get();
    }
    //Setters
    public void setPartCompanyName(String companyName){
        this.companyName.set(companyName);
    }
}