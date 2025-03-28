/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeríainventario.dao;

import com.joyeríainventario.models.Cliente;
import com.joyeríainventario.vos.ClienteVO;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;

/**
 *
 * @author esteb
 */
public class ClienteDAO {

    Conexion con = new Conexion();

    // buscamos a todos los clientes
    public void buscarTodo(String tabla, String valor, JComboBox combo) throws IOException, Exception {
        con.acceder();
        String sql = "SELECT idCliente, nombreCliente, direccion, correo, telefono FROM " + tabla;
        PreparedStatement consulta = con.get().prepareStatement(sql);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            Cliente cliente = new Cliente(
                    resultado.getInt("idCliente"),
                    resultado.getString("nombreCliente"),
                    resultado.getString("direccion"),
                    resultado.getString("correo"),
                    resultado.getInt("telefono")
            );
            combo.addItem(cliente);
        }
        con.cerrar();
    }

    // buscar por id
    public Cliente buscarPorIdCliente(int idCliente) throws Exception {
        con.acceder();
        String sql = "SELECT nombreCliente, direccion, correo, telefono FROM cliente WHERE idCliente=?";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setInt(1, idCliente);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            String nombreCliente = resultado.getString("nombreCliente");
            String direccion = resultado.getString("direccion");
            String correo = resultado.getString("correo");
            int telefono = resultado.getInt("telefono");
            con.cerrar();
            return new Cliente(idCliente, nombreCliente, direccion, correo, telefono);
        }
        con.cerrar();
        return null;
    }
    
    // buscar por id
    public Cliente buscarPorNombreCliente(String nombreCliente) throws Exception {
        con.acceder();
        String sql = "SELECT idCliente, direccion, correo, telefono FROM cliente WHERE nombreCliente=?";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setString(1, nombreCliente);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            int idCliente = resultado.getInt("idCliente");
            String direccion = resultado.getString("direccion");
            String correo = resultado.getString("correo");
            int telefono = resultado.getInt("telefono");
            con.cerrar();
            return new Cliente(idCliente, nombreCliente, direccion, correo, telefono);
        }
        con.cerrar();
        return null;
    }

    // BUSCAMOS LOS CLIENETS POR JCOMBOX
    public void buscarClientes(JComboBox<Cliente> combo) throws IOException, Exception {
        con.acceder();
        combo.removeAll();; // limpiamos JComboBox antes de llenarlo
        String sql = "SELECT idCliente, nombreCliente, direccion, correo, telefono FROM cliente";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            Cliente cliente = new Cliente(
                    resultado.getInt("idCliente"),
                    resultado.getString("nombreCliente"),
                    resultado.getString("direccion"),
                    resultado.getString("correo"),
                    resultado.getInt("telefono")
            );
            combo.addItem(cliente);
        }
        con.cerrar();
    }
}
