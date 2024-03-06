package MyPack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;
import java.util.Scanner;

import org.apache.catalina.valves.JsonAccessLogValve;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String api_key="cb3b3a610939f8df908de595ac7d3c22";
		String city = request.getParameter("city");
		
		String api_url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+api_key;
		try {
		URL url=new URL(api_url);
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		InputStream inputstream=con.getInputStream();
		InputStreamReader reader=new InputStreamReader(inputstream);
		
		StringBuilder response_data=new StringBuilder();
		
		Scanner scanner=new Scanner(reader);
		while(scanner.hasNext()) {
			response_data.append(scanner.nextLine());
		}
		scanner.close();
		Gson gson=new Gson();
		JsonObject jsonObject =gson.fromJson(response_data.toString(),JsonObject.class);	
	
         double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
         int temperature = (int) (temperatureKelvin - 273.15);
         
         int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
        
         double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
         
         String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
         
         request.setAttribute("city", city);
         request.setAttribute("temperature", temperature);
         request.setAttribute("weatherCondition", weatherCondition); 
         request.setAttribute("humidity", humidity);    
         request.setAttribute("windSpeed", windSpeed);
         request.setAttribute("weatherData", response_data.toString());
         
         con.disconnect();
		}catch(Exception exception) {
			exception.printStackTrace();
		}
         request.getRequestDispatcher("index.jsp").forward(request, response);
         
         
	}

}
