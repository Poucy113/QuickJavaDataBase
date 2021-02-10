package lu.poucy.qjdb.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lu.poucy.qjdb.Condition;
import lu.poucy.qjdb.DataBase;
import lu.poucy.qjdb.results.DBDeleteRequestResult;
import lu.poucy.qjdb.results.DBRequestResult;

public class DBDeleteRequest extends DBRequest {

	private Condition[] conditions;
	
	public DBDeleteRequest(Condition[] conditions) {
		super(DBRequestType.DELETE);
		this.conditions = conditions;
	}

	@Override
	public DBRequestResult execute(DataBase db) {
		List<HashMap<String, Object>> removed = new ArrayList<>();
		for(HashMap<String, Object> o : db.getLines())
			if(Condition.conditions(conditions, o))
				removed.add(o);
		db.getLines().removeAll(removed);
		return new DBDeleteRequestResult(removed);
	}

}
