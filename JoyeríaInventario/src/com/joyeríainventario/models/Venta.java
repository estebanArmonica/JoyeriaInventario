/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeríainventario.models;

import java.time.LocalDate;

/**
 *
 * @author esteb
 */
public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Joya joya;
    private int cantidad;
    private double precioTotal;
    private LocalDate fechaVenta;

    public Venta(int idVenta, Cliente cliente, Joya joya, int cantidad, double precioTotal, LocalDate fechaVenta) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.joya = joya;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.fechaVenta = fechaVenta;
    }

    public Venta() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Joya getJoya() {
        return joya;
    }

    public void setJoya(Joya joya) {
        this.joya = joya;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    
    // método para obtener la fecha en Date de sql
    public java.sql.Date obtenerFormatoTerminate(){
        // convertimos LocalDateTime a java.sql.Date
        if(this.fechaVenta != null){
            return java.sql.Date.valueOf(this.fechaVenta);
        }
        return null;
    }
}
