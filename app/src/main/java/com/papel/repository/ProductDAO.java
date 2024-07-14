package com.papel.repository;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import com.papel.model.Product;
import com.papel.utils.DataBaseHelper;


public class ProductDAO {

    private SQLiteDatabase db;// Objeto para interactuar con la base de datos
    private DataBaseHelper dbHelper;// Instancia de DatabaseHelper para crear y actualizar la base de datos

    // Constructor que recibe el contexto de la aplicación y crea una instancia de DatabaseHelper
    public ProductDAO(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    // Método para abrir la conexión con la base de datos en modo escritura
    public void open() {

        db = dbHelper.getWritableDatabase();
    }
    // Método para cerrar la conexión con la base de datos
    public void close() {
        dbHelper.close();
    }


    // Método para insertar un nuevo producto en la tabla 'products'
    public long insertProduct(String name,String description){
        if(db==null){
            this.open();
        }
        ContentValues values = new ContentValues();// Objeto para almacenar los valores a insertar
        values.put("name",name);// Inserción del nombre del producto
        values.put("description",description);
        return db.insert("product",null,values);// Ejecución de la inserción y retorno del ID del nuevo registro
    }
    // Método para obtener todos los productos de la tabla 'products'
    public List<Product> getAllProducts(){ //pdt meter try catch
        if(db==null){
            this.open();
        }
        List<Product> products = new ArrayList<>();// Lista para almacenar los productos obtenidos
        Cursor cursor = db.rawQuery("SELECT * FROM product",null);
        // Iteración sobre los resultados del cursor para obtener los datos de cada producto
        if(cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                products.add(product);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }
    // Método para actualizar los datos de un producto en la tabla 'products'
    public int updateProduct(Integer id, String name, String description) {
        boolean result=false;
        if(db==null){
            this.open();
        }
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        return db.update("product", values, "id = ?", new String[]{String.valueOf(id)});

    }
    // Método para eliminar un producto de la tabla 'products' mediante su ID
    public int deleteProduct(int id) {
        if(db==null){
            this.open();
        }
        // Eliminación del producto con el ID proporcionado
        return db.delete("product", "id = ?", new String[]{String.valueOf(id)});
    }

    public Product findById(int id) {
        if (db == null) {
            this.open();
        }
        Product product = null;
        Cursor cursor = db.rawQuery("SELECT * FROM product WHERE id=?", new String[]{String.valueOf(id)});
        if (cursor != null && cursor.moveToFirst()) {
            product = new Product();
            product.setId(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setDescription(cursor.getString(2));

            cursor.close();
        }
        return product;
    }
    public boolean existById(int id){
        boolean exists=false;
        if(db==null){
            this.open();
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM product WHERE id=?", new String[]{String.valueOf(id)});
        // Si el cursor tiene datos, obtener el conteo
        if (cursor != null && cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            exists = count > 0;
        }
        cursor.close();
        return exists;
    }

}
