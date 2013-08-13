/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.web.controllers;

import com.exadel.borsch.util.PDFCreator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Fedor
 */
@Controller
public class PrintController {

    @Secured("ROLE_PRINT_ORDER")
    @RequestMapping("/print/pdf")
    public void loadOrderTablePDF(HttpServletRequest request, HttpServletResponse response)
            throws FileNotFoundException, IOException {
            String src = request.getParameter("src");
            File filePdf = new File("table.pdf");
            PDFCreator.create(src, filePdf.getName());
            InputStream stream = new FileInputStream(filePdf);
            byte[] data = IOUtils.toByteArray(stream);
            stream.close();
            response.setHeader("Content-type", "application/download");
            response.setHeader("Content-length", Integer.toString(data.length));
            response.setHeader("Content-transfer-encodig", "binary");
            response.setHeader("Content-disposition", "attachment; filename=table.pdf");
            response.getOutputStream().write(data);
    }
}
