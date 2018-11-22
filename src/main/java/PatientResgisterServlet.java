import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;

@WebServlet(
		name = "PatientRegisterServlet",
		urlPatterns = {"/patient_register"}
)
public class PatientResgisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NamespaceManager.set("ClienteX"); 
		resp.setContentType("text/plain");
		resp.setContentType("UFT-8");

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Transaction txn = (Transaction) datastore.beginTransaction();
		
		try {
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
			
			datastore.put(patient);
			
			((com.google.appengine.api.datastore.Transaction) txn).commit();
			resp.getWriter().print("Postagem realizada com sucesso!\r\n");
		} finally {
			if (((com.google.appengine.api.datastore.Transaction) txn).isActive()) {
				((com.google.appengine.api.datastore.Transaction) txn).rollback();
				resp.getWriter().print("Postagem nao realizada com sucesso!\r\n");
			}
		}
	}
}
