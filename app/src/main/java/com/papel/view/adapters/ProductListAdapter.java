package com.papel.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import com.papel.R;
import com.papel.model.Product;


public class ProductListAdapter extends BaseAdapter {

    private List<Product> products;
    private LayoutInflater inflater;
    private OnEditClickListener editClickListener; // Escucha para clics en el botón de editar
    private OnDeleteClickListener deleteClickListener; // Escucha para clics en el botón de eliminar

    // Interfaz para escuchar clics en el botón de editar
    public interface OnEditClickListener {
        void onEditClick(int position);
    }

    // Interfaz para escuchar clics en el botón de eliminar
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    // Métodos para establecer los listeners
    public void setOnEditClickListener(OnEditClickListener listener) {
        this.editClickListener = listener;
    }

    // Método para actualizar la lista de productos
    public void updateProducts(List<Product> newProductList) {
        this.products.clear();
        if (newProductList != null) {
            this.products.addAll(newProductList);
        }
        notifyDataSetChanged();
    }
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }
    //constructor
    public ProductListAdapter(Context context,List<Product> products){
        this.products=products;
        this.inflater = LayoutInflater.from(context);
    }

    // Devuelve el número de elementos en la lista
    @Override
    public int getCount() {
        return products.size();
    }
    // Devuelve el producti en la posición especificada
    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    // Devuelve el ID del elemento en la posición especificada
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Método principal para crear y actualizar las vistas de cada elemento en la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.list_item_products, parent, false);
            viewHolder = new ViewHolder(); // Crear un nuevo ViewHolder para almacenar las vistas
            viewHolder.txtId = convertView.findViewById(R.id.etIdProduct);
            viewHolder.txtName = convertView.findViewById(R.id.etNameProduct);
            viewHolder.txtDescription = convertView.findViewById(R.id.etDescriptionProduct);
            viewHolder.btnEdit = convertView.findViewById(R.id.btnEditProduct);
            viewHolder.btnDelete = convertView.findViewById(R.id.btnDeleteProduct);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag(); // Si la vista ya está inflada, obtener el ViewHolder de la etiqueta

        }
        // Obtener el producto en la posición actual
        Product product = products.get(position);
        // Establecer los valores de los TextView con la información del producto
        viewHolder.txtId.setText(String.valueOf("ID Product: "+product.getId()));
        viewHolder.txtName.setText(String.valueOf("Name: "+product.getName()));
        viewHolder.txtDescription.setText(String.valueOf("Description: "+product.getDescription()));

        // Establecer listeners para los botones de editar y eliminar
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editClickListener != null) {
                    editClickListener.onEditClick(position);
                }
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteClickListener != null) {
                    deleteClickListener.onDeleteClick(position);
                }
            }
        });


        return convertView;
    }

    // Clase estática para almacenar vistas de elementos de la lista
    static class ViewHolder {
        TextView txtId;
        TextView txtName;
        TextView txtDescription;
        Button btnEdit;
        Button btnDelete;
    }

}
