package utils;

import controllers.TimeSheetCtrl;
import models.ReportWrapper;
import models.TimeSheet;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created: 14-12-2012
 * @version: 0.1
 * Filename: GenerateReport.java
 * Description:
 * @changes
 */

public class GenerateReport
{
    public void fillReport(int sheetId, boolean getPdf, String outputPath) throws Exception
    {
        TimeSheetCtrl timeSheetCtrl = new TimeSheetCtrl();
        List<ReportWrapper> listTimeSheets = new ArrayList<ReportWrapper>();
        TimeSheet timeSheet = timeSheetCtrl.getTimeSheetById(sheetId);
        listTimeSheets.add(new ReportWrapper(timeSheet));

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listTimeSheets);
        String templateLocation = System.getProperty("user.dir") + "?src?utils?templates?TimeSheet.jasper";
        templateLocation = templateLocation.replace("?", File.separator);
        JasperPrint jasperPrint = JasperFillManager.fillReport(templateLocation, new HashMap(), beanCollectionDataSource);

        if(getPdf && outputPath.length() > 0)
            exportToPdf(jasperPrint, outputPath);
        else
            exportToPrintDialog(jasperPrint);
    }

    public void fillReport(int[] sheetIds, boolean getPdf, String outputPath) throws Exception
    {
        TimeSheetCtrl timeSheetCtrl = new TimeSheetCtrl();
        List<ReportWrapper> listTimeSheets = new ArrayList<ReportWrapper>();
        for(TimeSheet timeSheet : timeSheetCtrl.getAllTimeSheets(sheetIds))
            listTimeSheets.add(new ReportWrapper(timeSheet));

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listTimeSheets);
        String templateLocation = System.getProperty("user.dir") + "?src?utils?templates?TimeSheet.jasper";
        templateLocation = templateLocation.replace("?", File.separator);
        JasperPrint jasperPrint = JasperFillManager.fillReport(templateLocation, new HashMap(), beanCollectionDataSource);

        if(getPdf && outputPath.length() > 0)
            exportToPdf(jasperPrint, outputPath);
        else
            exportToPrintDialog(jasperPrint);
    }

    protected void exportToPdf(JasperPrint populatedReport, String exportLocation) throws Exception
    {
        System.out.println("Exporting to PDF");
        JRPdfExporter exporterPdf = new JRPdfExporter();
        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT,  populatedReport);
        exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, exportLocation);
        exporterPdf.exportReport();
    }

    protected void exportToPrintDialog(JasperPrint populatedReport) throws Exception
    {
        JasperPrintManager.printReport(populatedReport, true);
    }
}
