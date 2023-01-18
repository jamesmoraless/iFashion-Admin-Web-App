package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddProductController
{
    @FXML
    Button cancelButton;

    @FXML
    Button saveButton;

    @FXML
    TextField name;

    @FXML
    TextField description;

    @FXML
    TextField productNumber;

    @FXML
    TextField brand;

    @FXML
    TextField productQty;

    @FXML
    ComboBox<String> catalogBox;

    private ProductAdapter productAdapter;
    private CatalogAdapter catalogAdapter;


    //Sets the models
    public void setModel(ProductAdapter productAdapter, CatalogAdapter catalogAdapter){
        this.productAdapter = productAdapter;
        this.catalogAdapter = catalogAdapter;
        buildComboBoxData();
    }

    //Saves the entered data
    @FXML
    public void save() {
        try {
            productAdapter.insertProduct(productAdapter.getMax(),
                    name.getText(),
                    description.getText(),
                    Integer.parseInt(productNumber.getText()),
                    brand.getText(),
                    Integer.parseInt(productQty.getText()),
                    Integer.parseInt(catalogBox.getValue().split(" - ")[0].trim()));
        } catch(SQLException ex){ /*displayAlert(ex.getMessage());*/ }

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    //Closes the window
    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    //Helps with populating the combobox
    public void buildComboBoxData() {
        try {
            catalogBox.setItems(catalogAdapter.getCatalogNameList());
        } catch(SQLException ex){}
    }




}
