package com.exadel.borsch.notification;

import com.exadel.borsch.dao.User;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;

/**
 * @author zubr
 */
public class PrintedNotification extends Notification {
    protected static final Logger LOGGER = Logger.getLogger(PrintedNotification.class.getName());
    private String printerName;
    private int numberOfCopies;

    public PrintedNotification(String printerName, int numberOfCopies, String message) {
        super(message);
        this.printerName = printerName;
        this.numberOfCopies = numberOfCopies;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    private PrintService findPrintService(String printerName) {
        PrintService[] services = PrinterJob.lookupPrintServices();

        for (int i = 0; i < services.length; i++) {
            if (services[i].getName().equalsIgnoreCase(printerName)) {
                return services[i];
            }
        }

        return null;
    }

    @Override
    public void submit(User target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void submit(List<User> targets) {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintService service = findPrintService(printerName);
            if (service != null) {
                job.setPrintService(service);
            }
            job.setCopies(numberOfCopies);
            job.setPrintable(new TextPrintable(getMessage()));
            job.print();
        } catch (PrinterException ex) {
            // TODO: handle somewhere else? Rethrow as internal borsch ex?
            LOGGER.log(Level.SEVERE, "Some printer exception occured", ex);
        }
    }

    private static class TextPrintable implements Printable {
        private static final int FONT_SIZE = 10;
        private String text;

        public TextPrintable(String text) {
            this.text = text;
        }

        @Override
        public int print(Graphics g, PageFormat format, int pageIndex) throws PrinterException {
            // TODO: some templating, also some nicer page
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(new Font("TimesNewRoman", Font.PLAIN, FONT_SIZE));
            int x = (int) format.getImageableX();
            int y = (int) format.getImageableY();
            // TODO: multiline output
            g2.drawString(text, x, y);
            return Printable.PAGE_EXISTS;
        }
    }
}
