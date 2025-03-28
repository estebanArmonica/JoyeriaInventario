/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeríainventario.vos;

/**
 *
 * @author esteb
 */
public class JoyaVO {
    private int idJoya;
    private String nombre, material;
    private double peso, precio;
    private int stock;

    public JoyaVO(int idJoya, String nombre, String material, double peso, double precio, int stock) {
        this.idJoya = idJoya;
        this.nombre = nombre;
        this.material = material;
        this.peso = peso;
        this.precio = precio;
        this.stock = stock;
    }

    public JoyaVO() {
    }
    
    public int getIdJoya(){
        return idJoya;
    }
    
    public void setIdJoya(int idJoya) {
        this.idJoya = idJoya;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    @Override
    public String toString() {
        return nombre != null ? nombre : "Seleccione una joya";
    }
}
