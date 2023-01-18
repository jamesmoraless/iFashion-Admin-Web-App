package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EDCatalogController
{
    @FXML
    Button exitButton;

    @FXML
    Button deleteButton;

    @FXML
    Button updateButton;

    @FXML
    ComboBox<String> catalogBox;

    @FXML
    TextField name;

    private CatalogAdapter catalogAdapter;

    //Sets the models
    public void setModel(CatalogAdapter catalogAdapter)
    {
        this.catalogAdapter = catalogAdapter;
        buildComboBoxData();
    }

    //Closes the Window
    @FXML
    public void cancel()
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    //Deletes the selected entry
    @FXML
    public void delete() {
        try {
            catalogAdapter.deleteCatalog(Integer.parseInt(catalogBox.getValue().split("-")[0].trim()));
        } catch(SQLException ex){ /*displayAlert(ex.getMessage());*/ }

        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
    }

    //Updates the selected entry
    @FXML
    public void update() {
        try {
            catalogAdapter.updateCatalog(Integer.parseInt(catalogBox.getValue().split("-")[0].trim()),
                    name.getText().trim());
        } catch(SQLException ex){ /*displayAlert(ex.getMessage());*/ }

        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }

    //Populates the text fields
    @FXML
    public void populateFields() {
        Catalog cat = null;

        try {
            cat = catalogAdapter.lookUpCategory(Integer.parseInt(catalogBox.getValue().split("-")[0].trim()));
        } catch(SQLException ex){ /*displayAlert(ex.getMessage()); */}

        if (cat != null) {
            name.setText(cat.getName().trim());
        }
    }

    //Populating the combobox
    public void buildComboBoxData()
    {
        try {
            catalogBox.setItems(catalogAdapter.getCatalogNameList());
        } catch(SQLException ex){ /*displayAlert(ex.getMessage());*/ }
    }
    
}
