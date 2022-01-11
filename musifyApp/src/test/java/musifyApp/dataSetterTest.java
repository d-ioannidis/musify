
import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class dataSetterTest {
	

	@Test
	void test() throws SQLException {
		PreparedStatement preparedStmt = null;
		preparedStmt.setString(1, "mpamphs@gmail.com");
		preparedStmt.setString(2, "mpamphs");
		preparedStmt.setString(3, "papadopoylos");
		preparedStmt.setString(4, "mpamphs");
		preparedStmt.setString(5, "mpamphs");
		
		
			Assertions.assertTrue(true);
		
	}

}
