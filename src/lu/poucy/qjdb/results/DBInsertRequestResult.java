package lu.poucy.qjdb.results;

import java.util.HashMap;
import java.util.List;

public class DBInsertRequestResult implements DBRequestResult {

	private List<HashMap<String, Object>> add;
	
	public DBInsertRequestResult(List<HashMap<String, Object>> list) {
		this.add = list;
	}
	
	public List<HashMap<String, Object>> getLines() {return add;}
	
}
