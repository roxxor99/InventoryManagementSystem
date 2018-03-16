package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jed Gunderson
 */
public class Inventory {
    
    //need observable list for allParts and Products
    public static ObservableList <Part> allParts = FXCollections.observableArrayList();
    public static ObservableList <Product> products = FXCollections.observableArrayList();
    public static Part selectedPart;
    public static Product selectedProduct = new Product();
    
    public static int productCounter = 0;
    public static int partCounter = 0;
    
        //This functionality is handled within the controller.
//    public static void addProduct(Product product){
//                
//    }  
//    
//    public static boolean removeProduct(int){
//    
//    }
//    
//    public static Product lookupProduct(int){
//        
//    }
//    
//    public static void updateProduct(int){
//        
//    }
//    
//    public static void addPart(part){
//        
//    }
//    
//    public static boolean deletPart(part){
//        
//    }
//
//    public static Part lookupPart(int){
//        
//    }
//    
//    public static void updatePart(int){
//        
//    }
    
    
}
