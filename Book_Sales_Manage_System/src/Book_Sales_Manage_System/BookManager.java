/*
 * Author��Jarvis
 * Date��2020.11.1
 * Class��17������Ϣ����1 
 * Num��Xb17610107
*/
/*
 * Description���鱾��������
 * 
*/
package Book_Sales_Manage_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
	List<Book> books = new ArrayList<Book>();// �½�Book���Arraylist�����ֽ�books��������������ݿⶼ����������
	List<Customer> trolly = new ArrayList<Customer>();// �½�Customer���Arraylist�����ֽ�trolly�����������û�������鱾��Ϣ
	Customer good = new Customer();// �½�Customer���good��������Ϊ�м���������û���������
	Connection con = null;
	Statement stat = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;// �����ַ������Ӻ����ѣ�����Ԥ����ķ���

	public void system_info() {// ϵͳ��Ϣ�������
		System.out.println("��ӭʹ������ͼ����� Rev1.0");
		System.out.println("Powered by Jarvis,2020.11.01");
		for (int i = 0; i < 20; i++)
			System.out.print("-");
		System.out.print("\n");
	}

	public void search() {// ͼ���ѯ����
		int i = 0;
		int flag = 0;// flagΪ��������ͬͼ��ı�־���������ʼ��=0��˵��û����ͬ��ͼ�飻���=1��˵��������ͬ��ͼ��
		String num;
		System.out.print("��������Ҫ��ѯ��ͼ����ţ�");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		num = input.next();
		for (i = 0; i < books.size(); i++) {// ͨ�����������Ƿ���ڷ���Ҫ���ͼ��
			Book book = (Book) books.get(i);
			if (book.getNum().equals(num)) {// ����ҵ���������ӡ�����˳���ǰ��������flag=1
				System.out.println("[���]\t[���]\t\t[����]\t\t\t [����]\t\t[��������]\t[�۸�]\t[���]");
				System.out.println("  " + 1 + "\t " + book.getNum() + "\t     " + book.getName() + "\t      " + book.getAuthor() + "\t" + book.getDate() + "\t " + book.getPrice() + "\t " + book.getAmount());
				flag = 1;
				break;
			} else
				;
		}
		if (flag == 0) {// ͨ�������Ҳ����飬�������
			System.out.println("û���Ȿ�飡������");
		}
	}

	public void add() {// ����ͼ�鹦��
		show();// ����ͼ��֮ǰ����ӡ���
		String num;
		String name;
		String author;
		String date;
		float price;
		int amount;
		int i = 0;
		int flag = 1;// flagΪ��������ͬͼ��ı�־���������ʼ��=1��˵��û����ͬ��ͼ�飻���=0��˵��������ͬ��ͼ��
		System.out.print("��������Ҫ��ӵ�������:");
		Scanner input = new Scanner(System.in);
		num = input.next();
		while (flag == 1) {
			for (i = 0; i < books.size(); i++) {
				Book book1 = (Book) books.get(i);
				if (book1.getNum().equals(num)) {// �ҵ���ͬ������������棬��ʾ�������룬ͬʱ��������ѭ�����¿�ʼ����
					System.out.print("���棡����ͼ���Ѿ����ڣ�������������:");
					num = input.next();
					break;// �ҵ���ͬ��ͼ�������˳���ǰ��forѭ��
				} else// û���ҵ���ͬ�ļ���ִ�е�ǰѭ��
					;
			}
			if (i == books.size()) {// ��ȫִ����һ�α���Ҳû���ҵ���ͬ��ͼ��Ļ���˵��������Ա����
				flag = 0;// ֻ�����벻���ڵ�ͼ�飬���Ƴ�whileѭ��
			}
		}
		System.out.print("����:");
		name = input.next();
		System.out.print("����:");
		author = input.next();
		System.out.print("��������:");
		date = input.next();
		System.out.print("�۸�:");
		price = input.nextFloat();
		System.out.print("���:");
		amount = input.nextInt();
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement("insert into book values(?,?,?,?,?,?)");
			pstmt.setString(1, num);
			pstmt.setString(2, name);
			pstmt.setString(3, author);
			pstmt.setString(4, date);
			pstmt.setFloat(5, price);
			pstmt.setInt(6, amount);
			pstmt.execute();// ���µ�ͼ����뵽�������ݿ���
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			JDBCUtils.release(pstmt, con);
		}
		System.out.print("���ͼ��ɹ�����");
		show();// ��ӳɹ�֮�������һ��
	}

	public void in() {// ��⺯��
		show();// ���֮ǰ����ӡ���
		String num;// ���
		int amount = 0;
		int i = 0;
		int flag = 1;// flagΪ��������ͬͼ��ı�־���������ʼ��=1��˵��û����ͬ��ͼ�飻�����=0��˵��������ͬ��ͼ��
		Book book = new Book();// �½�����book
		System.out.print("��������Ҫ�������:");
		Scanner input = new Scanner(System.in);
		num = input.next();
		while (flag == 1) {
			for (i = 0; i < books.size(); i++) {
				book = (Book) books.get(i);
				if (book.getNum().equals(num)) {
					flag = 0;// һ���ҵ���ͬ��ͼ�飬����־����=0�������˳���ǰ����
					break;
				} else
					;
			}
			if (flag == 1) {// ��ȫִ����һ�α���Ҳû���ҵ���ͬ��ͼ��Ļ���˵�������벻�˿�
				System.out.print("���棡����ͼ�鲻���ڣ�������������:");
				num = input.next();
			} else
				;
		}
		System.out.print("��������Ҫ��������:");
		amount = input.nextInt();
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement("update book set amount=? where num=?");
			pstmt.setInt(1, book.getAmount() + amount);// �����������Ŷ�Ӧ�Ŀ��
			pstmt.setString(2, num);
			pstmt.execute();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			JDBCUtils.release(pstmt, con);
		}
		System.out.println("���ɹ�������");
		show();// ���ɹ�֮�������һ��
	}

	public void out() {// �������
		show();// ����֮ǰ����ӡ���
		String num;
		int amount = 0;
		int i = 0;
		int flag = 1;// flagΪ��������ͬͼ��ı�־���������ʼ��=1��˵��û����ͬ��ͼ�飬���=0��˵��������ͬ��ͼ��
		Book book = new Book();
		System.out.print("��������Ҫ��������:");
		Scanner input = new Scanner(System.in);
		num = input.next();
		while (flag == 1) {
			for (i = 0; i < books.size(); i++) {
				book = (Book) books.get(i);
				if (book.getNum().equals(num)) {
					flag = 0;// һ���ҵ���ͬ��ͼ�飬����־����=0�������˳���ǰ����
					break;
				} else
					;
			}
			if (flag == 1) {// ��ȫִ����һ�α���Ҳû���ҵ���ͬ��ͼ��Ļ���˵�����鲻���Գ��⣬���½�������ִ���µı���
				System.out.print("���棡����ͼ�鲻���ڣ�������������:");
				num = input.next();
			} else
				;
		}
		System.out.print("��������Ҫ���������:");
		amount = input.nextInt();
		while ((book.getAmount() - amount) < 0) {// �ж������Ƿ񳬿��
			System.out.print("��治�㣬����������:");
			amount = input.nextInt();// ֻ�����벻�������˳�ѭ��
		}
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement("update book set amount=? where num=?");
			pstmt.setInt(1, book.getAmount() - amount);// ���¿��
			pstmt.setString(2, num);
			pstmt.execute();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			JDBCUtils.release(pstmt, con);
		}
		System.out.println("����ɹ�������");
		show();// ����ɹ�����������
	}

	public void delete() {// ɾ������
		show();// ɾ��֮ǰ����ӡ���
		String num;
		int i = 0;
		int flag = 1;// flagΪ��������ͬͼ��ı�־���������ʼ��=1��˵��û����ͬ��ͼ�飬���=0��˵��������ͬ��ͼ��
		Book book = new Book();
		System.out.print("��������Ҫɾ�������:");
		Scanner input = new Scanner(System.in);
		num = input.next();
		while (flag == 1) {
			for (i = 0; i < books.size(); i++) {
				book = (Book) books.get(i);
				if (book.getNum().equals(num)) {
					flag = 0;// һ���ҵ���ͬ��ͼ�飬����־����=0�������˳���ǰ����
					break;
				} else
					;
			}
			if (flag == 1) {// ��ȫִ����һ�α���Ҳû���ҵ���ͬ��ͼ��Ļ���˵�����鲻���ڣ���������ִ���µı���
				System.out.print("���棡����ͼ�鲻���ڣ�������������:");
				num = input.next();
			} else
				;
		}
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement("delete from book where num=?");
			pstmt.setString(1, num);
			pstmt.execute();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			JDBCUtils.release(pstmt, con);
		}
		System.out.println("ͼ��ɾ���ɹ�������");
		show();// ɾ���ɹ�����������
	}

	public void show() {// �鿴����
		int num = 1;
		books.clear();
		try {
			con = JDBCUtils.getConnection();
			stat = con.createStatement();
			rs = stat.executeQuery("select * from book");// �ӱ������ݿ��л�ȡͼ����Ϣ�����Arraylist���books��
			while (rs.next()) {
				Book book = new Book();
				book.setNum(rs.getString(1));
				book.setName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setDate(rs.getString(4));
				book.setPrice(rs.getFloat(5));
				book.setAmount(rs.getInt(6));
				books.add(book);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			JDBCUtils.release(rs, stat, con);
		}
		System.out.println("�ֿ��ܹ���" + books.size() + "����");
		System.out.println("[���]\t[���]\t\t[����]\t\t\t [����]\t\t[��������]\t[�۸�]\t[���]");
		for (int i = 0; i < books.size(); i++) {// ������
			Book book1 = (Book) books.get(i);
			System.out.println("  " + num + "\t " + book1.getNum() + "\t     " + book1.getName() + "\t      " + book1.getAuthor() + "\t" + book1.getDate() + "\t " + book1.getPrice() + "\t " + book1.getAmount());
			num++;
		}
	}

	// ���������㷨
	public void quick_sort(int[][] a, int left, int right) {// ��������Ƕ�ά���飬��ߵ��������ұߵ�����
		int l = left;// ���±�
		int r = right;// ���±�
		int pivot = a[0][(l + r) / 2];// �ҵ��м��ֵ
		int[][] temp = new int[2][1];// �м�Ľ�������
		// whileѭ����Ŀ���ǽ���pivotС�ķŵ��ұߣ���pivot��ķŵ����
		while (l < r) {
			while (a[0][l] > pivot) {// ��pivot�����һֱ�ң��ҵ�<=pivot��ֵ���˳�
				l++;
			}
			while (a[0][r] < pivot) {// ��pivot���ұ�һֱ�ң��ҵ�>=pivot��ֵ���˳�
				r--;
			}
			if (l >= r) {// ���l >= r������˵����߶���pivot���ұ߶���pivotС
				break;
			}
			temp[0][0] = a[0][l];
			temp[1][0] = a[1][l];// ��ֵ�±����һ�𽻻�
			a[0][l] = a[0][r];
			a[1][l] = a[1][r];// ��ֵ�±����һ�𽻻�
			a[0][r] = temp[0][0];
			a[1][r] = temp[1][0];// ��ֵ�±����һ�𽻻�
			if (a[0][l] == pivot) {// ������������a[0][l]==privot����Ҫ��r--,ǰ��
				r--;
			}
			if (a[0][r] == pivot) {// ������������a[0][l]==privot����Ҫ��l++,����
				l++;
			}
		}
		// ���l==r������ʹ��l++,r--,���򽫻����ջ���
		if (l == r) {
			l++;
			r--;
		}
		// ����ݹ�
		if (left < r) {
			quick_sort(a, left, r);
		}
		// ���ҵݹ�
		if (right > l) {
			quick_sort(a, l, right);
		}
	}

	public void rank() {// ������а���
		int i = 0;
		int num = 1;
		books.clear();
		try {
			con = JDBCUtils.getConnection();
			stat = con.createStatement();
			rs = stat.executeQuery("select * from book");
			while (rs.next()) {
				Book book = new Book();
				book.setNum(rs.getString(1));
				book.setName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setDate(rs.getString(4));
				book.setPrice(rs.getFloat(5));
				book.setAmount(rs.getInt(6));
				books.add(book);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			JDBCUtils.release(rs, stat, con);
		}
		int[][] a = new int[2][books.size()];// �ö�ά����Կ���������ԭ������Ϊ����ֵ�����ͬʱ�����±�Ҳһ�������������±������
		for (i = 0; i < books.size(); i++) {
			Book book = (Book) books.get(i);
			a[0][i] = book.getAmount();
			a[1][i] = i;
		}

		quick_sort(a, 0, books.size() - 1);// ִ�п�������

		System.out.println("�ֿ��ܹ���" + books.size() + "����");
		System.out.println("[���]\t[���]\t\t[����]\t\t\t [����]\t\t[��������]\t[�۸�]\t[���]");
		for (i = 0; i < books.size(); i++) {
			Book book1 = (Book) books.get(a[1][i]);// ����֮ǰ����õ��±�������
			System.out.println("  " + num + "\t " + book1.getNum() + "\t     " + book1.getName() + "\t      " + book1.getAuthor() + "\t" + book1.getDate() + "\t " + book1.getPrice() + "\t " + book1.getAmount());
			num++;
		}
	}

	public void buy() {// ������
		show();// ����֮ǰ��������
		String num;
		int amount = 0;
		int i = 0;
		int flag = 1;// flagΪ��������ͬͼ��ı�־���������ʼ��=1��˵��û����ͬ��ͼ�飬���=0��˵��������ͬ��ͼ��
		char confirm1 = 0;// ��������ѭ��ȷ�ϱ���
		int cycle = 1;// ��������ͼ��ѭ������
		float money = 0;// һ��ͼ��Ľ���
		float total_money = 0;// ����ͼ��Ľ���
		while (cycle == 1) {
			Book book = new Book();
			good = new Customer();
			System.out.print("��������Ҫ��������:");
			Scanner input = new Scanner(System.in);
			num = input.next();
			while (flag == 1) {
				for (i = 0; i < books.size(); i++) {
					book = (Book) books.get(i);
					if (book.getNum().equals(num)) {
						flag = 0;// һ���ҵ���ͬ��ͼ�飬����־����=1����ͼ����Ϣ��ֵ�����ﳵ�м�����������˳���ǰ����
						good.setNum(num);
						good.setName(book.getName());
						good.setAuthor(book.getAuthor());
						good.setDate(book.getDate());
						good.setPrice(book.getPrice());
						break;
					} else
						;
				}
				if (flag == 1) {// ��ȫִ����һ�α���Ҳû���ҵ���ͬ��ͼ��Ļ���˵�����鲻���ڣ���������ִ���µı���
					System.out.print("���棡����ͼ�鲻���ڣ�������������:");
					num = input.next();
				} else
					;
			}
			System.out.print("��������Ҫ���������:");
			amount = input.nextInt();
			while ((book.getAmount() - amount) < 0) {// �ж������Ƿ񳬿��
				System.out.print("��治�㣬����������:");
				amount = input.nextInt();// ֻ�����벻�������˳�ѭ��
			}
			good.setAmount(amount);
			money = amount * book.getPrice();// �����Ǯ
			good.setMoney(money);// ����Ǯ��¼���м������
			trolly.add(good);// ������ӵ����ﳵ��
			try {
				con = JDBCUtils.getConnection();
				pstmt = con.prepareStatement("update book set amount=? where num=?");
				pstmt.setInt(1, book.getAmount() - amount);
				pstmt.setString(2, num);
				pstmt.execute();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			} finally {
				JDBCUtils.release(pstmt, con);
			}
			System.out.println("����ͼ��ɹ�������");
			System.out.print("���ʻ���Ҫ����ͼ��ô����y or press any key��:");// ����y�����������������˳���ǰ�ͻ�������
			@SuppressWarnings("resource")
			Scanner input1 = new Scanner(System.in);
			confirm1 = input1.next().charAt(0);
			if (confirm1 != 'y') {
				cycle = 0;
			} else {// ѡ���������ͼ��󣬱��뽫֮ǰѭ���ı����ͼ�¼��ͬ�鱾�ı�����λ��ԭֵ������ŻṤ������
				confirm1 = 0;
				flag = 1;
			}
		}
		System.out.println("\n-----------�����ǹ����嵥-----------");
		System.out.println("[���]\t[���]\t\t[����]\t\t\t [����]\t\t[��������]\t[�۸�]\t[����]\t[���]");
		for (i = 0; i < trolly.size(); i++) {
			Customer custom = (Customer) trolly.get(i);// ����û��Ĺ����嵥
			System.out.println("  " + (i + 1) + "\t " + custom.getNum() + "\t     " + custom.getName() + "\t      " + custom.getAuthor() + "\t" + custom.getDate() + "\t " + custom.getPrice() + "\t " + custom.getAmount() + "\t " + custom.getMoney());
			total_money = total_money + custom.getMoney();
		}
		System.out.println("\n��֧��" + "��" + total_money + "��" + "Ԫ");// ����ܼ�Ǯ
		System.out.println("---------------------------\n");
	}

	public void admin() {// ����Ա����
		int choose = 0;
		int cycle = 1;
		String Username;
		String Password;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print(">>Administrator Username:");// �����û���������
		Username = input.next();
		System.out.print(">>Administrator Password:");
		Password = input.next();
		while ((Username.equals("admin") != true) && (Password.equals("admin") != true)) {// �û���������=admin���ܽ���
			System.out.println("�û�����������������������룡����");
			System.out.print(">>Administrator Username:");
			Username = input.next();
			System.out.print(">>Administrator Password:");
			Password = input.next();
		}
		System.out.println("��������¼�ɹ�������");
		while (cycle == 1) {
			System.out.print(">>��ѡ���ܣ�1.����    2.���    3.����б�    4.����ͼ��    5.����    6.ɾ��    7.������а�    8.�˳��˻���:");// ����ѡ����棬8�ֹ���
			choose = input.nextInt();
			while ((choose != 1) && (choose != 2) && (choose != 3) && (choose != 4) && (choose != 5) && (choose != 6) && (choose != 7) && (choose != 8)) {
				System.out.println("������󣡣������������룡����");
				choose = input.nextInt();
			}
			switch (choose) {
			case 1:
				System.out.println("��������>���⹦������");
				out();// ���⹦��
				break;
			case 2:
				System.out.println("��������>��⹦������");
				in();// ��⹦��
				break;
			case 3:
				System.out.println("��������>����б�������");
				show();// �鿴����
				break;
			case 4:
				System.out.println("��������>����ͼ�鹦������");
				search();// ����ͼ�鹦��
				break;
			case 5:
				System.out.println("��������>������������");
				add();// ��������
				break;
			case 6:
				System.out.println("��������>ɾ����������");
				delete();// ɾ������
				break;
			case 7:
				System.out.println("��������>������а�������");
				rank();// ������а���
				break;
			case 8:
				cycle = 0;
				System.out.println("��������>�˳��˻��ɹ�");
				break;
			default:
				break;
			}
		}

	}

	public void customer() {// ��ͨ�û�����
		int choose = 0;
		int cycle = 1;
		String Username;
		String Password;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print(">>Username:");// �����û���������
		Username = input.next();
		System.out.print(">>Password:");
		Password = input.next();
		while (Username.equals(Password) != true) {
			System.out.println("�û�����������������������룡����");// �û�����������ͬ���ܽ���ϵͳ
			System.out.println(">>Username:");
			Username = input.next();
			System.out.println(">>Password:");
			Password = input.next();
		}
		System.out.println("��������¼�ɹ�������");
		while (cycle == 1) {
			System.out.print(">>��ѡ���ܣ�1.����б�    2.����ͼ��    3.����ͼ��    4.������а�    5.�˳��˻���:");// ����ѡ����棬5�ֹ���
			choose = input.nextInt();
			while ((choose != 1) && (choose != 2) && (choose != 3) && (choose != 4) && (choose != 5)) {
				System.out.println("������󣡣������������룡����");
				choose = input.nextInt();
			}
			switch (choose) {
			case 1:
				System.out.println("��������>����б�������");
				show();// �鿴����
				break;
			case 2:
				System.out.println("��������>����ͼ�鹦������");
				search();// ����ͼ�鹦��
				break;
			case 3:
				System.out.println("��������>����ͼ�鹦������");
				buy();// ������а���
				break;
			case 4:
				System.out.println("��������>������а�������");
				rank();// ������а���
				break;
			case 5:
				cycle = 0;
				trolly.clear();
				System.out.println("��������>�˳��˻��ɹ�");
				break;
			default:
				break;
			}
		}

	}

	public int login_initial() {// ��½��ʼ��
		int choose = 0;
		System.out.print(">>��ѡ��ģʽ��1.������Ա    2.�˿�    3.�˳�ϵͳ��:");// һ��ѡ����棬3�ֹ���
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		choose = input.nextInt();
		while ((choose != 1) && (choose != 2) && (choose != 3)) {
			System.out.println("������󣡣������������룡��:");
			choose = input.nextInt();
		}
		return choose;
	}

	public void menu() {
		int option = 0;// option���������û��ĵ�½ģʽѡ��
		int cycle = 1;// ��½����ģʽѭ������
		system_info();// ϵͳ��Ϣ��ʼ��
		while (cycle == 1) {
			option = login_initial();// option���������û��ĵ�½ģʽѡ��
			if (option == 1) {
				admin();// ִ�й���Աģʽ
			} else if (option == 2) {
				customer();// ִ�й˿�ģʽ
			} else {
				cycle = 0;
				System.out.println("�˳�ϵͳ�ɹ�������");
			}
		}
	}
}
