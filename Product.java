package se2203b.assignments.adminapp;

import javafx.beans.property.*;

public class Product {
    private IntegerProperty productId;
    private StringProperty name;
    private StringProperty description;
    private IntegerProperty productNo;
    private StringProperty brand;
    private IntegerProperty productQty;
    private ObjectProperty<Catalog> catalog;
    private IntegerProperty catalogID;

    //Getters and setters
    public void setCatalogID(int catID) {

        this.catalogID.set(catID);
    }

    public IntegerProperty catIDProperty() {
        return catalogID;
    }

    public int getCatId()
    {
        return catalogID.get();
    }



    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    public IntegerProperty productIdProperty() {
        return productId;
    }

    public int getProductId() {
        return productId.get();
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

    public void setDescription(String des) {
        description.set(des);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public void setProductNo(int num) {
        productNo.set(num);
    }

    public IntegerProperty productNoProperty() {
        return productNo;
    }

    public int getProductNo() {
        return productNo.get();
    }

    public void setBrand(String bran) {
        brand.set(bran);
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public String getBrand() {
        return brand.get();
    }

    public void setProductQty(int num) {
        productQty.set(num);
    }

    public IntegerProperty productQtyProperty() {
        return productQty;
    }

    public int getProductQty() {
        return productQty.get();
    }

    public void setCatalog(Catalog catalog)
    {
        this.catalog.set(catalog);
    }

    public ObjectProperty<Catalog> catalogProperty() {
        return catalog;
    }

    public Catalog getCatalog()
    {
        return catalog.get();
    }

    //A constructor for title
    public Product(int productId, String name, String description, int productNo,String brand, int productQty, int catalogID) {
        this.productId = new SimpleIntegerProperty(productId);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.productNo = new SimpleIntegerProperty(productNo);
        this.brand = new SimpleStringProperty(brand);
        this.productQty = new SimpleIntegerProperty(productQty);
        this.catalogID = new SimpleIntegerProperty(catalogID);
        this.catalog = new SimpleObjectProperty<>();
    }

}
