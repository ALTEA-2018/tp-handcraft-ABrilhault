import annotations.Controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

	// Cette Map va contenir l’association entre une URI et la méthode Java qui l’écoute (annotée @RequestMapping)
	private Map<String, Method> uriMappings = new HashMap<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Getting request for " + req.getRequestURI());
		// TODO
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// on enregistre notre controller au démarrage de la servlet
		this.registerController(HelloController.class);
	}

	protected void registerController(Class controllerClass){
		System.out.println("Analysing class " + controllerClass.getName());

			Annotation a = controllerClass.getAnnotation(Controller.class);
			if (a == null) throw new IllegalArgumentException("not a controller");
			for (Method m : controllerClass.getMethods()) {
				registerMethod(m);
			}

	}

	protected void registerMethod(Method method) {
		System.out.println("Registering method " + method.getName());
		// TODO
	}

	protected Map<String, Method> getMappings(){
		return this.uriMappings;
	}

	protected Method getMappingForUri(String uri){
		return this.uriMappings.get(uri);
	}

}
