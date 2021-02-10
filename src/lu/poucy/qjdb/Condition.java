package lu.poucy.qjdb;

import java.util.HashMap;

public class Condition {

	public static final int LOWER = -2;
	public static final int LOWER_EQUALS = -1;
	public static final int EQUALS = 0;
	public static final int HIGHER_EQUALS = 1;
	public static final int HIGHER = 2;
	
	private String col;
	private int con;
	private Object t;
	
	public Condition(String column, int cond, Object obj) {
		this.col = column;
		this.con = cond;
		this.t = obj;
	}
	
	public boolean good(HashMap<String, Object> h) {
		if(h.get(col) instanceof Number &&
				t instanceof Number) {
			if(con == LOWER)
				if((Integer) h.get(col) < (Integer) t)
					return true;
			if(con == LOWER_EQUALS)
				if((Integer) h.get(col) <= (Integer) t)
					return true;
			if(con == HIGHER_EQUALS)
				if((Integer) h.get(col) >= (Integer) t)
					return true;
			if(con == HIGHER)
				if((Integer) h.get(col) > (Integer) t)
					return true;
		}
		if(con == EQUALS)
			if(h.get(col).equals(t))
				return true;
		return false;
	}
	
	public String getCol() {return col;}
	public int getCon() {return con;}
	public Object getT() {return t;}
	
	public static boolean conditions(Condition[] conditions, HashMap<String, Object> h) {
		boolean ret = true;
		for(Condition c : conditions)
			if(!c.good(h))
				ret = false;
		return ret;
	}

}
