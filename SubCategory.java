package se2203b.assignments.adminapp;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class SubCategory
{
    private IntegerProperty subCategoryId;
    private StringProperty name;
    private ObjectProperty<Catalog> relatedUnit;
    private IntegerProperty relatedUnitId;

    public ObservableList<Product> getProduct() {
        return product.get();
    }

    public ListProperty<Product> productProperty() {
        return product;
    }

    public void setProduct(ObservableList<Product> product) {
        this.product.set(product);
    }

    private ListProperty<Product> product;

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId.set(subCategoryId);
    }

    public IntegerProperty subCategoryIdProperty() {
        return subCategoryId;
    }

    public int getSubCategoryId() {

        return subCategoryId.get();
    }

    public void setRelatedUnit(Catalog cat) { relatedUnit.set(cat); }

    public ObjectProperty<Catalog> relatedUnitProperty() {
        return relatedUnit;
    }

    public Catalog getRelatedUnit() {
        return relatedUnit.get();
    }

    public void setRelatedUnitId(int id) {
        this.relatedUnitId.set(id);
    }

    public IntegerProperty relatedUnitIdProperty() {
        return relatedUnitId;
    }

    public int getRelatedUnitId() {
        return relatedUnitId.get();
    }

    //A constructor for SubCategory
    public SubCategory(int subCategoryId, String name, int relatedUnitId){
        this.subCategoryId = new SimpleIntegerProperty(subCategoryId);
        this.name = new SimpleStringProperty(name);

        this.relatedUnitId = new SimpleIntegerProperty(relatedUnitId);
        this.relatedUnit = new SimpleObjectProperty<>();
        this.product = new SimpleListProperty<>();
    }

}
