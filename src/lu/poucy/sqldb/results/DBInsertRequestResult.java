package lu.poucy.sqldb.results;

public class DBInsertRequestResult implements DBRequestResult {

	private boolean add;
	
	public DBInsertRequestResult(boolean add) {
		this.add = add;
	}
	
	public boolean isAdded() {return add;}
	
}
