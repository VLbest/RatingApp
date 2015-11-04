package com.exia.lan.ratingapp.utils;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.view.View;

public class PDFGenerator implements I_PDFGenerator{

    PrintedPdfDocument document;
    Context ctx;

    public PDFGenerator(Context ctx, PrintAttributes attr){
        this.ctx = ctx;
        document = new PrintedPdfDocument(ctx, attr);
        this.startGeneration();
    }

    private void startGeneration() {
        PdfDocument.Page page = document.startPage(0);

        

        //TODO Do it
        /*
        http://stackoverflow.com/questions/3736367/why-isnt-there-a-getcontentview-method-for-activity
        */
    }
}
