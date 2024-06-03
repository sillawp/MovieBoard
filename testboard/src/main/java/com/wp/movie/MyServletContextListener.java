package com.wp.movie;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println(">>> ServletContext object is created.");
         
         ServletContext context = sce.getServletContext();
         DBConnectionInfo connInfo = new DBConnectionInfo();
         connInfo.setDriverName(context.getInitParameter("jdbc.driverClassName"));
         connInfo.setUrl(context.getInitParameter("jdbc.url"));
         connInfo.setUsername(context.getInitParameter("jdbc.username"));
         connInfo.setPassword(context.getInitParameter("jdbc.password"));
         
         context.setAttribute("jdbc-info", connInfo);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
        System.out.println(">>> ServletContext object is destroyed.");
    }
	
}
