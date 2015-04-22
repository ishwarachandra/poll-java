package testPoll;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class Poll {


    static List<Double> lat = new ArrayList<Double>();
    static List<Double> lng = new ArrayList<Double>();

	public static void main(String[] args) {
		
		readLatLongData();
		for (int index = 0; index < lat.size();index++)
		{
			try {
				storeLatLongData(index+6, lat.get(index), lng.get(index));
				Thread.sleep(2000);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	public static void readLatLongData()
	{
        
        try {
			Scanner scanner = new Scanner(new File("D:/Test.txt"));
		     

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
	
	public static void storeLatLongData(int index, double lat, double longtitude ) throws SQLException
	{
		// TODO Auto-generated method stub
		  String url="jdbc:mysql://localhost:3306/aegis";
	        Connection conn = null;
	        PreparedStatement pStatement = null;
			try {
				conn = DriverManager.getConnection(url, "root", "root");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       // conn.setAutoCommit(false); keep auto commit false for better performance
	        
	        //query for inserting batch data
	        String query = "insert into trip_track values (?,now(),'2','2',?,?,0,NULL,'101','2',now(),'1','11')";
	        
			try {
				pStatement = conn.prepareStatement(query);
				pStatement.setString(1, Integer.toString(index));
				pStatement.setDouble(2, lat);
				pStatement.setDouble(3, longtitude);
				pStatement.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally
			{
				pStatement.close();
				conn.close();
			}
	      
	}
	

}
