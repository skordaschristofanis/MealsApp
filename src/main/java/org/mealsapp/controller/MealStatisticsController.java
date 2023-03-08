package org.mealsapp.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.mealsapp.model.MainModel;
import org.mealsapp.view.MealStatisticsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


public class MealStatisticsController {

    private final MealStatisticsView view;
    private final MainModel model;

    public MealStatisticsController(MealStatisticsView view, MainModel model) {
        this.view = view;
        this.model = model;

        // Update the meal table
        this.view.btnRefreshTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear existing rows
                view.statisticsTableModel.setRowCount(0);

                // Get meals shorted by descending order of views
                List<Object[]> queryResults = model.getQueryResult();

                for (Object[] object : queryResults) {
                    // Add row
                    String[] row = new String[]{
                            object[0].toString(),
                            object[5].toString(),
                            object[2].toString(),
                            object[3].toString(),
                            object[1].toString()
                    };
                    view.statisticsTableModel.addRow(row);
                }
            }
        });

        // Create .pdf file
        this.view.btnSaveToPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.tblMealStatistics.getRowCount() >= 1 && view.tblMealStatistics.getValueAt(0, 0) != null) {
                    try {
                        // Create document
                        Document doc = new Document(PageSize.A4.rotate());
                        PdfWriter.getInstance(doc, new FileOutputStream("meal.pdf"));

                        // Open .pdf
                        doc.open();

                        // Create statistics pdf table
                        PdfPTable pdfTable = new PdfPTable(view.tblMealStatistics.getColumnCount());

                        // Set full width
                        pdfTable.setWidthPercentage(100);

                        // Set title
                        for (int i = 0; i < view.tblMealStatistics.getColumnCount(); i++) {
                            PdfPCell cell = new PdfPCell(new Paragraph(view.tblMealStatistics.getColumnName(i)));
                            // Set color
                            cell.setBackgroundColor(BaseColor.GREEN);
                            pdfTable.addCell(cell);
                        }

                        // Add the data to the pdf table
                        for (int rows = 0; rows < view.tblMealStatistics.getRowCount(); rows++) {
                            for (int cols = 0; cols < view.tblMealStatistics.getColumnCount(); cols++) {
                                pdfTable.addCell(view.tblMealStatistics.getModel().getValueAt(rows, cols).toString());
                            }
                        }

                        // Add to pdf and close the doc
                        doc.add(new Paragraph("R4. Προβολή στατιστικών δεδομένων γευμάτων σε αρχείο pdf "));
                        doc.add(Chunk.NEWLINE);
                        doc.add(pdfTable);
                        doc.close();

                        // Open the .pdf file to the user
                        File myFile = new File("meal.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (Exception ex) {

                        System.out.println(ex);

                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Παρακαλώ πατήστε πρώτα το κουμπί Προβολή στατιστικών ",
                            "Ενημέρωση",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
