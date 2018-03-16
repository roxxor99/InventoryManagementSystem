package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jed Gunderson
 */
public class MainScreenController {

    @FXML
    private Button MainPartsSearchBtn;
    @FXML
    private TextField MainPartsSearchBox;
    @FXML
    private TableView<Part> MainPartsTable;
    @FXML
    private TableColumn<Part, Integer> MainPartIDCol;
    @FXML
    private TableColumn<Part, String> MainPartNameCol;
    @FXML
    private TableColumn<Part, Integer> MainPartInvCol;
    @FXML
    private TableColumn<Part, Double> MainPartPriceCol;
    @FXML
    private Button MainPartsAddBtn;
    @FXML
    private Button MainPartsModifyBtn;
    @FXML
    private Button MainPartsDeleteBtn;
    @FXML
    private TableView<Product> MainProdTable;
    @FXML
    private Button MainProdSearchBtn;
    @FXML
    private TextField MainProdSearchBox;
    @FXML
    private TableColumn<Product, Integer> MainProductIDCol;
    @FXML
    private TableColumn<Product, String> MainProductNameCol;
    @FXML
    private TableColumn<Product, Integer> MainProductInvCol;
    @FXML
    private TableColumn<Product, Double> MainProductPriceCol;
    @FXML
    private Button MainProdAddBtn;
    @FXML
    private Button MainProdModifyBtn;
    @FXML
    private Button MainProdDeleteBtn;
    @FXML
    private Button MainExitBtn;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        MainPartIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        MainPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        MainPartInvCol.setCellValueFactory(cellData -> cellData.getValue().partInStockProperty().asObject());
        MainPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        MainPartsTable.setItems(Inventory.allParts);
        MainProdTable.setItems(Inventory.products);
        MainProductIDCol.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        MainProductNameCol.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        MainProductInvCol.setCellValueFactory(cellData -> cellData.getValue().productInStockProperty().asObject());
        MainProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
    }

    @FXML
    private void MainPartsSearchAction(ActionEvent event) {
        String searchText = MainPartsSearchBox.getText();
        FilteredList<Part> MainPartsSearchBoxResults = searchParts(searchText);
        SortedList<Part> sortedData = new SortedList<>(MainPartsSearchBoxResults);
        sortedData.comparatorProperty().bind(MainPartsTable.comparatorProperty());
        MainPartsTable.setItems(sortedData);
    }

    public FilteredList<Part> searchParts(String s) {
        return Inventory.allParts.filtered(p -> p.getPartName().toLowerCase().contains(s.toLowerCase()));
    }

    @FXML
    private void MainPartsAddAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) MainPartsSearchBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void MainPartsModifyAction(ActionEvent event) throws IOException {
        Inventory.selectedPart = MainPartsTable.getSelectionModel().getSelectedItem();
        Stage stage;
        Parent root;
        stage = (Stage) MainPartsSearchBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void MainPartsDeleteAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation Delete Dialog");
        alert.setContentText("Are you sure you want to delete the selected part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Inventory.allParts.remove(MainPartsTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void MainProdSearchAction(ActionEvent event) {
        String searchText = MainProdSearchBox.getText();
        FilteredList<Product> MainProdSearchBoxResults = searchProd(searchText);
        SortedList<Product> sortedData = new SortedList<>(MainProdSearchBoxResults);
        sortedData.comparatorProperty().bind(MainProdTable.comparatorProperty());
        MainProdTable.setItems(sortedData);
    }

    public FilteredList<Product> searchProd(String s) {
        return Inventory.products.filtered(p -> p.getProductName().toLowerCase().contains(s.toLowerCase()));
    }

    @FXML
    private void MainProdAddAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) MainProdSearchBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void MainProdModifyAction(ActionEvent event) throws IOException {
        Inventory.selectedProduct = MainProdTable.getSelectionModel().getSelectedItem();

        Stage stage;
        Parent root;
        stage = (Stage) MainProdSearchBox.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void MainProdDeleteAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation Delete Dialog");
        alert.setContentText("You are about to delete a product that has parts associated with it, are you sure you want to delete the selected product?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Inventory.products.remove(MainProdTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void MainExitAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation ");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }

    }
}
