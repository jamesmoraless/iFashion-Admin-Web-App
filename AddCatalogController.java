package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCatalogController
{
    @FXML
    Button saveButton;

    @FXML
    Button cancelButton;

    @FXML
    TextField brandName;

    private CatalogAdapter catalogAdapter;

    //Sets the models
    public void setModel(CatalogAdapter catalogAdapters)
    {
        this.catalogAdapter = catalogAdapters;
    }

    //Saves the entered data
    @FXML
    public void save()
    {
        try {
            catalogAdapter.insertCatalog(catalogAdapter.getMax(), brandName.getText());
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
}
