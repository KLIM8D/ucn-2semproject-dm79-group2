package utils;

import controllers.TimeSheetCtrl;
import models.ReportWrapper;
import models.TimeSheet;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

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
    public static void main(String[] args)
    {
        try
        {
            GenerateReport  ok = new GenerateReport();
            ok.pdf();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void pdf() throws Exception
    {
        int[] sheetIds = {2};
        TimeSheetCtrl timeSheetCtrl = new TimeSheetCtrl();
        List<ReportWrapper> listTimeSheets = new ArrayList<ReportWrapper>();
        for(TimeSheet timeSheet : timeSheetCtrl.getAllTimeSheets(sheetIds))
            listTimeSheets.add(new ReportWrapper(timeSheet));

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listTimeSheets);
        String templateLocation = "/home/klim/workspace/java/ucn-2semproject-dm79-group2/src/utils/templates/TimeSheet.jasper";
        JasperPrint jasperPrint = JasperFillManager.fillReport(templateLocation, new HashMap(), beanCollectionDataSource);
        postProcessReport(jasperPrint);
    }


    protected void postProcessReport(JasperPrint populatedReport) throws Exception
    {
        System.out.println("Exporting to PDF");
        String exportLocation =  "/home/klim/report2.pdf";
        JRPdfExporter exporterPdf = new JRPdfExporter();
        exporterPdf.setParameter(JRExporterParameter.JASPER_PRINT,  populatedReport);
        exporterPdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, exportLocation);
        exporterPdf.exportReport();
    }
}
