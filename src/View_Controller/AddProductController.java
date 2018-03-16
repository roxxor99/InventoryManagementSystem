package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField AddProductID;
    @FXML
    private TextField AddProductName;
    @FXML
    private TextField AddProductInv;
    @FXML
    private TextField AddProductPrice;
    @FXML
    private TextField AddProductMax;
    @FXML
    private TextField AddProductMin;
    @FXML
    private TextField AddProductSearchBox;
    @FXML
    private Button AddProductSearchBtn;
    @FXML
    private TableView<Part> AddProductDeleteTable;
    @FXML
    private TableColumn<Part, Integer> AddProductDeleteIDCol;
    @FXML
    private TableColumn<Part, String> AddProductDeleteNameCol;
    @FXML
    private TableColumn<Part, Integer> AddProductDeleteInvCol;
    @FXML
    private TableColumn<Part, Double> AddProductDeletePriceCol;
    @FXML
    private Button AddProductAddBtn;
    @FXML
    private Button AddProductCancelBtn;
    @FXML
    private TableView<Part> AddProductAddTable;
    @FXML
    private TableColumn<Part, Integer> AddProductIDCol;
    @FXML
    private TableColumn<Part, String> AddProductNameCol;
    @FXML
    private TableColumn<Part, Integer> AddProductInvCol;
    @FXML
    private TableColumn<Part, Double> AddProductPriceCol;
    @FXML
    private Button AddProductDeleteBtn;
    @FXML
    private Button AddProductSaveBtn;

    private ObservableList<Part> tempList = FXCollections.observableArrayList();
    //private String exceptionMessage = new String();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AddProductIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        AddProductNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        AddProductInvCol.setCellValueFactory(cellData -> cellData.getValue().partInStockProperty().asObject());
        AddProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        AddProductAddTable.setItems(Inventory.allParts);

        AddProductDeleteTable.setItems(Inventory.allParts);
        AddProductDeleteIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        AddProductDeleteNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        AddProductDeleteInvCol.setCellValueFactory(cellData -> cellData.getValue().partInStockProperty().asObject());
        AddProductDeletePriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        AddProductDeleteTable.setItems(tempList);
    }

    public boolean isValid() {

        String msg = "";
        int max = 0;
        int min = 0;
        int inv = 0;
        double price = 0.0;
        double sumParts = 0.00;
        for (int i = 0; i < tempList.size(); i++) {
            sumParts = sumParts + tempList.get(i).getPartPrice();
        }

        try {
            max = Integer.parseInt(AddProductMax.getText());
            min = Integer.parseInt(AddProductMin.getText());
            inv = Integer.parseInt(AddProductInv.getText());
            price = Double.parseDouble(AddProductPrice.getText());

        } catch (NumberFormatException e) {
            msg += "Products must have a price, inventory, max and min numeric values\n";
        }

        if (max < min) {
            msg += "Maximum must be greater than minimum\n";
        }

        if (inv < min || inv > max) {
            msg += "Inventory must be between the min and max\n";
        }

        if (AddProductName.getText().equals("")) {
            msg += "Products must have a name\n";
        }

        if (tempList.size() < 1) {
            msg += ("Product must contain at least one part.");
        }

        if (sumParts > price) {
            msg += ("Product price must cannot be less than the cost of associated parts.");
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

    @FXML
    private void AddProductSearchAction(ActionEvent event) {
        String searchText = AddProductSearchBox.getText();
        FilteredList<Part> MainPartsSearchBoxResults = searchParts(searchText);
        SortedList<Part> sortedData = new SortedList<>(MainPartsSearchBoxResults);
        sortedData.comparatorProperty().bind(AddProductAddTable.comparatorProperty());
        AddProductAddTable.setItems(sortedData);
    }

    public FilteredList<Part> searchParts(String s) {
        return Inventory.allParts.filtered(p -> p.getPartName().toLowerCase().contains(s.toLowerCase()));
    }

    @FXML
    private void AddProductAddAction(ActionEvent event) throws IOException {
        if (AddProductAddTable.getSelectionModel().getSelectedItem() != null)
        tempList.add(AddProductAddTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void AddProductCancelAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Cancel");
        alert.setContentText("Are you sure you would like to cancel?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Stage stage;
            Parent root;
            stage = (Stage) AddProductName.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void AddProductDeleteAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation Delete Dialog");
        alert.setContentText("Are you sure you want to delete the selected part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
//            Inventory.products.remove(Inventory.selectedProduct);
            tempList.remove(AddProductDeleteTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void AddProductSaveAction(ActionEvent event) throws IOException {
        if (isValid() == true) {
            Product p;
            p = new Product();
            String productName = AddProductName.getText();
            int productInv = Integer.parseInt(AddProductInv.getText());
            Double productPrice = Double.parseDouble(AddProductPrice.getText());
            int productMax = Integer.parseInt(AddProductMax.getText());
            int productMin = Integer.parseInt(AddProductMin.getText());

            p.setProductName(productName);
            p.setProductInStock(productInv);
            p.setProductPrice(productPrice);
            p.setProductMax(productMax);
            p.setProductMin(productMin);
            p.setAssociatedParts(tempList);

            Inventory.products.add(p);

            Stage stage;
            Parent root;
            stage = (Stage) AddProductName.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
