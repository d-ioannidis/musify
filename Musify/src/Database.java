import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Container;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;


public class Database {
	Connection conn = null;
	private Statement stmt = null;
	private ArrayList<Photo> photos = new ArrayList<Photo>();
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
	
	static final String USER = "root";
	static final String PASS = "N123456789";
	
	
	
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
	
	public int updateData(String Username, String Password) {
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String query = "UPDATE register SET PASSWORD = '"+ Password +"' WHERE USERNAME = '"+ Username +"'";
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt.executeUpdate();
			
			conn.close();
		}
		catch (SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "User doesn't exist","Error", JOptionPane.ERROR_MESSAGE);
			return 1;
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
		return 0;
	}
	public void connec(){
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
	
	public ArrayList<Photo> getImageArtist(){
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			String sql_query = "SELECT PHOTO_ARTIST FROM artist";
			
			ResultSet rs = stmt.executeQuery(sql_query);
			
			while(rs.next()) {
				photos.add(new Photo(
						rs.getBlob(1))
						);
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

	       return photos;
	    }
	

	public ResultSet SearchMusic(String setext){
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		String query = "SELECT * FROM artist WHERE NAME IN ('"+ setext +"') OR NICKNAME IN ('"+ setext +"') OR LASTNAME IN ('"+ setext +"');";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		ResultSet rs = preparedStmt.executeQuery(query);
		
		
		return rs;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public DefaultTableModel selectDataArtist() {
	      DefaultTableModel dm = new DefaultTableModel(0, 0);
	      try {
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         stmt = conn.createStatement();
	         String sql;
	         sql = "SELECT NICKNAME, TRACK_NAME FROM ARTIST, TRACKS WHERE ARTIST.ID_ARTIST = TRACKS.ID_TRACKS ORDER BY TRACK_NAME";
	         ResultSet rs = stmt.executeQuery(sql);
	         JTable tblTaskList = new JTable();
	         String header[] = new String[] {"Track1", "Track2"};
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

