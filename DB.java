package Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.sql.*;


public class DB {
	public Connection conn = null;
	private Statement stmt = null;
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
	
	static final String USER = "root";
	static final String PASS = "Sarap4610_Kof4665_Ioan4578_Alex4631";
	
	public void addData(String Email,String Name, String Surname, String Username, String Password) {
		
		try {
		
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		
		String sql_query = "INSERT INTO register (EMAIL,NAME,SURNAME,USERNAME,PASSWORD) VALUES (?,?,?,?,?)";
		
		
		PreparedStatement preparedStmt = conn.prepareStatement(sql_query);
		
		
		preparedStmt.setString(1, Email);
		preparedStmt.setString(2, Name);
		preparedStmt.setString(3, Surname);
		preparedStmt.setString(4, Username);
		preparedStmt.setString(5, Password);
		
		preparedStmt.execute();
		
		conn.close();
		
	}
		catch (SQLException e){
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null) 
					stmt.close();
			}
				catch (SQLException e2) {
					
				}
			try {
				if(conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
			
	}
	
	public Boolean searchData(String Username, String Email) {
		Boolean flag = true; //User doesn't exist
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql_query = "SELECT * FROM register WHERE USERNAME = ? OR EMAIL = ?";
			
			PreparedStatement preparedStmt = conn.prepareStatement(sql_query);
			
			preparedStmt.setString(1, Username);
			preparedStmt.setString(2, Email);
			ResultSet rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				flag = false;
			}
			
			preparedStmt.execute();
			conn.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null) 
					stmt.close();
			}
				catch (SQLException e2) {
					
				}
			try {
				if(conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return flag;
	}
	
	public void updateData(String Password) {
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String query = "UPDATE register SET PASSWORD = ? WHERE USERNAME = ?";
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt.setString(2,Password);
			
			preparedStmt.executeUpdate();
			
			conn.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt != null) 
					stmt.close();
			}
				catch (SQLException e2) {
					
				}
			try {
				if(conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	
	
	
	public DefaultTableModel selectDataArtist() {
	      DefaultTableModel dm = new DefaultTableModel(0, 0);
	      try {
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         stmt = conn.createStatement();
	         String sql;
	         sql = "SELECT NICKNAME, TRACK_NAME FROM ARTIST, TRACKS WHERE ARTIST.ID_ARTIST = TRACKS.ID_ARTIST ORDER BY TRACK_NAME";
	         ResultSet rs = stmt.executeQuery(sql);
	         JTable tblTaskList = new JTable();
	         String header[] = new String[] {"Nickname", "Track"};
	         dm.setColumnIdentifiers(header);
	         tblTaskList.setModel(dm);

	         while (rs.next()) {
	            Vector<Object> data = new Vector<Object>();
	            data.add(rs.getString(1));
	            data.add(rs.getString(2));
	            //data.add(rs.getString(3));
	           // data.add(rs.getString(4));
	            //data.add(rs.getString(5));
	            
	            dm.addRow(data);
	         }
	         rs.close();
	         stmt.close();
	         conn.close();
	      }
	      catch (SQLException se) {
	         se.printStackTrace();
	      }
	      catch (Exception e) {
	         e.printStackTrace();
	      }
	      finally {
	         try {
	            if (stmt!=null)
	               stmt.close();
	         }
	         catch (SQLException se2) {
	         }
	         try {
	            if (conn!=null)
	               conn.close();
	         }
	         catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
	      return dm;
	   }

}
