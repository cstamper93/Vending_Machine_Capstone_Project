package com.techelevator.application;

import com.techelevator.models.Items;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sales {

    private File salesFile;
    private BigDecimal totalSales;

    public Sales(String salesFileName) {
        this.salesFile = new File(salesFileName);
    }

    public void setTotalSales(BigDecimal newSale) {
        totalSales = totalSales.add(newSale);
    }

    public void write(List<Items> itemsList) {

        try (PrintWriter writer = new PrintWriter(salesFile)){
            writer.println("Taste Elevator Sales Report");
            for(Items item : itemsList) {
                writer.print(item.getName() + "|");
                writer.print(6 - (item.getAmountLeft() + item.getSoldAtDiscount()) + "|");
                writer.print(item.getSoldAtDiscount() + "\n");
            }
            writer.println("TOTAL SALES " + String.format("%.2f", totalSales));
        } catch (FileNotFoundException e) {
            System.out.println("Error writing sales report.");
        }
    }
}
