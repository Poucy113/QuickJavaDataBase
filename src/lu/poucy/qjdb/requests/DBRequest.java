package lu.poucy.qjdb.requests;

import lu.poucy.qjdb.DataBase;
import lu.poucy.qjdb.results.DBRequestResult;

public abstract class DBRequest {

	private DBRequestType type;
	
	public DBRequest(DBRequestType type) {
		this.type = type;
	}

	public abstract DBRequestResult execute(DataBase db);
	
	public DBRequestType getType() {return type;}

}
