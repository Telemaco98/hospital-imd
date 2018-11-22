import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@WebServlet(
		name = "RecuperaServlet", 
		urlPatterns = {"/consulta"}
)
public class RecuperaServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NamespaceManager.set("ClienteX");
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query("Patient"); 
		PreparedQuery pq = datastoreService.prepare(q);  
		
		int count = 1;
		
		for (Entity result : pq.asIterable()) {   
		    String titulo = (String) result.getProperty("Name");   
			response.getWriter().write("Patient " + count + ": " + titulo + "<br>");
		    count++;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NamespaceManager.set("ClienteX");
		doGet(request, response);
	}
}
