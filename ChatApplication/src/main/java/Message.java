import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Message {

@Id @GeneratedValue
int id;

@Column
String message;

public Message(String message) {
	super();
	this.message = message;
}

public Message() {
	
}





}
