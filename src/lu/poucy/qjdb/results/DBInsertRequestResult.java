package lu.poucy.qjdb.results;

public class DBInsertRequestResult implements DBRequestResult {

	private boolean add;
	
	public DBInsertRequestResult(boolean add) {
		this.add = add;
	}
	
	public boolean isAdded() {return add;}
	
}
