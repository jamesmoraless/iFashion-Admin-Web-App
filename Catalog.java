package se2203b.assignments.adminapp;

import javafx.beans.property.*;


public class Catalog
{
    private StringProperty name;
    private IntegerProperty id;

    public void setId(int num) { id.set(num); }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }


    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    //Constructor for Catalog
    public Catalog(int id, String name)
    {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

}
