package utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import de.redsix.pdfcompare.PdfComparator;

public class PDFTest {

	public static void read_PDF(String filePath) throws InvalidPasswordException, IOException {

		PDDocument document = PDDocument.load(new File(filePath));

		document.getClass();

		if (!document.isEncrypted()) {

			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);

			PDFTextStripper tStripper = new PDFTextStripper();

			String pdfFileInText = tStripper.getText(document);

			String lines[] = pdfFileInText.split("\\r?\\n");
			for (String line : lines) {
				System.out.println(line);
			}

		}

	}

	public static void read_PDF_pageWise(String FILE_NAME) {

		PdfReader reader;

		try {

			reader = new PdfReader(FILE_NAME);

			// pageNumber = 1
			String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

			System.out.println(textFromPage);

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void write_PDF(String FILE_NAME) {
		Document document = new Document();

		try {

			PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

			// open
			document.open();

			Paragraph p = new Paragraph();
			p.add("This is my paragraph 1");
			// p.setAlignment(Element.ATTRIBUTE_NODE);

			document.add(p);

			Paragraph p2 = new Paragraph();
			p2.add("This is my paragraph 2"); // no alignment

			document.add(p2);

			Font f = new Font();
			f.setStyle(Font.BOLD);
			f.setSize(8);

			document.add(new Paragraph("This is my paragraph 3", f));

			// close
			document.close();

			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void pdf_Comparison(String file1, String file2, String result) throws Exception {
		boolean test = new PdfComparator("file1", "file2").compare().writeTo("result");
		System.out.println(test);
	}
}
