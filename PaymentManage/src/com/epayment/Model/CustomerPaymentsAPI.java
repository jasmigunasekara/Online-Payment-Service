package com.epayment.Model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epayment.Model.CustomerPayment;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/CustomerPaymentsAPI")
public class CustomerPaymentsAPI extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	CustomerPayment payObj = new CustomerPayment();
	
    public CustomerPaymentsAPI() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Not Used
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException 
    {
    	String output = payObj.insertCustomerPayment(request.getParameter("payCardType"),
				request.getParameter("payCardNO"),
				request.getParameter("payExpiryDate"),
				request.getParameter("payCVV"),
				request.getParameter("payDate"),
				request.getParameter("payTotalAmount"),
				request.getParameter("payAmount"));
    	
    	response.getWriter().write(output);
		
	}
    
 // Convert request parameters to a Map
 	private static Map getParasMap(HttpServletRequest request)
     {
 		Map<String, String> map = new HashMap<String, String>();
 		try
 		{
 			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
 			String queryString = scanner.hasNext() ?
 					scanner.useDelimiter("\\A").next() : "";
 			scanner.close();
 	 
 			String[] params = queryString.split("&");
 			for (String param : params)
 			{ 
 				String[] p = param.split("=");
 				map.put(p[0], p[1]);
 		    }
 		 }
 				
 		 catch (Exception e)
 	     {
 		 }
 		 
 		return map;
 	}

		protected void doPut(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException 
	{
			Map paras = getParasMap(request);
			String output = payObj.updateCustomerPayment(paras.get("hidPayIDSave").toString(),
											   paras.get("payCardType").toString(),
											   paras.get("payCardNO").toString(),
											   paras.get("payExpiryDate").toString(),
											   paras.get("payCVV").toString(),
											   paras.get("payDate").toString(),
											   paras.get("payTotalAmount").toString(),
											   paras.get("payAmount").toString());	
		
			response.getWriter().write(output);
	}

}
