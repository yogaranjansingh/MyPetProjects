package pack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


import javax.ws.rs.PathParam;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;

@Path("/BookService")
public class ViewAllService extends ConnectionManager{
	
	Boolean login;
	String userwa;

	@GET
	@Path("/ViewAllBooks")
	public String viewAllBooks() 
	{
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml"); 
 
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Query qry = session.createQuery("from Books b");
		 
		List list =qry.list();
		System.out.println("Total Number Of Records : "+list.size());
		Iterator itr = list.iterator();
 
		while(itr.hasNext())
		{
			Object o = (Object)itr.next();
			Books b = (Books)o;
		}	
		
		
		
		String json = new Gson().toJson(list);

		return json;

	}
	
	@GET
	@Path("/AddBook/{bname}/{bauthor}/{bcount}/{bdescription}")
	public void AddBook(@PathParam("bname") String bname, @PathParam("bauthor") String bauthor ,@PathParam("bcount") int bcount , @PathParam("bdescription") String bdescription) 
	{
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml"); 
 
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		Books b= new Books();
		
		 
		Transaction tx = session.beginTransaction();	
 
		b.setName(bname);   
		b.setAuthor(bauthor);  
		b.setCount(bcount);   
		b.setDescription(bdescription); 
 
		session.save(b);
		tx.commit();
		 
	}
	
	
	
	@GET
	@Path("/RemoveBook/{bid}")
	public void RemoveBook(@PathParam("bid") int bid) 
	{
		
		 Configuration cfg = new Configuration();
	        cfg.configure("hibernate.cfg.xml"); 
	 
	        SessionFactory factory = cfg.buildSessionFactory();
	        Session session = factory.openSession();
	 
	        Object o=session.load(Books.class,new Integer(bid));
	        Books b = (Books)o;
	 
	        Transaction tx = session.beginTransaction();
	        session.delete(b);
	        System.out.println("Object Deleted successfully.....!!");
	        tx.commit();
	        session.close();
	        factory.close();
		

	}
	
	@GET
	@Path("/IssueBook/{bid}")
	public int IssueBook(@PathParam("bid") int bid) 
	{
		
		 Configuration cfg = new Configuration();
	        cfg.configure("hibernate.cfg.xml"); 
	 
	        SessionFactory factory = cfg.buildSessionFactory();
	        Session session = factory.openSession();
	 
	        Object o = session.load(Books.class,new Integer(bid));
	        Books b = (Books)o;
	       
	        if(b.getCount()==0)
	        {
	        	return 0;
	        }
	        else
	        {
	        Transaction tx = session.beginTransaction();
	        
	        b.setCount(b.getCount()-1);
	        
	        tx.commit();
	       
	        return b.getCount()+1;
	        }
	        
		

	}
	
	@GET
	@Path("/ReturnBook/{bid}")
	public int ReturnBook(@PathParam("bid") int bid) 
	{
		
		 Configuration cfg = new Configuration();
	        cfg.configure("hibernate.cfg.xml"); 
	 
	        SessionFactory factory = cfg.buildSessionFactory();
	        Session session = factory.openSession();
	 
	        Object o=session.load(Books.class,new Integer(bid));
	        Books b = (Books)o;
	       
	       
	        Transaction tx = session.beginTransaction();
	        b.setCount(b.getCount()+1);
	        tx.commit();
	         
	        return b.getCount()-1;
		

	}
	
	@GET
	@Path("/Login/{username}/{password}")
	public String Login(@PathParam("username") String username, @PathParam("password") String password )// throws Exception
	{
		
		 Configuration cfg = new Configuration();
	        cfg.configure("hibernate.cfg.xml"); 
	 
	        SessionFactory factory = cfg.buildSessionFactory();
	        Session session = factory.openSession();
	        
	        Query query = session.createQuery("select u.name from Users u where u.username = ? and u.password = ?");
	        query.setParameter(0, username);
	        query.setParameter(1, password);
	        
	        List l = query.list();
	        int n = l.size();
	        Iterator it = l.iterator();
	        while(it.hasNext())
	        {
	           String s  = (String)it.next();
	           this.userwa = s;
	           
	           if (n>0)
		         {
		        	 this.login = true;
		         }   
	        }
	        return userwa;
	}
	
	@GET
	@Path("/Register/{name}/{age}/{profession}/{username}/{password}")
	public void Register(@PathParam("name") String name, @PathParam("age") int age, @PathParam("profession") String profession, @PathParam("username") String username, @PathParam("password") String password ) 
	{
		
		 Configuration cfg = new Configuration();
	        cfg.configure("hibernate.cfg.xml"); 
	 
	        SessionFactory factory = cfg.buildSessionFactory();
	        Session session = factory.openSession();
	
	        Users user = new Users();
	 
	      
	        user.setName(name);
	        user.setAge(age);
	        user.setProfession(profession);
	        user.setUsername(username);
	        user.setPassword(password);
	        
	        
	        Transaction tx = session.beginTransaction();
	        
	        session.save(user);
	        
	        System.out.println("Object saved successfully.....!!");
	        tx.commit();
	        session.close();
	        factory.close();
		

	}

}
