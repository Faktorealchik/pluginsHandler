package main;

import helpers.MainHelper;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.ExitServlet;
import servlets.ListenServlet;

public class Main {

    public static void main(String[] args) throws Exception {
        MainHelper help = new MainHelper();

//        Runtime.getRuntime().addShutdownHook(exit); //позволяет закрыть все открытые плагины.

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ListenServlet(help.getMap())), "/listen");
        context.addServlet(new ServletHolder(new ListenServlet(help.getMap())), "/again");
        context.addServlet(new ServletHolder(new ExitServlet(help.getMap())),"/close");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();

    }
}