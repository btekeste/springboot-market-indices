package com.app.index;

import javax.persistence.*;

@Entity //Annotation to map Index class to database for Hibernate.
@Table //Annotation to declare the class as table for the database.
public class Index {
    //Auto-generate values in column Id for variable id. Part of DB configuration.
    @Id
    @SequenceGenerator(
        name = "index_sequence",
        sequenceName = "index_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "index_sequence"
    )
    private Long id;
    private String ticker;
    private String description;
    private float lastPrice;
    private float changePercentage;
    private float changeValue;
    
    //Annotation means the variable doesn't need to become a table column.
    //It will be calculated at runtime.
    // @Transient

    public Index() {
    }

    public Index(Long id, 
                    String ticker, 
                    String description, 
                    float lastPrice, 
                    float changePercentage, 
                    float changeValue) {
        this.id = id;
        this.ticker = ticker;
        this.description = description;
        this.lastPrice = lastPrice;
        this.changePercentage = changePercentage;
        this.changeValue = changeValue;
    }

    public Index(String ticker, 
                    String description, 
                    float lastPrice, 
                    float changePercentage, 
                    float changeValue) {
        this.ticker = ticker;
        this.description = description;
        this.lastPrice = lastPrice;
        this.changePercentage = changePercentage;
        this.changeValue = changeValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getChangePercentage() {
        return changePercentage;
    }

    public void setChangePercentage(float changePercentage) {
        this.changePercentage = changePercentage;
    }

    public float getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(float changeValue) {
        this.changeValue = changeValue;
    }
    
    @Override
    public String toString() {
        return "Index [id=" + id + ", ticker=" + ticker + ", description=" + description + 
                ", lastPrice=" + lastPrice + ", changePercentage=" + changePercentage + 
                ", changeValue=" + changeValue + "]";
    }
    
}
