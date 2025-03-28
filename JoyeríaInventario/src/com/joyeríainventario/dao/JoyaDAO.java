/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeríainventario.dao;

import com.joyeríainventario.models.Joya;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author esteb
 */
public class JoyaDAO {
    Conexion con = new Conexion();
    
    // agregamos una joya
    public int agregarJoya(Joya joya) throws ClassNotFoundException, Exception {
        con.acceder();
        String sql = "INSERT INTO joya(nombre, material, peso, precio, stock) VALUES(?,?,?,?,?)";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setString(1, joya.getNombre());
        consulta.setString(2, joya.getMaterial());
        consulta.setDouble(3, joya.getPeso());
        consulta.setDouble(4, joya.getPrecio());
        consulta.setInt(5, joya.getStock());
        consulta.executeUpdate();
        con.cerrar();
        return 1;
    }
    
    // modificar una joya
    public int modificarJoya(Joya joya) throws ClassNotFoundException, Exception {
        con.acceder();
        String sql = "UPDATE joya SET stock=? WHERE idJoya=?";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setInt(2, joya.getIdJoya());
        consulta.setInt(1, joya.getStock());
        consulta.executeUpdate();
        con.cerrar();
        return 1;
    }
    
    // modificar una joya
    public int eliminarJoya(int idJoya) throws ClassNotFoundException, Exception {
        con.acceder();
        String sql = "DELETE FROM joya WHERE idJoya=?";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setInt(1, idJoya);
        consulta.execute();
        con.cerrar();
        return 1;
    }
    
    // listamos las joya en un TJable
    public void listarJoya(JTable tabla) throws IOException, Exception {
        con.acceder();
        DefaultTableModel model;
        String [] columnas = {"ID", "NOMBRE", "MATERIAL", "PESO", "PRECIO", "STOCK"};
        model = new DefaultTableModel(null, columnas);
        
        // ejecutamos las sentencias SQL
        String sql = "SELECT * FROM joya ORDER BY idJoya";
        String [] filas = new String[6];
        
        PreparedStatement consulat = con.get().prepareStatement(sql);
        ResultSet resultado = consulat.executeQuery();
        
        while(resultado.next()){
            for (int i = 0; i < 6; i++) {
                filas[i] = resultado.getString(i+1);
            }
            
            model.addRow(filas);
        }
        tabla.setModel(model);
        con.cerrar();
    }
    
    public Joya buscarPorMaterial(String material) throws ClassNotFoundException, Exception {
        con.acceder();
        String sql = "SELECT idJoya, nombre, peso, precio, stock FROM joya WHERE material LIKE ?";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setString(1, "%"+material+"%");
        ResultSet resultado = consulta.executeQuery();
        while(resultado.next()){
            int idJoya = resultado.getInt("idJoya");
            String nombre = resultado.getString("nombre");
            double peso = resultado.getDouble("peso");
            double precio = resultado.getDouble("precio");
            int stock = resultado.getInt("stock");
            con.cerrar();
            return new Joya(idJoya, nombre, material, peso, precio, stock);
        }
        con.cerrar();
        return null;
    }
    
    public Joya buscarJoyaPorId(int idJoya) throws Exception {
        con.acceder();
        String sql = "SELECT nombre, peso, precio, stock FROM joya WHERE idJoya= ?";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setInt(1, idJoya);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            String nombre = resultado.getString("nombre");
            double peso = resultado.getDouble("peso");
            double precio = resultado.getDouble("precio");
            int stock = resultado.getInt("stock");
            con.cerrar();
            return new Joya(idJoya, nombre, nombre, peso, precio, stock);
        }
        con.cerrar();
        return null;
    }
    
    public void buscarTodoJoyaCombo(String tabla, String valor, JComboBox combo) throws IOException, Exception {
        con.acceder();
        String sql = "SELECT idJoya, nombre, material, peso, precio, stock FROM "+tabla;
        PreparedStatement consulta = con.get().prepareStatement(sql);
        ResultSet resultado = consulta.executeQuery();
        while(resultado.next()){
            Joya joya = new Joya(
                    resultado.getInt("idJoya"),
                    resultado.getString("nombre"),
                    resultado.getString("material"),
                    resultado.getDouble("peso"),
                    resultado.getDouble("precio"),
                    resultado.getInt("stock")
            );
            combo.addItem(joya);
        }
        con.cerrar();
    }
}
