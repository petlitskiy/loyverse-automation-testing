package com.backoffice.datatypes;

/**
 * @author Neha Kharbanda
 */
public class Item {

    private String price;
    private String cost;
    private String sku;
    private String barCode;


    private String name;
    private String category;
    private String description;
    private String trackStockSwitch;
    private String inStock;
    private String lowStock;
    private String modifierIcon;

    public Item() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrackStockSwitch() {
        return trackStockSwitch;
    }

    public void setTrackStockSwitch(String trackStockSwitch) {
        this.trackStockSwitch = trackStockSwitch;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    public String getLowStock() {
        return lowStock;
    }

    public void setLowStock(String lowStock) {
        this.lowStock = lowStock;
    }

    public String getModifierIcon() {
        return modifierIcon;
    }

    public void setModifierIcon(String modifierIcon) {
        this.modifierIcon = modifierIcon;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

}
