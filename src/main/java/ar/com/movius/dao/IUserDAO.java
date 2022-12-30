package ar.com.movius.dao;

public interface IUserDAO {

	public boolean login(String user, String passwd) throws Exception;
	
}
