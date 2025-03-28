/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeríainventario.documentos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author esteb
 */
public class ExportarExcel {

    public void exportarExcel(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            File archivoXLS = new File(ruta);
            if (archivoXLS.exists()) {
                archivoXLS.delete();
            }
            archivoXLS.createNewFile();
            Workbook libro = new HSSFWorkbook();
            FileOutputStream archivo = new FileOutputStream(archivoXLS);
            Sheet hoja = libro.createSheet("Hoja de Ventas");
            hoja.setDisplayGridlines(false);

            // Crear estilos
            CellStyle estiloEncabezado = libro.createCellStyle();
            HSSFFont fuenteEncabezado = (HSSFFont) libro.createFont();
            fuenteEncabezado.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // Usar setBoldweight
            fuenteEncabezado.setFontHeightInPoints((short) 12);
            estiloEncabezado.setFont(fuenteEncabezado);

            CellStyle estiloDatos = libro.createCellStyle();
            estiloDatos.setWrapText(true); // Ajustar texto

            // Escribir encabezados de columna
            Row filaEncabezado = hoja.createRow(0);
            for (int c = 0; c < t.getColumnCount(); c++) {
                Cell celda = filaEncabezado.createCell(c);
                celda.setCellValue(t.getColumnName(c));
                celda.setCellStyle(estiloEncabezado); // Aplicar estilo de encabezado
            }

            // Escribir datos del JTable
            for (int f = 0; f < t.getRowCount(); f++) {
                Row fila = hoja.createRow(f + 1); // Se empieza desde la fila 1
                for (int c = 0; c < t.getColumnCount(); c++) {
                    Cell celda = fila.createCell(c);
                    Object valor = t.getValueAt(f, c);
                    if (valor instanceof Double) {
                        celda.setCellValue((Double) valor);
                    } else if (valor instanceof Float) {
                        celda.setCellValue((Float) valor);
                    } else {
                        celda.setCellValue(String.valueOf(valor));
                    }
                    celda.setCellStyle(estiloDatos); // Aplicar estilo de datos
                }
            }

            // Guardar el archivo
            libro.write(archivo);
            archivo.close();
            Desktop.getDesktop().open(archivoXLS); // Abrir el archivo después de exportar
        }
    }
}
