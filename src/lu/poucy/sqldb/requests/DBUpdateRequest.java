package lu.poucy.sqldb.requests;

import java.sql.SQLException;
import java.util.HashMap;

import lu.poucy.sqldb.Colum;
import lu.poucy.sqldb.Condition;
import lu.poucy.sqldb.DataBase;
import lu.poucy.sqldb.results.DBRequestResult;
import lu.poucy.sqldb.results.DBUpdateRequestResult;

public class DBUpdateRequest extends DBRequest {

	private Colum[] colums;
	private Condition[] conditions;
	private Object[] replace;
	
	public DBUpdateRequest(Colum[] colums, Condition[] conditions, Object[] replace) {
		super(DBRequestType.UPDATE);
		this.colums = colums;
		this.conditions = conditions;
		this.replace = replace;
	}

	@Override
	public DBRequestResult execute(DataBase sqlDataBase) throws SQLException {
		for(HashMap<String, Object> so : sqlDataBase.getLines())
			if(Condition.conditions(conditions, so))
				for(int i = 0; i < colums.length; i++)
					so.replace(colums[i].getName(), replace[i]);
		return new DBUpdateRequestResult();
	}

}
