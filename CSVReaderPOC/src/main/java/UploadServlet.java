

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import au.com.bytecode.opencsv.CSVReader;




@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize=16177215)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InputStream inputStream = null;
	
	String cvsSplitBy = ",";
	BufferedReader br = null;

	CSVReader reader = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean filereq = ServletFileUpload.isMultipartContent(request);
		Part filepart = request.getPart("csv");
		if(filepart!=null)
		{
			System.out.println(filepart.getName());
			System.out.println(filepart.getContentType());
			System.out.println(filepart.getSize());
			inputStream = filepart.getInputStream();
			br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			//reader=new CSVReader(new InputStreamReader(inputStream,"UTF-8"));
		}
		
		String line[];
		CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		while((line = reader.readNext()) != null)
		{
			System.out.println("reading line------------------------------"+Arrays.toString(line));
			Question q = new Question(Integer.parseInt(line[0]),line[1],line[2],line[3],line[4],line[5],line[6]);
			Transaction tx = session.beginTransaction();
			session.save(q);
			System.out.println("Object saved successfully.....!!");
			tx.commit();
			
		}
		session.close();
		factory.close();
	}
}
