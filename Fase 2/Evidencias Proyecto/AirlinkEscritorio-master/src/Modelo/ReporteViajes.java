package Modelo;

import dao.ReportesDAO;

public class ReporteViajes {

    public void generarPDF(String rutaDestino) {
        try {
            ReportesDAO dao = new ReportesDAO();
            dao.generarReporteViajes(rutaDestino); // 🔗 usa el método real
            System.out.println("✅ Reporte generado exitosamente en: " + rutaDestino);
        } catch (Exception e) {
            System.out.println("❌ Error al generar reporte de viajes: " + e.getMessage());
        }
    }
}
