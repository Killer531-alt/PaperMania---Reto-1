package com.papel.presenter;

import android.content.Context;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.papel.dto.ProductDTO;
import com.papel.model.Product;
import com.papel.repository.ProductDAO;
import com.papel.view.IProductView;


public class ProductPresenterImpl implements IProductPresenter {

    private IProductView view;
    private ProductDAO dao;
    // Constructor que inicializa el presenter con la vista y el contexto
    public ProductPresenterImpl(IProductView view, Context context){
        this.view=view;
        this.dao= new ProductDAO(context);//validar
    }
    @Override
    public void showProducts(){
        List<Product> productList =dao.getAllProducts();
        if(productList.isEmpty()){
            view.showMessage("No product found");
        }
        view.showProducts(productList);

    }

    @Override
    public void addProduct(ProductDTO dto) {
        long result = dao.insertProduct(dto.getName(),dto.getDescription());
        if(result!=-1){
            view.showMessage("added successfully");
        }else{
            view.showError("failed to add");
        }
    }

    @Override
    public void updateProduct(ProductDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            view.showError("El nombre no puede estar vacío");
            return;
        }
        if (dto.getDescription() == null || dto.getDescription().isEmpty()) {
            view.showError("La descripción no puede estar vacía");
            return;
        }
        if (!dao.existById(dto.getId())) {
            view.showError("El producto con ID " + dto.getId() + " no existe");
            return;
        }
        int result = dao.updateProduct(dto.getId(),dto.getName(),dto.getDescription());

        if (result>0) {
            view.showMessage("Producto actualizado exitosamente");
        } else {
            view.showError("Error al actualizar el producto");
        }
    }

    @Override
    public void deleteProduct(ProductDTO dto) {
        if (!dao.existById(dto.getId())) {
            view.showError("El producto con ID " + dto.getId() + " no existe");
            return;
        }
        int result = dao.deleteProduct(dto.getId());
        if (result>0) {
            view.showMessage("Producto eliminado exitosamente");
        } else {
            view.showError("Error al eliminar el producto");
        }
        showProducts();
    }

}
