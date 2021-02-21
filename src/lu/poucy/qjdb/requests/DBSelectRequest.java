package lu.poucy.qjdb.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lu.poucy.qjdb.Colum;
import lu.poucy.qjdb.Condition;
import lu.poucy.qjdb.DataBase;
import lu.poucy.qjdb.results.DBRequestResult;
import lu.poucy.qjdb.results.DBSelectRequestResult;

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
	public DBRequestResult execute(DataBase db) {
		DBSelectRequestResult ret = null;
		List<HashMap<String, Object>> r = new ArrayList<>();
		for(HashMap<String, Object> h : db.getLines())
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
