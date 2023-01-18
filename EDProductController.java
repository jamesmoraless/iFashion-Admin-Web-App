package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EDProductController
{
    @FXML
    Button exitButton;

    @FXML
    Button deleteButton;

    @FXML
    Button updateButton;

    @FXML
    ComboBox<String> productComboBox;

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

    //Sets the model
    public void setModel(ProductAdapter productAdapter, CatalogAdapter catalogAdapter){
        this.productAdapter = productAdapter;
        this.catalogAdapter = catalogAdapter;
        buildComboBox();
    }

    //Closes the window
    @FXML
    public void cancel() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    //Deletes the selected entry
    @FXML
    public void delete() {
        try {
            productAdapter.deleteProduct(Integer.parseInt(productComboBox.getValue().split(" - ")[0].trim()));
        } catch(SQLException ex){ /*displayAlert(ex.getMessage());*/ }

        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
    }

    //Updates the selected entry
    @FXML
    public void update() {
        try {
            productAdapter.updateProduct(
                    Integer.parseInt(productComboBox.getValue().split(" - ")[0].trim()),
                    name.getText().trim(),
                    description.getText().trim(),
                    Integer.parseInt(productNumber.getText()),
                    brand.getText().trim(),
                    Integer.parseInt(productQty.getText()),
                    Integer.parseInt(catalogBox.getValue().split(" - ")[0].trim()));
        } catch(SQLException ex){ /*displayAlert(ex.getMessage());*/ }

        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }

    //Populates the text fields
    @FXML
    public void populateFields() {
        Product product = null;
        Catalog catalog = null;

        try {
            product = productAdapter.lookUpProduct(Integer.parseInt(productComboBox.getValue().split(" - ")[0].trim()));
            catalog = catalogAdapter.lookUpCategory(product.getCatId());
        } catch(SQLException ex){ /*displayAlert(ex.getMessage());*/ }

        if (product != null && catalog != null){
            name.setText(product.getName().trim());
            description.setText(product.getDescription().trim());
            productNumber.setText(product.getProductNo()+"");
            brand.setText(product.getBrand().trim());

            productQty.setText(product.getProductQty()+"");
            catalogBox.setValue(catalog.getId()+" - "+ catalog.getName());
        }
    }

    //Helps with populating the combobox
    public void buildComboBox() {
        try {
            productComboBox.setItems(productAdapter.getProductList());
            catalogBox.setItems(catalogAdapter.getCatalogNameList());
        } catch(SQLException ex){
            System.out.println("Didn't build");
            /*displayAlert(ex.getMessage());*/ }
    }










}
