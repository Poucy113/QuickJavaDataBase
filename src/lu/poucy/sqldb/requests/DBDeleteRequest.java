package lu.poucy.sqldb.requests;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lu.poucy.sqldb.Condition;
import lu.poucy.sqldb.DataBase;
import lu.poucy.sqldb.results.DBDeleteRequestResult;
import lu.poucy.sqldb.results.DBRequestResult;

public class DBDeleteRequest extends DBRequest {

	private Condition[] conditions;
	
	public DBDeleteRequest(Condition[] conditions) {
		super(DBRequestType.DELETE);
		this.conditions = conditions;
	}

	@Override
	public DBRequestResult execute(DataBase sqlDataBase) throws SQLException {
		List<HashMap<String, Object>> removed = new ArrayList<>();
		for(HashMap<String, Object> o : sqlDataBase.getLines())
			if(Condition.conditions(conditions, o))
				removed.add(o);
		sqlDataBase.getLines().removeAll(removed);
		return new DBDeleteRequestResult(removed);
	}

}
