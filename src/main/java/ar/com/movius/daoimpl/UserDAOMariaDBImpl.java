package ar.com.movius.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import ar.com.movius.dao.IUserDAO;
import ar.com.movius.db.AdmConnection;

public class UserDAOMariaDBImpl implements IUserDAO {

	@Override
	public boolean login(String user, String passwd) throws Exception {
		Connection cnn = AdmConnection.getConnection();
		String query = "SELECT * FROM users WHERE username=? AND password=?";
		PreparedStatement statement = cnn.prepareStatement(query);
		statement.setString(1, user);
		statement.setString(2, passwd);
		boolean result = statement.execute();
		
		this.closeCnn(cnn);

		if(result) {
			ResultSet res = statement.getResultSet();
			int i = 0;
			while(res.next()) {
			    i++;
			}
			if(i > 0) {
				return true;
			}
		}
		return false;
	}
	
	private void closeCnn(Connection cnn) throws Exception {
		cnn.close();
	}

}
