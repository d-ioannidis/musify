import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Container;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Database {
	Connection conn = null;
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
	
	// Αλλαγή ονόματος από SearchMusic σε SearchDataArtist()
	public ResultSet SearchDataArtist(String setext){
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
	         sql = "SELECT NICKNAME, TRACK_NAME FROM artist,tracks,participate WHERE (artist.ID_ARTIST = participate.ID_ARTIST) AND (tracks.id_tracks = participate.id_tracks) "
	         		+ "ORDER BY TRACK_NAME";
	         ResultSet rs = stmt.executeQuery(sql);
	         JTable tblTaskList = new JTable();
	         String header[] = new String[] {"Nickname", "Track2"};
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
	
	// TEST CONNECTION IN DATABASE
	public static void main(String[]args) {
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Sarap4610_Kof4665_Ioan4578_Alex4631";
		
		System.out.println("Connecting to server...");
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			System.out.println("Server connected");
			Statement stmt = null;
			ResultSet resultset = null;
			
			try {
				stmt = connection.createStatement();
				resultset = stmt.executeQuery("SHOW DATABASES;");
				
				if(stmt.execute("SHOW DATABASES;")) {
					resultset = stmt.getResultSet();
				}
				while(resultset.next()) {
					System.out.println(resultset.getString("Database"));
				}
			}
			catch (SQLException ex){
	            ex.printStackTrace();
	        }
	        finally {
	            if (resultset != null) {
	                try {
	                    resultset.close();
	                } catch (SQLException sqlEx) { }
	                resultset = null;
	            }

	            if (stmt != null) {
	                try {
	                    stmt.close();
	                } catch (SQLException sqlEx) { }
	                stmt = null;
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    } catch (SQLException e) {
	        throw new IllegalStateException("Cannot connect the server!", e);
	    }
		}
	public List<Artist> getArtists(){
		List<Artist> artists = new ArrayList<>();
		
		try {
	         conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         stmt = conn.createStatement();
	         String sql;
	         sql = "SELECT * FROM artist ORDER BY NICKNAME";
	         ResultSet rs = stmt.executeQuery(sql);
	         
	         
	         while (rs.next()) {
		            
		            
		            artists.add(new Artist(
		            		rs.getInt("ID_ARTIST"), 
		            		rs.getString("NAME"), 
		            		rs.getString("LASTNAME"), 
		            		rs.getString("NICKNAME"), 
		            		rs.getString("BIRTHDAY"), 
		            		rs.getString("FIRST_TRACK_DATE"), 
		            		rs.getString("NATIONALITY"), 
		            		rs.getBlob("PHOTO_ARTIST")
		            		));
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
		return artists;
	      
	}	
}

