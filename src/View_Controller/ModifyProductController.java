package View_Controller;

 

import Model.Inventory;

import Model.Part;

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

import javafx.scene.control.ButtonType;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.TextField;

import javafx.stage.Stage;

import Model.Product;

import java.io.IOException;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

 

/**

* FXML Controller class

*

* @author Jed Gunderson

*/

public class ModifyProductController implements Initializable {

 

    @FXML

    private TextField ModifyProductID;

    @FXML

    private TextField ModifyProductName;

    @FXML

    private TextField ModifyProductInv;

    @FXML

    private TextField ModifyProductPrice;

    @FXML

    private TextField ModifyProductMax;

    @FXML

    private TextField ModifyProductMin;

    @FXML

    private TextField ModifyProductSearchBox;

    @FXML

    private TableView<Part> ModifyProductDeleteTable;

    @FXML

    private TableColumn<Part, Integer> ModifyProductDeleteIDCol;

    @FXML

    private TableColumn<Part, String> ModifyProductDeleteNameCol;

    @FXML

    private TableColumn<Part, Integer> ModifyProductDeleteInvCol;

    @FXML

    private TableColumn<Part, Double> ModifyProductDeletePriceCol;

    @FXML

    private TableView<Part> ModifyProductAddTable;

    @FXML

    private TableColumn<Part, Integer> ModifyProductIDCol;

    @FXML

    private TableColumn<Part, String> ModifyProductNameCol;

    @FXML

    private TableColumn<Part, Integer> ModifyProductInvCol;

    @FXML

    private TableColumn<Part, Double> ModifyProductPriceCol;

 

    private ObservableList<Part> tempList = FXCollections.observableArrayList();

 

    /**

     * Initializes the controller class.

     */

    @Override

    public void initialize(URL url, ResourceBundle rb) {

 

        ModifyProductIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());

        ModifyProductNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());

        ModifyProductInvCol.setCellValueFactory(cellData -> cellData.getValue().partInStockProperty().asObject());

        ModifyProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());

        ModifyProductAddTable.setItems(Inventory.allParts);

 

        ModifyProductDeleteIDCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());

        ModifyProductDeleteNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());

        ModifyProductDeleteInvCol.setCellValueFactory(cellData -> cellData.getValue().partInStockProperty().asObject());

        ModifyProductDeletePriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());

        //!!!

        ModifyProductDeleteTable.setItems(Inventory.selectedProduct.getAssociatedParts());

        tempList = Inventory.selectedProduct.getAssociatedParts();

        System.out.println(Inventory.selectedProduct.getAssociatedParts());

        //ModifyProductDeleteTable.setItems(tempList);

        ModifyProductID.setText(Inventory.selectedProduct.getProductID() + "");

        ModifyProductName.setText(Inventory.selectedProduct.getProductName() + "");

        ModifyProductInv.setText(Inventory.selectedProduct.getProductInStock() + "");

        ModifyProductMin.setText(Inventory.selectedProduct.getProductMin() + "");

        ModifyProductMax.setText(Inventory.selectedProduct.getProductMax() + "");

        ModifyProductPrice.setText(Inventory.selectedProduct.getProductPrice() + "");

 

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

            max = Integer.parseInt(ModifyProductMax.getText());

            min = Integer.parseInt(ModifyProductMin.getText());

            inv = Integer.parseInt(ModifyProductInv.getText());

            price = Double.parseDouble(ModifyProductPrice.getText());

 

        } catch (NumberFormatException e) {

            msg += "Products must have a price, inventory, max and min numeric values\n";

        }

 

        if (max < min) {

            msg += "Maximum must be greater than minimum\n";

        }

 

        if (inv < min || inv > max) {

            msg += "Inventory must be between the min and max\n";

        }

 

        if (ModifyProductName.getText().equals("")) {

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

    private void ModifyProductSearchBtn(ActionEvent event) {

        String searchText = ModifyProductSearchBox.getText();

        FilteredList<Part> MainPartsSearchBoxResults = searchParts(searchText);

        SortedList<Part> sortedData = new SortedList<>(MainPartsSearchBoxResults);

        sortedData.comparatorProperty().bind(ModifyProductAddTable.comparatorProperty());

        ModifyProductAddTable.setItems(sortedData);

 

    }

 

    public FilteredList<Part> searchParts(String s) {

        return Inventory.allParts.filtered(p -> p.getPartName().toLowerCase().contains(s.toLowerCase()));

    }

 

    @FXML

    private void ModifyProductAddBtn(ActionEvent event) {

        tempList.add(ModifyProductAddTable.getSelectionModel().getSelectedItem());

        ModifyProductDeleteTable.setItems(tempList);

    }

 

    @FXML

    private void ModifyProductCancelBtn(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");

        alert.setHeaderText("Confirm Cancel");

        alert.setContentText("Are you sure you would like to cancel?");

        Optional<ButtonType> result = alert.showAndWait();

 

        if (result.get() == ButtonType.OK) {

            Stage stage;

            Parent root;

            stage = (Stage) ModifyProductName.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();

        }

    }

 

    @FXML

    private void ModifyProductDeleteBtn(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");

        alert.setHeaderText("Confirmation Delete Dialog");

        alert.setContentText("Are you sure you want to delete the selected part?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

 

//!!!!Deleting the entire table not just the item selected....

            tempList.remove(ModifyProductDeleteTable.getSelectionModel().getSelectedItem());

            //ModifyProductDeleteTable.setItems(Inventory.selectedProduct.getAssociatedParts());

            ModifyProductDeleteTable.setItems(tempList);

        }

    }

 

    @FXML

    private void ModifyProductSaveBtn(ActionEvent event) throws IOException {

        if (isValid() == true) {

            Product p;

            p = new Product();

            int productID = Integer.parseInt(ModifyProductID.getText());

            String productName = ModifyProductName.getText();

            int productInv = Integer.parseInt(ModifyProductInv.getText());

            Double productPrice = Double.parseDouble(ModifyProductPrice.getText());

            int productMax = Integer.parseInt(ModifyProductMax.getText());

            int productMin = Integer.parseInt(ModifyProductMin.getText());

 

            p.setProductName(productName);

            p.setProductInStock(productInv);

            p.setProductPrice(productPrice);

            p.setProductMax(productMax);

            p.setProductMin(productMin);

            p.setAssociatedParts(tempList);

            Inventory.products.remove(Inventory.selectedProduct);

            Inventory.products.add(p);

 

            Stage stage;

            Parent root;

            stage = (Stage) ModifyProductName.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();

        }

    }

}