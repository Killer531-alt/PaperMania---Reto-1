package com.papel.model;

public class Product {

    private Integer id;
    private String name;
    private String description;

    public Product(String name, String description){
        this.name=name;
        this.description=description;
    }
    public Product(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
