
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class RefreshChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
	SessionFactory factory;
	List<String> list;
	
   @Override
   public void init() throws ServletException {
	
	    cfg = new Configuration();
	    cfg.configure("hibernate.cfg.xml");
	    factory = cfg.buildSessionFactory();
	    list = new ArrayList<String>();
   }
   

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		
		String message = request.getParameter("message");
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Message msg = new Message(message);
		session.save(msg);
		String history = "";
		Query query = session.createQuery("from Message m");
		list =  query.list();
		Iterator i = list.iterator();
		//String newline = System.getProperty("line.separator");
		while(i.hasNext())
		{
			Object o = i.next();
			Message m = (Message) o;
			history = history + "/" + m.message;
		}
		tx.commit();
		session.close();
		

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.include(request, response);
		String a[] = history.split("/");
		
		for(int j=0;j<a.length;j++)
		{
			pw.print("<br/>");
			pw.print(a[j] + "\n");
		}
		
	}

}
