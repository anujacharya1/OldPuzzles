package com.anuj.client;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.core.MediaType;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CallingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    		String endPoint = "http://localhost:8080/Mail_TomCat/group";
    		JSONObject row = new JSONObject();
    		JSONArray b = new JSONArray();
    	
    		
    				String url = endPoint;
    				RestClient client = new RestClient();
    				Resource resource = client.resource(url);
    				b =  resource.accept(MediaType.APPLICATION_JSON).get(JSONArray.class);
    			Map<String,String> map = new HashMap<String,String>();
    		    Iterator iter = row.keys();
    		    while(iter.hasNext()){
    		        String key = (String)iter.next();
    		        String value = null;
					try {
						value = row.getString(key);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		        map.put(key,value);
    		    }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>All Group</title>");
        out.println("</head>");
        out.println("<body>");
       // out.println() 
        for (int i = 0; i < b.length(); i++) {
		    try {
				row = b.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        out.println("<h1>"+row+"</h1>");}
        out.println("</body>");
        out.println("</html>");
    }
}