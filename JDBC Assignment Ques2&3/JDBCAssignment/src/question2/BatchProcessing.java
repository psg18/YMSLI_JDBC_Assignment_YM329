package question2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ConnectionFactory.ConnectionFactory;

public class BatchProcessing {
	
	private static Connection connection = ConnectionFactory.getConnection();
	
	private static void insertWithoutBatchProcessing() throws SQLException {

		String sqlQuery = "insert into Employee(ename,city,salary) values (?,?,?)";
		try{
		     PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
		   		     
		     for(int i=1; i<= 1000;i++){
		          pstmt.setString(1, "User "+ i);
		          pstmt.setString(2, "City "+ i);
		          pstmt.setDouble(3, (10000.00 * i));
		          pstmt.executeUpdate();
		     }
		     
		}catch(Exception e){
		     e.printStackTrace();
		}
	}
	private static void insertWithBatchProcessing() throws SQLException{

		String sqlQuery = "insert into Employee(ename,city,salary) values (?,?,?)";
		try{
		     PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
		     
		     connection.setAutoCommit(false);
		     
		     for(int i=1; i <= 1000; i++){
		    	 
		    	 pstmt.setString(1, "User "+ i);
		         pstmt.setString(2, "City "+ i);
		         pstmt.setDouble(3, (10000.00 * i));
		         pstmt.addBatch();
		    	 if(i%200==0) {
		    		 connection.commit();
				     int[] result = pstmt.executeBatch();
				     System.out.println("Rows Inserted: "+result.length);		    	 
				 }
			     
		     }
		     
		     connection.setAutoCommit(true);

		     
		}catch(Exception e){
		     e.printStackTrace();
		     connection.rollback();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		
		//Insertion With Batch processing 
		long startTime = System.nanoTime();
		insertWithBatchProcessing();
		long endTime = System.nanoTime();
        double elapsedTimeInSecond = (double) (endTime-startTime) / 1_000_000_000;
		System.out.println("Time for Insertion With Batch processing: "+ elapsedTimeInSecond + "s");

		//Insertion Without Batch processing
		startTime = System.nanoTime();
		insertWithoutBatchProcessing();
		endTime = System.nanoTime();
        elapsedTimeInSecond = (double) (endTime-startTime) / 1_000_000_000;
		System.out.println("\n\nTime for Insertion Without Batch processing: "+ elapsedTimeInSecond + "s");

	}

}
