package pack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.GeneralSecurityException;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	
	static  {
		removeCryptographyRestrictions();
	}

	/*@Path("/ViewAllBooks")
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

	}*/
	
	@GET
	@Path("/ViewAllBooks")
	public String viewAllBooks() throws IOException, GeneralSecurityException 
	{
		List<Summary> list = getAllSummaryObjects();
		System.out.println("Total Number Of Records : "+list.size());
		Iterator itr = list.iterator();
 
		String json = new Gson().toJson(list);

		return json;

	}
	
	
	private List getAllSummaryObjects() throws IOException, GeneralSecurityException {
		System.out.println(Cipher.getMaxAllowedKeyLength("AES"));
		try {
			Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
			field.setAccessible(true);
			field.set(null, java.lang.Boolean.FALSE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("sample.xlsx").getFile());
		//File file = new File("./sample.xlsx");
		NPOIFSFileSystem fileSystem = new NPOIFSFileSystem(file, true);
		EncryptionInfo info = new EncryptionInfo(fileSystem);
		Decryptor decryptor = Decryptor.getInstance(info);
		if (!decryptor.verifyPassword("deepikarajiv")) {
			throw new RuntimeException("Unable to process: document is encrypted.");
		}
		InputStream dataStream = decryptor.getDataStream(fileSystem);
		Workbook workbook = new XSSFWorkbook(dataStream);
		dataStream.close();
		Sheet summarySheet = workbook.getSheet("New Summary");
		DataFormatter dataFormatter = new DataFormatter();
		
		List<Summary> list = new ArrayList<Summary>();
		int skip=4;
		for (Row row : summarySheet) {
			if(skip>=0)
			{
				skip--;
				continue;
			}
			Summary summary = new Summary();
			if(dataFormatter.formatCellValue(row.getCell(1)).equals("Grand Total") )
				break;
			summary.setId(dataFormatter.formatCellValue(row.getCell(0,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
			summary.setState(dataFormatter.formatCellValue(row.getCell(1,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
			summary.setNumberOfDistrict((dataFormatter.formatCellValue(row.getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK))));
			summary.setNumberOfBlocks(dataFormatter.formatCellValue(row.getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
			summary.setNumberOfGP(dataFormatter.formatCellValue(row.getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
			summary.setRouteKm(dataFormatter.formatCellValue(row.getCell(5,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
			summary.setCostOfAward(dataFormatter.formatCellValue(row.getCell(6,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
			summary.setNumberOfGpInWO(dataFormatter.formatCellValue(row.getCell(7,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
			summary.setIncrementalLength(dataFormatter.formatCellValue(row.getCell(8,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
			list.add(summary);
		}
		workbook.close();
		return list;
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

	private static void removeCryptographyRestrictions() {
		if (!isRestrictedCryptography()) {
			System.out.println(("Cryptography restrictions removal not needed"));
			return;
		}
		try {
			/*
			 * Do the following, but with reflection to bypass access checks:
			 *
			 * JceSecurity.isRestricted = false; JceSecurity.defaultPolicy.perms.clear();
			 * JceSecurity.defaultPolicy.add(CryptoAllPermission.INSTANCE);
			 */
			final Class<?> jceSecurity = Class.forName("javax.crypto.JceSecurity");
			final Class<?> cryptoPermissions = Class.forName("javax.crypto.CryptoPermissions");
			final Class<?> cryptoAllPermission = Class.forName("javax.crypto.CryptoAllPermission");

			final Field isRestrictedField = jceSecurity.getDeclaredField("isRestricted");
			isRestrictedField.setAccessible(true);
			final Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(isRestrictedField, isRestrictedField.getModifiers() & ~Modifier.FINAL);
			isRestrictedField.set(null, false);

			final Field defaultPolicyField = jceSecurity.getDeclaredField("defaultPolicy");
			defaultPolicyField.setAccessible(true);
			final PermissionCollection defaultPolicy = (PermissionCollection) defaultPolicyField.get(null);

			final Field perms = cryptoPermissions.getDeclaredField("perms");
			perms.setAccessible(true);
			((Map<?, ?>) perms.get(defaultPolicy)).clear();

			final Field instance = cryptoAllPermission.getDeclaredField("INSTANCE");
			instance.setAccessible(true);
			defaultPolicy.add((Permission) instance.get(null));

		} catch (final Exception e) {
			System.out.println(( "Failed to remove cryptography restrictions" +e));
		}
	}

	private static boolean isRestrictedCryptography() {
		// This matches Oracle Java 7 and 8, but not Java 9 or OpenJDK.
		final String name = System.getProperty("java.runtime.name");
		final String ver = System.getProperty("java.version");
		return name != null && name.equals("Java(TM) SE Runtime Environment") && ver != null
				&& (ver.startsWith("1.7") || ver.startsWith("1.8"));
	}
	
}
