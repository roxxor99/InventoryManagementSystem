package jed.gunderson;

import Model.InhousePart;
import Model.Inventory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Jed Gunderson
 */
public class JedGunderson extends Application {

    //constructor
    public JedGunderson() {
        Inventory.allParts.add(new InhousePart());
        InhousePart ihp2 = new InhousePart();
        ihp2.setPartName("Gear");
        ihp2.setPartPrice(15);
        ihp2.setPartInStock(100);
        ihp2.setPartMax(500);
        ihp2.setPartMin(1);
        ihp2.setPartMachineID(23);
        Inventory.allParts.add(ihp2);
        
        
        //add sample data here for testing purposes
        InhousePart ihp1 = new InhousePart();
        ihp1.setPartName("Widget");
        ihp1.setPartPrice(25);
        ihp1.setPartInStock(100);
        ihp1.setPartMax(500);
        ihp1.setPartMin(1);
        ihp1.setPartMachineID(14);
        Inventory.allParts.add(ihp1);
    }
    
    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
