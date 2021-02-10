package lu.poucy.qjdb.requests;

import java.sql.SQLException;
import java.util.HashMap;

import lu.poucy.qjdb.DataBase;
import lu.poucy.qjdb.results.DBInsertRequestResult;
import lu.poucy.qjdb.results.DBRequestResult;

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
