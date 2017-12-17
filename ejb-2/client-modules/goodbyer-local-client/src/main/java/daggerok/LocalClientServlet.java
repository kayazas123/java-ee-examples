package daggerok;

import daggerok.api.egb.local.GoodbyerLocal;
import lombok.SneakyThrows;
import lombok.val;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class LocalClientServlet extends HttpServlet {

  private static final long serialVersionUID = 6621759119315184794L;

//  // way 1:
//  @EJB(lookup = "java:global/ear-0.0.1/greeter-impl-0.0.1/GreeterImpl!daggerok.api.ejb.remote.GreeterRemote")
//  GreeterRemote greeter;

//  // way 2:
//  @EJB(beanName = "GreeterImpl")
//  GreeterRemote greeter;

//  // way 3:
//  @EJB(name = "GreeterImpl")
//  GreeterRemote greeter;

//  // way 4:
//  @EJB(name = "greet")
//  GreeterRemote greeter;

//  // way 5 (new):
//  @EJB
//  GreeterRemote greeter;

  @Override
  public void init() throws ServletException {
    super.init();
    System.out.println("LocalClientServlet started.");
  }

  @Override
  @SneakyThrows
  protected void service(final HttpServletRequest request,
                         final HttpServletResponse response) throws ServletException, IOException {

    // way 6 (old):
    Context context = new InitialContext();
    GoodbyerLocal goodbyer = (GoodbyerLocal) context.lookup(
        "java:global/ear-0.0.1/goodbyer-local-client-0.0.1/GoodbyerImpl!daggerok.api.egb.local.GoodbyerLocal");

    val writer = response.getWriter();

    writer.println(format("remote EJB client says: %s", goodbyer.byeMessage()));
    writer.close();
  }

  @Override
  public void destroy() {
    super.destroy();
    System.out.println("LocalClientServlet killed.");
  }
}
