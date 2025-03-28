/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeriainventario.documentos.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author esteb
 */
public class ExportarFacturaTest {

    public static void main(String[] args) {
        // CREAMOS UNU JFileChooser para permitir la seleccion de ubicacion
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");

        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Guardar Factura");

        // montar el dialogo y obtener la respuesta
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtenemos el archivo seleccionado por el usuario
            File archivoPDF = fileChooser.getSelectedFile();

            // nos aseguramos de que el archivo tenga la extension .pdf
            if (!archivoPDF.getAbsolutePath().endsWith(".pdf")) {
                archivoPDF = new File(archivoPDF.getAbsolutePath() + ".pdf");
            }
            try {
                // Crear un documento PDF
                Document document = new Document();
                String destino = archivoPDF.getAbsolutePath();

                System.out.println("Guardando PDF en: " + destino);
                PdfWriter.getInstance(document, new FileOutputStream(destino));
                document.open();

                // Agregar título
                document.add(new Paragraph("Factura", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK)));
                document.add(new Paragraph(" "));

                // Datos del vendedor
                document.add(new Paragraph("Vendedor:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                document.add(new Paragraph("Nombre de la Empresa", FontFactory.getFont(FontFactory.HELVETICA, 12)));
                document.add(new Paragraph("Dirección: Calle Falsa 123", FontFactory.getFont(FontFactory.HELVETICA, 12)));
                document.add(new Paragraph("Teléfono: 123-456-789", FontFactory.getFont(FontFactory.HELVETICA, 12)));
                document.add(new Paragraph(" "));

                // Datos del cliente
                document.add(new Paragraph("Cliente:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                document.add(new Paragraph("Nombre del Cliente", FontFactory.getFont(FontFactory.HELVETICA, 12)));
                document.add(new Paragraph("Dirección: Calle Verdadera 456", FontFactory.getFont(FontFactory.HELVETICA, 12)));
                document.add(new Paragraph("Teléfono: 987-654-321", FontFactory.getFont(FontFactory.HELVETICA, 12)));
                document.add(new Paragraph(" "));

                // Crear tabla
                PdfPTable table = new PdfPTable(3); // 3 columnas
                table.setWidthPercentage(100); // Ancho completo
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Colores de encabezado
                PdfPCell header1 = new PdfPCell(new Phrase("Descripción", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                header1.setBorderColor(BaseColor.DARK_GRAY);
                header1.setPadding(5);
                table.addCell(header1);

                PdfPCell header2 = new PdfPCell(new Phrase("Cantidad", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header2.setHorizontalAlignment(Element.ALIGN_CENTER);
                header2.setBorderColor(BaseColor.DARK_GRAY);
                header2.setPadding(5);
                table.addCell(header2);

                PdfPCell header3 = new PdfPCell(new Phrase("Precio", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                header3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header3.setHorizontalAlignment(Element.ALIGN_CENTER);
                header3.setBorderColor(BaseColor.DARK_GRAY);
                header3.setPadding(5);
                table.addCell(header3);

                // Agregar productos o servicios a la tabla
                // Ejemplo de un producto o servicio
                addProductRow(table, "Producto A", 2, "$10.00");
                addProductRow(table, "Producto B", 1, "$5.00");
                addProductRow(table, "Servicio C", 1, "$15.00");

                document.add(table);
                document.add(new Paragraph(" "));

                // Total
                document.add(new Paragraph("Total: $25.00", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

                // Cerrar documento
                document.close();
                System.out.println("Factura creada exitosamente.");

                // Verificar si el archivo existe
                if (archivoPDF.exists()) {
                    System.out.println("El archivo PDF se ha guardado correctamente.");
                } else {
                    System.out.println("Error: El archivo PDF no se ha encontrado en la ubicación especificada.");
                }
            } catch (FileNotFoundException | DocumentException ex) {
                Logger.getLogger(ExportarFacturaTest.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error al crear el archivo PDF: " + ex.getMessage());
            }
        } else {
            System.out.println("Operación cancelada por el usuario.");
        }
    }

    // Método para agregar filas de productos a la tabla
    private static void addProductRow(PdfPTable table, String description, int quantity, String price) {
        table.addCell(new Phrase(description, FontFactory.getFont(FontFactory.HELVETICA, 12)));
        table.addCell(new Phrase(String.valueOf(quantity), FontFactory.getFont(FontFactory.HELVETICA, 12)));
        table.addCell(new Phrase(price, FontFactory.getFont(FontFactory.HELVETICA, 12)));
    }
}
