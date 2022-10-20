package com.company.Summative1TriJonathanRichard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "sales_tax_rate")
public class SalesTaxRate {

    @Id
    @NotNull(message = "Please enter an ID")
    @Size(min = 2, max = 2)
    private String state;

    public SalesTaxRate(){

    }

    public SalesTaxRate(String state) {
        this.state = state;
        this.rate = findRate(state);
    }

    public Double findRate(String state){
        switch (state) {
            case "CT":
            case "ID":
            case "ME":
            case "MT":
            case "TX":
            case "WI":
                return .03;
            case "AZ":
            case "CO":
            case "IA":
            case "KY":
            case "NE":
            case "NV":
            case "OH":
            case "OK":
            case "UT":
            case "WY":
                return 0.04;
            case "AL":
            case "DE":
            case "HI":
            case "IL":
            case "IN":
            case "LA":
            case "MA":
            case "MS":
            case "MO":
            case "NJ":
            case "NM":
            case "NC":
            case "ND":
            case "TN":
            case "WA":
            case "WV":
                return 0.05;
            case "AK":
            case "AR":
            case "CA":
            case "FL":
            case "KS":
            case "MI":
            case "MN":
            case "NH":
            case "NY":
            case "PA":
            case "RI":
            case "SC":
            case "SD":
            case "VA":
                return 0.06;
            case "GA":
            case "MD":
            case "OR":
            case "VT":
                return 0.07;
            default:
                return null;
        }
    }


    @NotNull(message = "Please enter a rate")
    private double rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTaxRate that = (SalesTaxRate) o;
        return Double.compare(that.rate, rate) == 0 && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }

    @Override
    public String toString() {
        return "SalesTaxRate{" +
                "state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
