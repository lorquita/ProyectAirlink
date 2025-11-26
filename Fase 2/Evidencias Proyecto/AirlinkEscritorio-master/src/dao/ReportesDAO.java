package dao;

import java.io.FileOutputStream;
import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.util.ArrayList;
import java.util.List;

public class ReportesDAO {

    public void generarReporteViajes(String rutaArchivo) {
        String sql = """
            SELECT 
                v.idViaje,
                v.salida,
                v.llegada,
                t1.nombreTerminal AS Origen,
                t2.nombreTerminal AS Destino,
                e.nombreEmpresa AS Empresa,
                v.estado
            FROM viaje v
            LEFT JOIN ruta r ON v.idRuta = r.idRuta
            LEFT JOIN terminal t1 ON r.idTerminalOrigen = t1.idTerminal
            LEFT JOIN terminal t2 ON r.idTerminalDestino = t2.idTerminal
            LEFT JOIN empresa_equipo eq ON v.idEquipo = eq.idEquipo
            LEFT JOIN empresa e ON eq.idEmpresa = e.idEmpresa
            ORDER BY v.salida;
        """;

        List<String[]> datos = new ArrayList<>();

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int contador = 0;
            while (rs.next()) {
                String[] fila = new String[]{
                    rs.getString("idViaje"),
                    rs.getString("Origen"),
                    rs.getString("Destino"),
                    rs.getString("salida"),
                    rs.getString("llegada"),
                    rs.getString("Empresa"),
                    rs.getString("estado")
                };
                datos.add(fila);
                contador++;

                // Para depuración
                System.out.println("✅ Viaje ID " + rs.getInt("idViaje") + " - " +
                        rs.getString("Origen") + " → " + rs.getString("Destino"));
            }

            if (contador == 0) {
                System.out.println("⚠️ No se encontraron viajes para el reporte.");
            } else {
                System.out.println("✅ Total de viajes encontrados: " + contador);
            }

            // Crear PDF
            crearPDF(datos, rutaArchivo);

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener datos de reporte: " + e.getMessage());
        }
    }

    private void crearPDF(List<String[]> datos, String rutaArchivo) {
        Document doc = new Document(PageSize.A4.rotate());
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(rutaArchivo));
            doc.open();

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
            Paragraph titulo = new Paragraph("Reporte de Viajes Airlink\n\n", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);

            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{1.2f, 2.5f, 2.5f, 2.8f, 2.8f, 2.5f, 2f});

            // Encabezados
            String[] encabezados = {"ID", "Origen", "Destino", "Salida", "Llegada", "Empresa", "Estado"};
            for (String encabezado : encabezados) {
                PdfPCell celda = new PdfPCell(new Phrase(encabezado, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(celda);
            }

            // Datos
            for (String[] fila : datos) {
                for (String valor : fila) {
                    tabla.addCell(new Phrase(valor != null ? valor : "N/D"));
                }
            }

            doc.add(tabla);
            doc.add(new Paragraph("\nTotal de viajes registrados: " + datos.size()));

            System.out.println("✅ Reporte PDF generado correctamente: " + rutaArchivo);

        } catch (Exception e) {
            System.out.println("❌ Error al generar PDF: " + e.getMessage());
        } finally {
            doc.close();
        }
    }
}
