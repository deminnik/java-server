package deminnik.javaserver.server;

import deminnik.javaserver.account.AccountService;
import deminnik.javaserver.db.DBException;
import deminnik.javaserver.servlets.MirrorRequestsServlet;
import deminnik.javaserver.servlets.SignInServlet;
import deminnik.javaserver.servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

        try {
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(new MirrorRequestsServlet()), "/mirror");
            context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
            context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

            Server server = new Server(8080);
            server.setHandler(context);

            server.start();
            java.util.logging.Logger.getGlobal().info("Server started");
            server.join();
        } catch (DBException e) {
            e.printStackTrace();
        }

        accountService.close();
    }
}
