import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Write assertions for DB Class")
public class DBTest {
	
	private Connection conn = null;
	private QueryRunner qr = new QueryRunner();
	private List<String> results;
	private Statement stmt = null;
	
	public void finalize() throws Throwable {
		//System.out.println("Object is destroyed by the Garbage Collector");
		if(conn != null)
			DbUtils.closeQuietly(conn);		
	}
    
    public DBTest() throws Exception {
    	
        conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "Sarap4610_Kof4665_Ioan4578_Alex4631");

        // Create a new table artist
        qr.update(conn, "CREATE TABLE IF NOT EXISTS `artist` ("
        		+ "`ID_ARTIST` int NOT NULL AUTO_INCREMENT,"
        		+ "`NAME` varchar(100) DEFAULT NULL,"
        		+ "`LASTNAME` varchar(100) DEFAULT NULL,"
        		+ "`NICKNAME` varchar(100) DEFAULT NULL,"
        		+ "`BIRTHDAY` date DEFAULT NULL,"
        		+ "`FIRST_TRACK_DATE` date DEFAULT NULL,"
        		+ "`NATIONALITY` varchar(100) DEFAULT NULL,"
        		+ "`PHOTO_ARTIST` longblob,"
        		+ "PRIMARY KEY (`ID_ARTIST`)"
        		+ ") ENGINE=InnoDB AUTO_INCREMENT=1");   
        
        // Create a new table register    
        qr.update(conn, "CREATE TABLE IF NOT EXISTS `register` ("
        		+ "`ID` int NOT NULL AUTO_INCREMENT,"
        		+ "`EMAIL` varchar(100) NOT NULL,"
        		+ "`NAME` varchar(100) NOT NULL,"
        		+ "`SURNAME` varchar(100) NOT NULL,"
        		+ "`USERNAME` varchar(100) NOT NULL,"
        		+ "`PASSWORD` varchar(100) DEFAULT NULL,"
        		+ "PRIMARY KEY (`ID`)"            		
        		+ ") ENGINE=InnoDB AUTO_INCREMENT=1");
        
        results = qr.query(conn, "SELECT * FROM artist", new ColumnListHandler<String>());
        
        if (results.size() == 0) {
        	qr.update(conn, "INSERT INTO artist (NAME,LASTNAME,NICKNAME,BIRTHDAY,FIRST_TRACK_DATE,NATIONALITY,PHOTO_ARTIST) VALUES"
        		+ "('Ariana','Grande-Butera','Ariana Grande','1993-06-26','2013-03-28','USA',NULL),"
        		+ "('Edward Christopher','Sheeran','Ed Sheeran','1991-02-17','2010-04-22','UK',NULL),"
        		+ "('Curtis James','Jackson','50 Cent','1975-07-06','1999-01-01','USA',NULL),"
        		+ "('Marshall Bruce','Mathers','Eminem','1972-10-17','2000-10-15','USA',NULL),"
        		+ "('Athanasios ','Rouvas','Sakis Rouvas','1972-01-05','1992-10-08','Greece',NULL),"
        		+ "('Ioannis','Kakosaios','Giannis Ploutarxos','1970-12-18','1998-03-07','Greece',NULL),"
        		+ "('Daniel','Rocco','Des Rocs','1998-05-23','2018-03-15','USA',NULL),"
        		+ "('George','Barnett','These New Puritans','1988-08-09','2010-09-06','UK',NULL),"
        		+ "('Peter ','Anastasopoulos','Mad Clip','1987-05-25','2013-05-20','USA',NULL),"
        		+ "('Karla Camila','Cabello Estrabao','Camila Cabello','1997-03-03','2012-06-09','Cuba',NULL)"
        		);  
        }	        
    }

    @DisplayName("Check artist table size")
    @Test
    public void checkArtistTableSize() throws SQLException {
    	// Should be able to select from a table
        results = qr.query(conn, "SELECT * FROM artist",
                new ColumnListHandler<String>());
        Assertions.assertEquals(10, results.size());
    }
    
    @DisplayName("Check artists names")
    @Test
    public void checkArtistsNames() throws SQLException {
    	
        results = qr.query(conn, "SELECT NAME FROM artist", 
        new ColumnListHandler<String>());
        
        Assertions.assertEquals("Ariana", results.get(0));        
        Assertions.assertEquals("Edward Christopher", results.get(1));
        Assertions.assertEquals("Karla Camila", results.get(9));        
    }
    
    @DisplayName("Check adding data into register table")
    @Test
    public void checkAddingDataIntoRegisterTable() throws SQLException {
    	
    	// given
    	String Email = "info@dipae.gr";
    	String Name = "John";
    	String Surname = "Doe";
    	String Username = "user1";
    	String Password = "password1";    	
    	
    	// when    	
    	addData(
    			Email,
    			Name, 
    			Surname, 
    			Username, 
    			Password);    	
    	
    	// then
    	results = qr.query(conn, "SELECT USERNAME FROM register WHERE USERNAME = 'user1'", 
        		new ColumnListHandler<String>());
    	Assertions.assertEquals("user1", results.get(0));
    }
    
    public void addData(
			String Email,
			String Name, 
			String Surname, 
			String Username, 
			String Password) {
		
		try {
		
		results = qr.query(conn, "SELECT * FROM register", new ColumnListHandler<String>());
		if (results.size() == 0) {
			String sql_query = "INSERT INTO register (EMAIL,NAME,SURNAME,USERNAME,PASSWORD) "
					+ "VALUES (?,?,?,?,?)";
		
			PreparedStatement preparedStmt = conn.prepareStatement(sql_query);
		
			preparedStmt.setString(1, Email);
			preparedStmt.setString(2, Name);
			preparedStmt.setString(3, Surname);
			preparedStmt.setString(4, Username);
			preparedStmt.setString(5, Password);
		
			preparedStmt.execute();
		}
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
			
		}
			
	}

}