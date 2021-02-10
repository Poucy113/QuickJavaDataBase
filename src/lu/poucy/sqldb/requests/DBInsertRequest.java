package lu.poucy.sqldb.requests;

import java.sql.SQLException;
import java.util.HashMap;

import lu.poucy.sqldb.DataBase;
import lu.poucy.sqldb.results.DBRequestResult;
import lu.poucy.sqldb.results.DBInsertRequestResult;

public class DBInsertRequest extends DBRequest {

	private HashMap<String, Object> to;
	
	public DBInsertRequest(HashMap<String, Object> to) {
		super(DBRequestType.INSERT);
		this.to = to;
	}

	@Override
	public DBRequestResult execute(DataBase sqlDataBase) throws SQLException {
		return new DBInsertRequestResult(sqlDataBase.getLines().add(to));
	}

}
