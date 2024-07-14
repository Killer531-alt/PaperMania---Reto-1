package com.papel.view;

import java.util.List;

import com.papel.model.Product;


public interface IProductView {

    void showProducts(List<Product> productList);

    void showMessage(String text);
    void showError(String text);
}
