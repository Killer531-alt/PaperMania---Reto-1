package com.papel.dto;

public class ProductDTO {

    private Integer id;
    private String name;
    private String description;


    public ProductDTO(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public ProductDTO() {
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
