package pack;


public class Books {
	
	int id;
	String name, author, description;

	int count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
	 	return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Books(int id, String name, String author, String description,
			int count) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.description = description;
		this.count = count;
	}
	

}
