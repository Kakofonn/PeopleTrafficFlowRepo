package hackjunction.intelligentbuildings.peopletrafficflow.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hackjunction.intelligentbuildings.peopletrafficflow.TrafficFlowController;

@WebServlet ("/ConnectorServlet")
public class ConnectorServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private TrafficFlowController trafficController;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	trafficController = new TrafficFlowController(this);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html");
    	resp.getWriter().print("Hello World!");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String jsonData = request.getParameter("json");
    	String jsonResponse = trafficController.alocateUser(jsonData);
    	sendResponseBack(response, jsonResponse);
    }
    
    private void sendResponseBack(HttpServletResponse response, String jsonData) {
    	response.setContentType("application/json");
    	try {
    		PrintWriter out;
    		out = response.getWriter();
    		out.println(jsonData) ; 
    		out.flush();
    		out.close(); 
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
//This is example on how to listen to and send json or any other data type
//    public class ExampServlet extends HttpServlet {
//    	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
//    	{
//    		// to accept the json data
//
//    		String jsonData = request.getParameter("json");  
//
//    		// to send out the json data
//
//    		response.setContentType("application/json");
//    		PrintWriter out = response.getWriter();
//    		out.println(jsonData) ; 
//			out.flush();
//    		out.close(); 
//    	}
//    }
}
