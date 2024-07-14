package com.papel.presenter;

import com.papel.dto.ProductDTO;
import com.papel.model.Product;


public interface IProductPresenter {

    void showProducts();

    void addProduct(ProductDTO dto);

    void updateProduct(ProductDTO dto);

    void deleteProduct(ProductDTO dto);

}
