import static org.junit.jupiter.api.Assertions.*;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class artistSetterTest {

	@Test
	void test()  throws SQLException{
		PreparedStatement preparedStmt = null;

		preparedStmt.setString(1, "mpamphs");
		preparedStmt.setString(2, "papadopoylos");
		preparedStmt.setString(3, "mpamphs");
		preparedStmt.setString(4, "1/1/2022");
		preparedStmt.setString(5, "1/1/2022");
		preparedStmt.setString(6, "Greek");
		
		if(preparedStmt.execute()) {
			Assertions.assertTrue(true);
		}
		else {
			Assertions.assertFalse(false);
		}
	}

}


