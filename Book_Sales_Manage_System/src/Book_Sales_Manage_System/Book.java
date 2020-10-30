package Book_Sales_Manage_System;

public class Book {
	private int order;
	private String num;
	private String name;
	private String author;
	private String date;
	private float price;
	private int amount;

	public boolean equals(Object anObject) {// 由于使用contains方法会调用equals，所以重写equals方法
		Book book = (Book) anObject;
		return num.equals(book.getNum());
	}

	public Book() {
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Book(int order, String num, String name, String author, String date, float price, int amount) {
		super();
		this.order = order;
		this.num = num;
		this.name = name;
		this.author = author;
		this.date = date;
		this.price = price;
		this.amount = amount;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
