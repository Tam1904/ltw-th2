package th2.model;

import java.text.NumberFormat;

import lombok.Data;

@Data
public class Product{
    private String code;
    private String description;
    private double price;

    public Product() {
        this.code = "";
        this.description = "";
        this.price = 0;
    }

    public Product(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }

    
    public  String getConvert(){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(this.price);
    }
    
}