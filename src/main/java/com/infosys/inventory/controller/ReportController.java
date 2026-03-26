package com.infosys.inventory.controller;

import com.infosys.inventory.entity.Product;
import com.infosys.inventory.repository.ProductRepository;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.PrintWriter;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/export-csv")
    public void exportCSV(HttpServletResponse response) throws Exception {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition","attachment; filename=products.csv");

        List<Product> products=productRepo.findAll();

        PrintWriter writer=response.getWriter();

        writer.println("Name,Category,Quantity,Price");

        for(Product p:products){

            writer.println(p.getName()+","+p.getCategory()+","+p.getQuantity()+","+p.getPrice());

        }

        writer.close();
    }

    @GetMapping("/export-pdf")
    public void exportPDF(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","attachment; filename=products.pdf");

        PdfWriter writer=new PdfWriter(response.getOutputStream());
        PdfDocument pdf=new PdfDocument(writer);

        Document document=new Document(pdf);

        document.add(new Paragraph("Inventory Report"));

        Table table=new Table(4);

        table.addCell("Name");
        table.addCell("Category");
        table.addCell("Quantity");
        table.addCell("Price");

        List<Product> products=productRepo.findAll();

        for(Product p:products){

            table.addCell(p.getName());
            table.addCell(p.getCategory());
            table.addCell(String.valueOf(p.getQuantity()));
            table.addCell(String.valueOf(p.getPrice()));

        }

        document.add(table);

        document.close();

    }

}