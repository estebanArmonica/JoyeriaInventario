/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeríainventario.vos;

import com.joyeríainventario.models.Joya;
import com.joyeríainventario.models.Venta;
import java.time.LocalDate;

/**
 *
 * @author esteb
 */
public class VentaVO {
    private final Venta venta;

    public VentaVO(Venta venta) {
        this.venta = venta;
    }
    
    //método para validar si hay suficiente stock
    public boolean validarStock() {
        Joya joyita = venta.getJoya();
        return (joyita.getStock() >= venta.getCantidad());
    }
    
}
