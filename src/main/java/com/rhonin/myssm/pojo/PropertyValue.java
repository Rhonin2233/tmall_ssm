package com.rhonin.myssm.pojo;

public class PropertyValue {
    private Integer id;

    private Integer product_id;

    private Integer property_id;

    private String value;

    private Property property;

    public PropertyValue() {
    }

    public PropertyValue(Integer product_id, Integer property_id, String value) {
        this.product_id = product_id;
        this.property_id = property_id;
        this.value = value;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getProperty_id() {
        return property_id;
    }

    public void setProperty_id(Integer property_id) {
        this.property_id = property_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}