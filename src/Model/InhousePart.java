package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Jed Gunderson
 */
public class InhousePart extends Part{
    private final IntegerProperty machineID = new SimpleIntegerProperty();
     
    //constructor
    
    //Getters
    public int getPartMachineID(){
        return machineID.get();
    }
    //Setters
    public void setPartMachineID(int machineID){
        this.machineID.set(machineID);
    }
}
    

