<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html style="background-color:powderblue;" >
<body>
<h1>Welcome Roommates  !!!! </h1>
<h2>Type your message here </h2>
<form action="RefreshChatServlet">

<textarea name="message" cols="50" rows="10" >  </textarea>
<input type="submit" value="send"></input>
<input type="submit" value="refresh" name= "refresh" ></input>

</form>

</body>
</html>
