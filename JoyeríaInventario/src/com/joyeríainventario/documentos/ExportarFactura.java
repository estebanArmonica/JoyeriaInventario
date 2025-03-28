/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joyeríainventario.documentos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.joyeríainventario.models.Venta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author esteb
 */
public class ExportarFactura {

    public void generarFactura(List<Venta> ventas, String nombreCliente) throws IOException {

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
                String destino = archivoPDF.getCanonicalPath();
                PdfWriter.getInstance(document, new FileOutputStream(destino));
                document.open();

                // Agregar título
                document.add(new Paragraph("Joyeria Inventario", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK)));
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Cliente: " + nombreCliente, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK)));
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

                double total = 0;
                // Agregar productos o servicios a la tabla
                for (Venta venta : ventas) {
                    table.addCell(new Phrase(venta.getJoya().getNombre(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    table.addCell(new Phrase(String.valueOf(venta.getCantidad()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    table.addCell(new Phrase(String.valueOf(venta.getPrecioTotal()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    total += venta.getPrecioTotal();
                }

                document.add(table);
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Total: $" + total,
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));

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
                Logger.getLogger(ExportarFactura.class.getName()).log(Level.SEVERE, null, ex);
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
