/*
 * Author��Jarvis
 * Date��2020.11.1
 * Class��17������Ϣ����1 
 * Num��Xb17610107
*/

/*
 * Description��ʵ����Book
 * 
*/
package Book_Sales_Manage_System;

public class Book {
	private String num;// ���
	private String name;// ����
	private String author;// ����
	private String date;// ��������
	private float price;// �۸�
	private int amount;// �������

	// Book()�Ĺ��캯��
	public Book() {
	}

	// Book()���вι��캯��
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
