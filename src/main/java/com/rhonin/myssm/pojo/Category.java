package com.rhonin.myssm.pojo;

import java.util.List;

public class Category {
    private Integer id;

    private String name;

    private List<Object> productsByRow;

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Object> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<Object> productsByRow) {
        this.productsByRow = productsByRow;
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
        this.name = name == null ? null : name.trim();
    }
}