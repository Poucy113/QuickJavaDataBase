package lu.poucy.sqldb.requests;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lu.poucy.sqldb.Colum;
import lu.poucy.sqldb.Condition;
import lu.poucy.sqldb.DataBase;
import lu.poucy.sqldb.results.DBRequestResult;
import lu.poucy.sqldb.results.DBSelectRequestResult;

public class DBSelectRequest extends DBRequest {

	private Colum[] colums;
	private Condition[] conditions;
	
	public DBSelectRequest(Colum[] colums,
			Condition[] conditions) {
		super(DBRequestType.SELECT);
		
		this.colums = colums;
		this.conditions = conditions;
	}

	@Override
	public DBRequestResult execute(DataBase sqlDataBase) throws SQLException {
		DBRequestResult ret = null;
		List<HashMap<String, Object>> r = new ArrayList<>();
		for(HashMap<String, Object> h : sqlDataBase.getLines())
			if(Condition.conditions(conditions, h)) {
				HashMap<String, Object> o = new HashMap<>();
				for(String s : h.keySet())
					if(contains(colums, s) || colums.length == 0)
						o.put(s, h.get(s));
				r.add(o);
			}
		ret = new DBSelectRequestResult(r);
		return ret;
	}

	public boolean contains(Colum[] list, Object s) {
		for(Colum o : list)
			if(o.getName().equals(s))
				return true;
		return false;
	}

}
