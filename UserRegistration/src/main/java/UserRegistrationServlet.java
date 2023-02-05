import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * Servlet implementation class UserRegistrationServerlet
 */
@MultipartConfig
@WebServlet("/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		String gender = request.getParameter("gender");
		String experience = request.getParameter("experience");
		String fileName = request.getParameter("fileName");
		

		/*
		 * request.getPart is to get the uploaded file handler. You can use
		 * filePart.getInputStream() to read the streaming data from client, for
		 * example: InputStream filecontent = filePart.getInputStream();
		 */
		Part filePart = request.getPart("file");

		/*
		 * Write your code here Step 1: check whether the client's inputs are
		 * complete or not; if anything is missing, return a web page that
		 * contains a link to go back to the registration page (e.g.,
		 * UserRegistration.html)
		 */
		response.setContentType("text/html;Charset=UTF-8");
		if (name.isEmpty() || email.isEmpty() || location.isEmpty() || gender == null || experience.isEmpty() || fileName.isEmpty() || filePart == null) {
			out.println("Your input information is not complete, try again <a href=\"/UserRegistration/UserRegistration.html\">go back</a>");
		} else {
			/*
			 * Step 2: save the uploaded picture under your project WebContent
			 * directory, for example, mine is
			 * "F:\workspace\UserRegistrationProject\WebContent". Step 3: send back
			 * the client's registration information to the client, remember, the
			 * client should be able to see all the information, including the
			 * profile picture.
			 */
		
			InputStream filecontent = filePart.getInputStream();
			OutputStream fileout = new FileOutputStream("/Users/linzeng/eclipse-workspace/UserRegistration/src/main/webapp/image/"+fileName);
			byte[] buffer = new byte[1000];
			int read = filecontent.read(buffer);
			while (read != -1) {
				fileout.write(buffer, 0, read);
				read = filecontent.read(buffer);
			}
			fileout.close();
			filecontent.close();			
			out.println("<!DOCTYPE html>\n" +
					"<html>\n" +
					"<head>\n" +
					"    <title>User Registration</title>\n" +
					"</head>\n" +
					"<body>\n" +
					"<h1>Welcome " + name + "</h1>" +
					"    <ul>\n" +
					"        <li><b>Your name</b>: " + name + "</li>\n" +
					"        <li><b>Your email</b>: " + email + "</li>\n" +
					"        <li><b>Your location</b>: " + location + "</li>\n" +
					"        <li><b>Your gender</b>: " + gender + "</li>\n" +
					"        <li><b>Your experience</b>: " + experience + "</li>\n" +
					"        <li><b>Your profile picture" + fileName + " has been uploaded successful</b>:</li>\n" +
					"    </ul>\n" +
					"     <img src=\"http://www.csc.lsu.edu/~qywang/CSC7610/HTMLExercise/project/myProPic.jpg\">" +

					"</body>\n" +
					"</html>\n");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}