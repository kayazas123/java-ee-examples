package daggerok;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//tag::content[]
@ApplicationScoped
@ApplicationPath("")
public class App extends Application { }
//end::content[]