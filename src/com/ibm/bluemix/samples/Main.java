package com.ibm.bluemix.samples;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Servlet implementation class Main
 */
@WebServlet("/")
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //INBOUND ADAPTER
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    	ApplicationContext integrationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
      
        OutboundService outService = integrationContext.getBean( "outboundServiceImpl", OutboundService.class );  
        
        //Create Database and add Priyanka
        SpringHandler helloWorld = (SpringHandler) context.getBean("params");
        String tableName = "tbl_servicetest";
        helloWorld.createTable(tableName);
        helloWorld.createPerson("Priyanka", tableName);        

        //Send priya over the channel and get retreive the it back with a full list of names from the database
        response.getWriter().println("Names retrieved from database are: " + outService.send( "Priya" ));
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
