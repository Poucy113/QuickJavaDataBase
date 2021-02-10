package lu.poucy.qjdb.results;

import java.util.HashMap;
import java.util.List;

public class DBSelectRequestResult implements DBRequestResult {

	List<HashMap<String, Object>> ret;
	
	public DBSelectRequestResult(List<HashMap<String, Object>> r) {
		this.ret = r;
	}
	
	public List<HashMap<String, Object>> getResult() {return ret;}

}
