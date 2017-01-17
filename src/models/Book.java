package models;

public class Book extends AbstractModel{
	private String bookid;
	private String booktitle;
	private String booklang;
	private String synopsis;
	private String toc;
	private String keywords;
	private String format;
	private boolean incatalog;
	private int orders_count;
	private float price;


	public Book(String booktitle, float price, String format)
	{
		this.booktitle = booktitle;
		this.price = price;
		this.format=format;
	}
	public Book(String bookid, String booktitle, String booklang, String synopsis, String toc, String keywords,
			String format, boolean incatalog, int orders_count, float price) {
		super();
		this.bookid = bookid;
		this.booktitle = booktitle;
		this.booklang = booklang;
		this.synopsis = synopsis;
		this.toc = toc;
		this.keywords = keywords;
		this.format = format;
		this.incatalog = incatalog;
		this.orders_count = orders_count;
		this.price = price;
	}

	
	
	/**Override equals to be able to compare between two books (mainly in contains for arraylists)*/
	@Override
	public boolean equals(Object object)
	{
		Book b = (Book) object;
		if (b.getBooktitle().equals(this.getBooktitle()))
			return true;
		return false;
	}


	/**Getters and Setters*/
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getBooklang() {
		return booklang;
	}
	public void setBooklang(String booklang) {
		this.booklang = booklang;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getToc() {
		return toc;
	}
	public void setToc(String toc) {
		this.toc = toc;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public boolean isIncatalog() {
		return incatalog;
	}
	public void setIncatalog(boolean incatalog) {
		this.incatalog = incatalog;
	}
	public int getOrders_count() {
		return orders_count;
	}
	public void setOrders_count(int orders_count) {
		this.orders_count = orders_count;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	/**End getters and setters*/
}
