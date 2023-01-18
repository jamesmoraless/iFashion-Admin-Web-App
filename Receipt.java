package se2203b.assignments.adminapp;

import javafx.beans.property.*;


public class Receipt
{
    private DoubleProperty payment;
    private StringProperty customerName;
    private StringProperty paymentMethods;
    private StringProperty date;
    private IntegerProperty productId;

    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    public IntegerProperty productIdProperty() {
        return productId;
    }

    public int getProductId() {
        return productId.get();
    }



    public void setCustomerName(String name) {
        this.customerName.set(name);
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public String getCustomerName()
    {
        return customerName.get();
    }


    public String getPaymentMethod() {
        return paymentMethods.get();
    }

    public void setPaymentMethiod(String payMethod) {
        this.paymentMethods.set(payMethod);
    }

    public StringProperty paymentMethodProperty() {
        return paymentMethods;
    }


    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getDate() {
        return date.get();
    }


    public void setPayment(double payment) {
        this.payment.set(payment);
    }

    public DoubleProperty paymentProperty() {
        return payment;
    }

    public double getPayment() {
        return payment.get();
    }

    //A constructor for Receipt
    public Receipt(String customerName, int productId, String paymentMethod, String date, double payment){
        this.customerName = new SimpleStringProperty(customerName);
        this.productId = new SimpleIntegerProperty(productId);
        this.paymentMethods = new SimpleStringProperty(paymentMethod);
        this.date = new SimpleStringProperty(date);
        this.payment = new SimpleDoubleProperty(payment);

    }



}
