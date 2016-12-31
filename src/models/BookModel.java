package models;

import java.util.ArrayList;

public class BookModel {
	
	private long id;
	private String name;
	private ArrayList<String> authors;
	private String language;
	private String toc;
	private ArrayList<String> keywords;
	private enum formats {PDF, DOC, FB2}
	
	public BookModel() {
		
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getAuthors() {
		return authors;
	}
	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getToc() {
		return toc;
	}
	public void setToc(String toc) {
		this.toc = toc;
	}
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	};
	
	/*public void setFormats(String fmt)
	{
		
		if(fmt == "pdf")
			
	}*/

}
