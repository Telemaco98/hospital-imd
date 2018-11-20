import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@WebServlet(
		name = "PatientRegisterServlet",
		urlPatterns = {"/patient_register"}
)
public class PatientResgisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.setContentType("UFT-8");
		
		String p_name 	= req.getParameter("name");
		String p_email 	= req.getParameter("email");
		String p_cpf 	= req.getParameter("cpf");
		String p_genre 	= req.getParameter("genre");
		String p_cell 	= req.getParameter("cellphone");
		
		Entity patient = new Entity("Patient");
		patient.setProperty("Name", p_name);
		patient.setProperty("Email", p_email);
		patient.setProperty("CPF", p_cpf);
		patient.setProperty("Genre", p_genre);
		patient.setProperty("Cellphone", p_cell);
		
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(patient);
		resp.getWriter().print("Register of the patient was sucess!\r\n");
	}
}
