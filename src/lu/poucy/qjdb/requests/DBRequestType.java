package lu.poucy.qjdb.requests;

public enum DBRequestType {

	UNKNOWN("UNKNOWN"),
	SELECT("SELECT"),
	UPDATE("UPDATE"),
	DELETE("DELETE"),
	INSERT("INSERT");
	
	private String s;
	
	private DBRequestType(String s) {
		this.s = s;
	}
	
	public String getTxt() {return s;}
	@Override
	public String toString() {
		return s;
	}

	public static DBRequestType fromString(String string) {
		for(DBRequestType t : DBRequestType.values())
			if(t.getTxt().equals(string))
				return t;
		return DBRequestType.UNKNOWN;
	}
	
}
