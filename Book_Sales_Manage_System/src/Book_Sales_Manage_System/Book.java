/*
 * Author：Jarvis
 * Date：2020.11.1
 * Class：17电子信息工程1 
 * Num：Xb17610107
*/

/*
 * Description：实体类Book
 * 
*/
package Book_Sales_Manage_System;

public class Book {
	private String num;// 书号
	private String name;// 书名
	private String author;// 作者
	private String date;// 出版日期
	private float price;// 价格
	private int amount;// 库存数量

	// Book()的构造函数
	public Book() {
	}

	// Book()的有参构造函数
	public Book(String num, String name, String author, String date, float price, int amount) {
		super();
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
