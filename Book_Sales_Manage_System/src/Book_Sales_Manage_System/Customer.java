/*
 * Author：Jarvis
 * Date：2020.11.1
 * Class：17电子信息工程1 
 * Num：Xb17610107
*/
/*
 * Description：购物车类
 * 
*/
package Book_Sales_Manage_System;

public class Customer {
	private String num;// 书号
	private String name;// 书名
	private String author;// 作者
	private String date;// 出版日期
	private float price;// 价格
	private float money;// 每种书的结算
	private int amount;// 每种书的购买数量

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

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	// 购物车的有参构造函数
	public Customer(String num, String name, String author, String date, float price, float money, int amount) {
		super();
		this.num = num;
		this.name = name;
		this.author = author;
		this.date = date;
		this.price = price;
		this.money = money;
		this.amount = amount;
	}

	// 购物车的无参构造函数
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
