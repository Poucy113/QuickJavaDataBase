package lu.poucy.qjdb.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	public DBRequestResult execute(DataBase db) {
		List<HashMap<String, Object>> ml = new ArrayList<>(db.getLines());
		ml.add(to);
		return new DBInsertRequestResult(db.setLines(ml));
	}

}
