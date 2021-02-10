package lu.poucy.qjdb.requests;

import java.sql.SQLException;
import java.util.HashMap;

import lu.poucy.qjdb.Colum;
import lu.poucy.qjdb.Condition;
import lu.poucy.qjdb.DataBase;
import lu.poucy.qjdb.results.DBRequestResult;
import lu.poucy.qjdb.results.DBUpdateRequestResult;

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
