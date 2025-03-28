/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeríainventario.dao;

import com.joyeríainventario.models.Cliente;
import com.joyeríainventario.models.Joya;
import com.joyeríainventario.models.Venta;
import java.util.List;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author esteb
 */
public class VentaDAO {
    Conexion con = new Conexion();
    
    // agregamos una venta
    public int registrarVenta(Venta venta) throws Exception, NullPointerException, ClassNotFoundException {
        con.acceder();
        String sql = "INSERT INTO venta(clienteId, joyaId, cantidad, precio_total, fecha_venta) VALUES(?,?,?,?,?)";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setInt(1, venta.getCliente().getIdCliente());
        consulta.setInt(2, venta.getJoya().getIdJoya());
        consulta.setInt(3, venta.getCantidad());
        consulta.setDouble(4, venta.getPrecioTotal());
        consulta.setDate(5, venta.obtenerFormatoTerminate());
        consulta.execute();
        con.cerrar();
        return 1;
    }
    
    // listamos las ventas
    public void listarVentas(JTable tabla) throws IOException, Exception {
        con.acceder();
        DefaultTableModel model;
        String [] columnas = {"ID", "CLIENTE", "JOYA", "CANTIDAD", "PRECIO TOTAL", "FECHA DE VENTA"};
        model = new DefaultTableModel(null, columnas);
        
        String sql = "SELECT MIN(idVenta) AS idVenta, c.nombreCliente, j.nombre, SUM(v.cantidad) AS total_cantidad, SUM(v.precio_total) AS total_precio, MAX(v.fecha_venta) AS fecha_venta FROM venta v INNER JOIN cliente c ON(c.idCliente = v.clienteId) INNER JOIN joya j ON(j.idJoya = v.joyaId) GROUP BY c.idCliente, c.nombreCliente, j.nombre ORDER BY total_precio DESC";
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
    
    public Venta buscarPorNombreCliente(String nombreCliente) throws Exception {
        con.acceder();
        String sql = "SELECT v.idventa, c.nombreCliente, j.nombre, v.cantidad, v.precio_total, v.fecha_venta FROM venta v INNER JOIN cliente c ON(c.idCliente = v.clienteId) INNER JOIN joya j ON(j.IdJoya = v.joyaId) WHERE c.nombreCliente = ?";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setString(1, nombreCliente);
        ResultSet resultado = consulta.executeQuery();
        while(resultado.next()){
            JoyaDAO joyaDAO = new JoyaDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            
            int idClienteResultado = resultado.getInt("idCliente");
            int idJoyaResultado = resultado.getInt("idJoya");
            
            Joya joya = joyaDAO.buscarJoyaPorId(idJoyaResultado);
            Cliente cliente = clienteDAO.buscarPorIdCliente(idClienteResultado);
         
            Venta venta = new Venta(
                resultado.getInt("idVenta"),
                cliente,
                joya,
                resultado.getInt("cantidad"),
                resultado.getInt("precio_total"),
                resultado.getDate("fecha_venta").toLocalDate()
            );
            return new Venta(idJoyaResultado, cliente, joya, idJoyaResultado, idJoyaResultado, LocalDate.MIN);
        }
        con.cerrar();
        return null;
    }
    
    public List<Venta> filtrarVentaPorCliente(String nombreCliente) throws Exception {
        List<Venta> ventas = new ArrayList<>();
        con.acceder();
        String sql = "SELECT v.idventa, c.nombreCliente, j.idJoya, j.nombre, v.cantidad, v.precio_total, v.fecha_venta FROM venta v INNER JOIN cliente c ON(c.idCliente = v.clienteId) INNER JOIN joya j ON(j.IdJoya = v.joyaId) WHERE c.nombreCliente = ?";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setString(1, nombreCliente);
        ResultSet resultado = consulta.executeQuery();
        
        while(resultado.next()){
            JoyaDAO joyaDAO = new JoyaDAO();
            int idJoyaResultado = resultado.getInt("IdJoya");
            
            Cliente cliente = new Cliente();
            
            cliente.setNombreCliente(resultado.getString("nombreCliente"));
            
            Joya joya = joyaDAO.buscarJoyaPorId(idJoyaResultado);
            
            Venta venta = new Venta(
                resultado.getInt("idVenta"),
                cliente,
                joya,
                resultado.getInt("cantidad"),
                resultado.getInt("precio_total"),
                resultado.getDate("fecha_venta").toLocalDate()
            );
            ventas.add(venta);
        }
        con.cerrar();
        return ventas;
    }
    
    
    public List<Venta> filtrarVentaPorFecha(LocalDate fechaVenta) throws Exception {
        List<Venta> ventas = new ArrayList<>();
        con.acceder();
        String sql = "SELECT v.idventa, c.idCliente, c.nombreCliente, j.idJoya, j.nombre, v.cantidad, v.precio_total, v.fecha_venta FROM venta v INNER JOIN cliente c ON(c.idCliente = v.clienteId) INNER JOIN joya j ON(j.IdJoya = v.joyaId) WHERE v.fecha_venta = ?";
        PreparedStatement consulta = con.get().prepareStatement(sql);
        consulta.setDate(1, java.sql.Date.valueOf(fechaVenta));
        ResultSet resultado = consulta.executeQuery();
        
        while(resultado.next()){
            JoyaDAO joyaDAO = new JoyaDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            
            int idClienteResultado = resultado.getInt("idCliente");
            int idJoyaResultado = resultado.getInt("idJoya");
            
            Joya joya = joyaDAO.buscarJoyaPorId(idJoyaResultado);
            Cliente cliente = clienteDAO.buscarPorIdCliente(idClienteResultado);
         
            Venta venta = new Venta(
                    resultado.getInt("idVenta"),
                    cliente,
                    joya,
                    resultado.getInt("cantidad"),
                    resultado.getInt("precio_total"),
                    resultado.getDate("fecha_venta").toLocalDate()
            );
            ventas.add(venta);
        }
        con.cerrar();
        return ventas;
    }
}
