/*
 * Author��Jarvis
 * Date��2020.11.1
 * Class��17������Ϣ����1 
 * Num��Xb17610107
*/
/*
 * Description�����ﳵ��
 * 
*/
package Book_Sales_Manage_System;

public class Customer {
	private String num;// ���
	private String name;// ����
	private String author;// ����
	private String date;// ��������
	private float price;// �۸�
	private float money;// ÿ����Ľ���
	private int amount;// ÿ����Ĺ�������

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

	// ���ﳵ���вι��캯��
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

	// ���ﳵ���޲ι��캯��
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
