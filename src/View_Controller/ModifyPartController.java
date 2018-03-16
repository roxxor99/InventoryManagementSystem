package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
public class ModifyPartController implements Initializable {

    @FXML
    private RadioButton ModifyPartsInRadio;
    @FXML
    private ToggleGroup ModifyPartRadio;
    @FXML
    private RadioButton ModifyPartsOutRadio;
    @FXML
    private TextField ModifyPartsID;
    @FXML
    private TextField ModifyPartsName;
    @FXML
    private TextField ModifyPartsInv;
    @FXML
    private TextField ModifyPartsPrice;
    @FXML
    private TextField ModifyPartsMax;
    @FXML
    private TextField ModifyPartsMin;
    @FXML
    private TextField ModifyPartsLocation;
    @FXML
    private Label ModifyPartsLocationLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Inventory.selectedPart instanceof InhousePart) {
            ModifyPartsInRadio.setSelected(true);
            ModifyPartsLocationLabel.setText("Machine ID");
            ModifyPartsLocation.setText(((InhousePart)Inventory.selectedPart).getPartMachineID()+ "");
        } else {
            ModifyPartsOutRadio.setSelected(true);
            ModifyPartsLocationLabel.setText("Company");
            ModifyPartsLocation.setText(((OutsourcedPart)Inventory.selectedPart).getPartCompanyName()+ "");
        }

        //populates textfields listed above
        ModifyPartsID.setText(Inventory.selectedPart.getPartID() + "");
        ModifyPartsInv.setText(Inventory.selectedPart.getPartInStock() + "");
        ModifyPartsPrice.setText(Inventory.selectedPart.getPartPrice() + "");
        ModifyPartsMax.setText(Inventory.selectedPart.getPartMax() + "");
        ModifyPartsMin.setText(Inventory.selectedPart.getPartMin() + "");
        ModifyPartsName.setText(Inventory.selectedPart.getPartName());
    }

    @FXML
    private void ModifyPartsInRadio(ActionEvent event) {
        ModifyPartsLocationLabel.setText("Machine ID");
        ModifyPartsLocation.setText("");
    }

    @FXML
    private void ModifyPartsOutRadio(ActionEvent event) {
        ModifyPartsLocationLabel.setText("Company");
        ModifyPartsLocation.setText("");
    }

    @FXML
    private void ModifyPartsSaveAction(ActionEvent event) throws IOException {
        Part p;
        String partName = ModifyPartsName.getText();
        int partInv = Integer.parseInt(ModifyPartsInv.getText());
        Double partPrice = Double.parseDouble(ModifyPartsPrice.getText());
        int partMax = Integer.parseInt(ModifyPartsMax.getText());
        int partMin = Integer.parseInt(ModifyPartsMin.getText());

        if (ModifyPartsInRadio.isSelected()) {
            p = new InhousePart();
            ((InhousePart) p).setPartMachineID(Integer.parseInt(ModifyPartsLocation.getText()));

        } else {
            p = new OutsourcedPart();
            ((OutsourcedPart) p).setPartCompanyName(ModifyPartsLocation.getText());
        }
        p.setPartID(Inventory.selectedPart.getPartID());
        Inventory.allParts.remove(Inventory.selectedPart);
        p.setPartName(partName);
        p.setPartInStock(partInv);
        p.setPartPrice(partPrice);
        p.setPartMax(partMax);
        p.setPartMin(partMin);
        Inventory.allParts.add(p);

        Stage stage;
        Parent root;
        stage = (Stage) ModifyPartsName.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ModifyPartsCancelAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you would like to cancel?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Stage stage;
            Parent root;
            stage = (Stage) ModifyPartsName.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
}
