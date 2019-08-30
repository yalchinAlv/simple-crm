package PDFgenerator;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.HyphenationAuto;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.hyphenation.Hyphenation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class ReportGenerator {

    String adress = "";

    public ReportGenerator(){
        adress = "./reports/";
    }


    public boolean generateProposal(String docName, String companyFullName, double mFee, double discount,
                                    double discMFee, int CPU, int RAM){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(adress+ docName + ".pdf"));
        }
        catch ( Exception e){
            return false;
        }


        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK);


        String content = "The initial proposal to " +companyFullName +" consists of " +  CPU+ " CPU(24 GHz each) and " +
                "" + RAM + " of RAM(GB) each. The total monthly fee is " +mFee+ " which is offered at a discount " +
               " of " +discount+ " making the price " +discMFee+ ".";


        try {
            Chunk chunk = new Chunk(content, font);
            HyphenationAuto auto = new HyphenationAuto("en", "GB", 2, 2);
            chunk.setHyphenation(auto);
            Paragraph p = new Paragraph(chunk);
            p.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            document.add(p);
            //document.add(chunk);

        }
        catch ( DocumentException de){
            return false;
        }
        document.close();
        return true;
    }

    public boolean generatInvoice( Double mFee, int noOfMonths,  double discount, String cName, String billingAdress){

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(adress+ cName +  "Invoice.pdf"));
        }
        catch ( Exception e){
            return false;
        }


        double discounted = (mFee - mFee*discount/100)*12;
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK);
        String content = "Amount: " +mFee+ " x " +noOfMonths +" Months = " +mFee*noOfMonths+ " AZN. Discount " +
                "of " +discount+ " Discounted: " +discounted + "      BILLING ADRESS: " +billingAdress;

        try {
            Chunk chunk = new Chunk(content, font);
            HyphenationAuto auto = new HyphenationAuto("en", "GB", 2, 2);
            chunk.setHyphenation(auto);
            Paragraph p = new Paragraph(chunk);
            p.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            document.add(p);
            //document.add(chunk);


        }
        catch ( DocumentException de){
            return false;
        }
        document.close();
        return true;
    }
}
