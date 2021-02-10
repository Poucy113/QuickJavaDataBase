package lu.poucy.sqldb.results;

import java.util.HashMap;
import java.util.List;

public class DBDeleteRequestResult implements DBRequestResult {

	private List<HashMap<String, Object>> removed;
	
	public DBDeleteRequestResult(List<HashMap<String, Object>> removed) {
		this.removed = removed;
	}
	
	public List<HashMap<String, Object>> getRemoved() {return removed;}

}
