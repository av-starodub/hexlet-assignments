package exercise;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import java.io.File;

import exercise.servlet.WelcomeServlet;
import exercise.servlet.UsersServlet;

public class App {

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 5000;
    }

    public static Tomcat getApp(int port) {
        Tomcat tomcat = new Tomcat();

        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.setPort(port);

        Context ctx = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());

        Tomcat.addServlet(ctx, "WelcomeServlet", new WelcomeServlet());
        ctx.addServletMappingDecoded("", "WelcomeServlet");

        Tomcat.addServlet(ctx, "UsersServlet", new UsersServlet());
        ctx.addServletMappingDecoded("/users/*", "UsersServlet");

        return tomcat;
    }

    public static void main(String[] args) throws LifecycleException {
        Tomcat app = getApp(getPort());
        app.start();
        app.getServer().await();
    }

}
