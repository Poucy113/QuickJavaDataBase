package lu.poucy.qjdb;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

import lu.poucy.qjdb.requests.DBRequest;
import lu.poucy.qjdb.results.DBRequestResult;

public class DataBase implements Closeable {
	
	private boolean isClosed = false;
	
	private String name = "Poucy-DataBase";
	private File file;
	
	private List<String> column = new ArrayList<>();
	private List<HashMap<String, Object>> lines = new ArrayList<>();
	
	public DataBase(File file) throws IOException {
		this.file = file;
		realLoad(file);
	}
	public DataBase(String name,
			List<String> column,
			List<HashMap<String, Object>> lines,
			File file) {
		this.column = column;
		this.lines = lines;
		this.file = file;
		this.name = name;
	}
	
	public DBRequestResult request(DBRequest s) {return s.execute(this);}
	
	private void realSave(File file) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		
		StringBuilder sb = new StringBuilder();
		for(String c : column)
			sb.append(c+";");
		obj.put("column", sb.deleteCharAt(sb.length()-1).toString());
		
		JSONArray array = new JSONArray();
		for(HashMap<String, Object> so : lines) {
			JSONObject o = new JSONObject();
			for(String s : so.keySet())
				o.put(s, so.get(s));
			array.put(o);
		}
		obj.put("content", array);
		
		Files.write(Paths.get(file.getPath()),obj.toString().getBytes());
	}
	private void realLoad(File file) throws IOException {
		Files.readAllBytes(Paths.get(file.getPath()));
		JSONObject obj = new JSONObject(new String(Files.readAllBytes(Paths.get(file.getPath()))));
		this.name = obj.getString("name");
		for(String c : obj.getString("column").split(";"))
			column.add(c);
		JSONArray array = obj.getJSONArray("content");
		for (int i = 0; i < array.length(); i++) {
			JSONObject o = array.getJSONObject(i);
			HashMap<String, Object> l = new HashMap<>();
			for(String s : o.keySet())
				l.put(s, o.get(s));
			lines.add(l);
		}
		isClosed = false;
	}
	
	public Thread save(Consumer<Exception> ex) {
		Thread th = new Thread(() -> {
			try {
				realSave(file);
			} catch (Exception e) {
				ex.accept(e);;
			}
		});
		th.start();
		return th;
	}
	public Thread load(Consumer<Exception> ex) {
		Thread th = new Thread(() -> {
			try {
				realLoad(file);
			} catch (Exception e) {
				ex.accept(e);;
			}
		});
		th.start();
		return th;
	}
	public Thread save() {return save((e) -> e.printStackTrace());}
	public Thread load() {return load((e) -> e.printStackTrace());}

	public List<String> getColumn() {return column;}
	public List<HashMap<String, Object>> getLines() {return lines;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public List<HashMap<String, Object>> setLines(List<HashMap<String, Object>> ml) {
		this.lines = ml;
		return this.getLines();
	}
	@Override
	public void close() throws IOException {
		column.clear();
		lines.clear();
		isClosed = true;
	}
	
	public boolean isClosed() {return isClosed;}
	
}
