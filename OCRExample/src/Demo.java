import java.io.File;
import java.util.Arrays;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

class Demo {

	public static void main(String[] args) {

		try {
			PDDocument document = null;
			document = PDDocument.load(new File("yoga.pdf"));
			document.getClass();
			
			if (!document.isEncrypted()) 
			{
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				
				PDFTextStripper Tstripper = new PDFTextStripper();
				String st = Tstripper.getText(document);
				
				//System.out.println("Text:" + st); // to read entire text
				
				/*
				 * maybe use a stringbuilder to do whatever manipulation is required
				 */
				
				
				System.out.println(st.contains("yoga"));   // split the text to string array
				String s[] = st.split("\n");
				System.out.println(Arrays.toString(s));
				
				/*
				 * for finding text by label, parse the string array find out the label spaced  and content nxt to it ,
				 *  find substring with correct delimeter 
				 * space will ideally work 
				 * Example below  : 
				 */
				
				for(String str  : s)
				{
					if(str.contains("Experience"))
					{
						System.out.println("Total experience is  : " + str.substring(str.indexOf(" ")));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}