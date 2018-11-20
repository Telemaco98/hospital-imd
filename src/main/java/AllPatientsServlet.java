import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;

@WebServlet (
		name = "AllPatientsServlet",
		urlPatterns = {"/patients"}
)
public class AllPatientsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Key k2 = KeyFactory.createKey("Patient", id);
		Entity pa = null;
		try {
		pa = datastoreService.get(k2);
		} catch (EntityNotFoundException e) {
		throw new ServletException(e);
		}
		resp.getWriter().write(pa.getProperty("Titulo").toString());
	}
}
