package se2203b.assignments.adminapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author James Morales
 */
public class MainApplicationController implements Initializable {
    private CatalogAdapter catalog;
    private ProductAdapter product;
    //private SubCategoryAdapter subCategory;
    private Connection conn;

    @FXML
    private MenuBar mainMenu;


    public void showAbout() throws Exception
    {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("About-view.fxml"));
        // create the root node
        Parent About =  fxmlLoader.load();
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showAddCatalog() throws Exception
    {
        catalog = new CatalogAdapter(conn, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("AddCatalog-view.fxml"));
        // create the root node
        Parent About =  fxmlLoader.load();

        AddCatalogController addCatController = fxmlLoader.getController();
        addCatController.setModel(catalog);

        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Add Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showEDCatalog() throws Exception
    {
        catalog = new CatalogAdapter(conn, false);

        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("EDCatalog-view.fxml"));
        // create the root node
        Parent About =  fxmlLoader.load();

        EDCatalogController eDCatController = fxmlLoader.getController();
        eDCatController.setModel(catalog);

        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Edit/Delete Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showAddProduct() throws Exception
    {
        product = new ProductAdapter(conn, false);
        catalog = new CatalogAdapter(conn, false);

        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("AddProduct-view.fxml"));
        // create the root node
        Parent About =  fxmlLoader.load();

        AddProductController addProductController = fxmlLoader.getController();
        addProductController.setModel(product, catalog);


        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Add Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void showEDProduct() throws Exception
    {
        product = new ProductAdapter(conn, false);
        catalog = new CatalogAdapter(conn, false);


        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("EDProduct-view.fxml"));
        // create the root node
        Parent About =  fxmlLoader.load();

        EDProductController editDeleteProductController = fxmlLoader.getController();
        editDeleteProductController.setModel(product, catalog);


        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Edit/Delete Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    public void exit()
    {
        // Get current stage reference
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        // Close stage
        stage.close();
    }

    //Resets the database through the tables
    @FXML
    public void resetDB() {
        try {
            catalog = new CatalogAdapter(conn, true);
        } catch (SQLException ex) {
        }
        try {
            product = new ProductAdapter(conn, true);
        } catch (SQLException ex) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iFashionStoreDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            /*displayAlert(ex.getMessage());*/
        }
    }

}
