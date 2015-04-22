package testPoll;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
public class RouteThree {
	
	//private   String LOCATION_JSON1 = "{   \"location\": [       {\"lat\": %f,\"lng\": %f,\"incident\": \"Normal\",\"status\": \"on\",\"trip_id\": \"%s\",\"route_id\": \"%s\",\"route_name\": \"\",\"driver_id\": \"%s\",\"driver_name\": \"\",\"vehicle_number\": null,\"vehicle_id\": \"%s\",\"employee_id\": null,\"employeeName\": null}]}";
	private   String LOCATION_JSON = "{   \"location\":{\"lattitude\": %f,\"longitude\": %f},\"trip\":{ \"driverId\": \"%s\",\"routeId\": \"%s\",\"tripId\": \"%s\",\"vehicleId\": \"%s\"},\"dateTime\": \"2015-04-14T02:16:47,592883024-0500\"}";
	static List<Double> lat = new ArrayList<Double>();
	static List<Double> lng = new ArrayList<Double>();
 public static void main(String[] args) throws ClientProtocolException, IOException {
	 RouteThree obj=new RouteThree();
	 readLatLongData();
		for (int index = 0; index < lat.size();index++)
		{
			try {
				String jsonVal=obj.storeLatLongData(index+6, lat.get(index), lng.get(index));
				HttpClient client = new DefaultHttpClient();
				  HttpPost post = new HttpPost("http://localhost:8080/aegis-web-1.0-SNAPSHOT/services/v1/location");
				
				  //JSONEntity i = new JsonEntity(jsonVal);
				  StringEntity input = new StringEntity(jsonVal);
				  input.setContentType("application/json");
				  post.setEntity(input);
				  HttpResponse response = client.execute(post);
				  System.out.println(response.getStatusLine().getStatusCode());
				  System.out.println("Response is"+response);
				Thread.sleep(500);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		  
	}
		 	 
		 public static void readLatLongData()
			{
		     
		     try {
					Scanner scanner = new Scanner(new File("D:/Routeval/Route1.txt"));
				     
		
			        while(scanner.hasNext()){
			            String[] tokens = scanner.nextLine().split(",");
			            lat.add(Double.parseDouble(tokens[0]));
			            lng.add(Double.parseDouble(tokens[1]));
			        }
			        scanner.close();
			        } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			public  String storeLatLongData(int index, double lat, double longtitude ) throws SQLException
			{
				String locJson = String.format(LOCATION_JSON, lat, longtitude, 1, 10,  11, 201);
				System.out.println("jsonval - "+locJson);
				return locJson;
			}
		 
		 
		 
		}