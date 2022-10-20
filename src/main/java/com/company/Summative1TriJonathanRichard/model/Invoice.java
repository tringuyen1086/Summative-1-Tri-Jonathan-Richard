package com.company.Summative1TriJonathanRichard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer id;

    @NotNull(message = "Please enter a name")
    @Size(max = 80)
    private String name;

    @NotNull(message = "Please enter a street")
    @Size(max = 30)
    private String street;

    @NotNull(message = "Please enter a city")
    @Size(max = 30)
    private String city;

    @NotNull(message = "Please enter a state")
    @Size(max = 2)
    private String state;


    @NotNull(message = "Please enter a zipcode")
    @Size(max = 5)
    private String zipcode;

    @NotNull(message = "Please enter an item-type")
    @Column(name = "item_type")
    @Size(max = 20)
    private String itemType;

    @NotNull(message = "Please enter an item-ID")
    @Column(name = "item_id")
    private int itemId;

    @NotNull(message = "Please enter an unit price")
    @Column(name = "unit_price")
    @Digits(integer = 5,fraction = 2)
    private double unitPrice;

    @NotNull(message = "Please enter a quantity")
    private int quantity;

    @NotNull(message = "Please enter a subtotal")
    @Digits(integer = 5,fraction = 2)
    private double subtotal;

    @NotNull(message = "Please enter a tax")
    @Digits(integer = 5,fraction = 2)
    private double tax;

    @Column(name = "processing_fee")
    @Digits(integer = 5,fraction = 2)
    private double processingFee;
    @NotNull(message = "Please enter a total")
    @Digits(integer = 5,fraction = 2)
    private double total;

    public Invoice(){

    }

    public Invoice(String name, String street, String city, String state, String zipcode, String itemType, int itemId, double unitPrice, int quantity, double subtotal, double total) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
//        this.tax = new SalesTaxRate(state);
  //      this.processingFee = new ProcessingFee();
        this.total = total;
    }

    public Invoice(Integer id, String name, String street, String city, String state, String zipcode, String itemType, int itemId, double unitPrice, int quantity, double subtotal, double tax, double processingFee, double total) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
        //this.tax = tax;
        //this.processingFee = processingFee;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

//    public double getTax() {
//        return tax;
//    }
//
//    public void setTax(double tax) {
//        this.tax = tax;
//    }
//
//    public double getProcessingFee() {
//        return processingFee;
//    }
//
//    public void setProcessingFee(double processingFee) {
//        this.processingFee = processingFee;
//    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return itemId == invoice.itemId && Double.compare(invoice.unitPrice, unitPrice) == 0 && quantity == invoice.quantity && Double.compare(invoice.subtotal, subtotal) == 0 && Double.compare(invoice.total, total) == 0 && Objects.equals(id, invoice.id) && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street) && Objects.equals(city, invoice.city) && Objects.equals(state, invoice.state) && Objects.equals(zipcode, invoice.zipcode) && Objects.equals(itemType, invoice.itemType) && Objects.equals(tax, invoice.tax) && Objects.equals(processingFee, invoice.processingFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
