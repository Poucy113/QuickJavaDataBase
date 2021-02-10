package lu.poucy.sqldb.requests;

import java.sql.SQLException;

import lu.poucy.sqldb.DataBase;
import lu.poucy.sqldb.results.DBRequestResult;

public abstract class DBRequest {

	private DBRequestType type;
	
	public DBRequest(DBRequestType type) {
		this.type = type;
	}

	public abstract DBRequestResult execute(DataBase sqlDataBase) throws SQLException;
	
	public DBRequestType getType() {return type;}

}
