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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
public class AddPartController implements Initializable {

    @FXML
    private RadioButton AddPartsInRadio;
    @FXML
    private ToggleGroup radioBtnGroup;
    @FXML
    private RadioButton AddPartsOutRadio;
    @FXML
    private TextField AddPartsID;
    @FXML
    private Label AddPartsLocationLabel;
    @FXML
    private TextField AddPartsLocation;
    @FXML
    private TextField AddPartsName;
    @FXML
    private TextField AddPartsInv;
    @FXML
    private TextField AddPartsPrice;
    @FXML
    private TextField AddPartsMax;
    @FXML
    private TextField AddPartsMin;

    //private String exceptionMsg = new String();

    public boolean isValid() {

        String msg = "";
        int max = 0;
        int min = 0;
        int inv = 0;
        double price = 0.0;

        try {
            max = Integer.parseInt(AddPartsMax.getText());
            min = Integer.parseInt(AddPartsMin.getText());
            inv = Integer.parseInt(AddPartsInv.getText());
            price = Double.parseDouble(AddPartsPrice.getText());

        } catch (NumberFormatException e) {
            msg += "Parts must have a price, inventory, max and min numeric values\n";
        }

        if (max < min) {
            msg += "Maximum must be greater than minimum\n";
        }

        if (inv < min || inv > max) {
            msg += "Inventory must be between the min and max\n";
        }

        if (AddPartsName.getText().equals("")) {
            msg += "Parts must have a name\n";
        }

        if (!(msg.equals(""))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText("Error!");
            alert.setContentText(msg);
            alert.showAndWait();

            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AddPartsInRadio(ActionEvent event) {
        AddPartsLocationLabel.setText("Machine ID");
        AddPartsLocation.setText("");
    }

    @FXML
    private void AddPartsOutRadio(ActionEvent event) {
        AddPartsLocationLabel.setText("Company Name");
        AddPartsLocation.setText("");
    }

    @FXML
    private void AddPartsSaveAction(ActionEvent event) throws IOException {
        if (isValid() == true) {

            Part p;
            String partName = AddPartsName.getText();
            int partInv = Integer.parseInt(AddPartsInv.getText());
            Double partPrice = Double.parseDouble(AddPartsPrice.getText());
            int partMax = Integer.parseInt(AddPartsMax.getText());
            int partMin = Integer.parseInt(AddPartsMin.getText());
            if (AddPartsInRadio.isSelected()) {
                p = new InhousePart();
                ((InhousePart) p).setPartMachineID(Integer.parseInt(AddPartsLocation.getText()));

            } else {
                p = new OutsourcedPart();
                ((OutsourcedPart) p).setPartCompanyName(AddPartsLocation.getText());
            }
            p.setPartName(partName);
            p.setPartInStock(partInv);
            p.setPartPrice(partPrice);
            p.setPartMax(partMax);
            p.setPartMin(partMin);
            Inventory.allParts.add(p);
        }
        Stage stage;
        Parent root;
        stage = (Stage) AddPartsName.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AddPartsCancelAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you would like to cancel?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Stage stage;
            Parent root;
            stage = (Stage) AddPartsName.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
