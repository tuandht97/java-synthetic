package org.example;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class AddAttachmentsToPDF {
    public static void main(String[] args) throws IOException {

        try (final PDDocument doc = new PDDocument()) {

            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Go to Document -> File Attachments to View Embedded Files");
            contentStream.endText();
            contentStream.close();

            // embedded files are stored in a named tree
            PDEmbeddedFilesNameTreeNode efTree = new PDEmbeddedFilesNameTreeNode();

            // first create the file specification, which holds the embedded file
            PDComplexFileSpecification fs = new PDComplexFileSpecification();
            fs.setFile("C:\\Users\\admin\\Downloads\\1.txt");

            // create a dummy file stream, this would probably normally be a FileInputStream
            byte[] data = "This is the contents of the embedded file".getBytes("ISO-8859-1");
            ByteArrayInputStream fakeFile = new ByteArrayInputStream(data);

            // now lets some of the optional parameters
            PDEmbeddedFile ef = new PDEmbeddedFile(doc, fakeFile);
            ef.setSubtype("text/plain");
            ef.setSize(data.length);
            ef.setCreationDate(Calendar.getInstance());
            fs.setEmbeddedFile(ef);

            // create a new tree node and add the embedded file
            PDEmbeddedFilesNameTreeNode treeNode = new PDEmbeddedFilesNameTreeNode();
            treeNode.setNames(Collections.singletonMap("My first attachment", fs));

            // add the new node as kid to the root node
            List<PDEmbeddedFilesNameTreeNode> kids = new ArrayList<PDEmbeddedFilesNameTreeNode>();
            kids.add(treeNode);
            efTree.setKids(kids);

            // add the tree to the document catalog
            PDDocumentNameDictionary names = new PDDocumentNameDictionary(doc.getDocumentCatalog());
            names.setEmbeddedFiles(efTree);
            doc.getDocumentCatalog().setNames(names);

            doc.save(new File("C:\\Users\\admin\\Downloads\\test1-add-attachments.pdf"));
            System.out.println("Embaded PDF file is created");
        } catch (IOException e) {
            System.err.println("Exception while trying to create pdf document - " + e);
        }
    }
}