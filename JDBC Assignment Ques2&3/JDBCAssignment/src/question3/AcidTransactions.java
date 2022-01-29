package question3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectionFactory.ConnectionFactory;

public class AcidTransactions {
	
	private static Connection connection = ConnectionFactory.getConnection();

	
	public static void main(String[] args) throws SQLException{

		try {

			connection.setAutoCommit(false);
			
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			PreparedStatement pstmt = connection.prepareStatement("update account set balance=balance-1000 where id=?");
			pstmt.setInt(1, 1);
			pstmt.executeUpdate();

			pstmt = connection.prepareStatement("update account set balance=balance+1000 where id=?");
			pstmt.setInt(1, 2);
			pstmt.executeUpdate();

			connection.commit();
			
			System.out.println("Tranfer of Amount Successfull");

		} catch (SQLException e) {
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
