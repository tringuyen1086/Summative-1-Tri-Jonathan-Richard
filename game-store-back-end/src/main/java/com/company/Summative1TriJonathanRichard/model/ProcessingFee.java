package com.company.Summative1TriJonathanRichard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

//Consoles: 14.99
//        T-shirts: 1.99
//        Games: 1.49

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(indexes = @Index(columnList = "product_type",name = "ix_product_type_fee",unique = true), name = "processing_fee")
public class ProcessingFee {
    @Id
    @Column(name = "product_type")
    @Size(max = 20)
    private String productType;
    private double fee;

    public ProcessingFee(){

    }

    public ProcessingFee(String productType) {
        this.productType = productType;
        this.fee = findFee(productType);
    }

    public Double findFee(String productType){
        switch (productType) {
            case"game":
                return 1.49;
            case "console":
                return 14.99;
            case "tshirt":
                return 1.98;
            default:
                return null;

        }
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return Double.compare(that.fee, fee) == 0 && Objects.equals(productType, that.productType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, fee);
    }

    @Override
    public String toString() {
        return "ProcessingFee{" +
                "productType='" + productType + '\'' +
                ", fee=" + fee +
                '}';
    }
}
