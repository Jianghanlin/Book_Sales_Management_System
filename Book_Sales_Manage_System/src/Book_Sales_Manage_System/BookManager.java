/*
 * Author：Jarvis
 * Date：2020.11.1
 * Class：17电子信息工程1 
 * Num：Xb17610107
*/
/*
 * Description：书本管理方法类
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
	List<Book> books = new ArrayList<Book>();// 新建Book类的Arraylist，名字叫books，用来保存从数据库都进来的数据
	List<Customer> trolly = new ArrayList<Customer>();// 新建Customer类的Arraylist，名字叫trolly，用来保存用户购买的书本信息
	Customer good = new Customer();// 新建Customer类的good，用来作为中间变量保存用户输入数据
	Connection con = null;
	Statement stat = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;// 由于字符串连接很困难，采用预处理的方法

	public void system_info() {// 系统信息输出函数
		System.out.println("欢迎使用新云图书书店 Rev1.0");
		System.out.println("Powered by Jarvis,2020.11.01");
		for (int i = 0; i < 20; i++)
			System.out.print("-");
		System.out.print("\n");
	}

	public void search() {// 图书查询函数
		int i = 0;
		int flag = 0;// flag为检索到相同图书的标志变量，如果始终=0，说明没有相同的图书；如果=1，说明存在相同的图书
		String num;
		System.out.print("请输入需要查询的图书书号：");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		num = input.next();
		for (i = 0; i < books.size(); i++) {// 通过遍历查找是否存在符合要求的图书
			Book book = (Book) books.get(i);
			if (book.getNum().equals(num)) {// 如果找到，立即打印并且退出当前遍历，将flag=1
				System.out.println("[序号]\t[书号]\t\t[书名]\t\t\t [作者]\t\t[出版日期]\t[价格]\t[库存]");
				System.out.println("  " + 1 + "\t " + book.getNum() + "\t     " + book.getName() + "\t      " + book.getAuthor() + "\t" + book.getDate() + "\t " + book.getPrice() + "\t " + book.getAmount());
				flag = 1;
				break;
			} else
				;
		}
		if (flag == 0) {// 通过遍历找不到书，输出警告
			System.out.println("没有这本书！！！！");
		}
	}

	public void add() {// 增加图书功能
		show();// 增加图书之前，打印库存
		String num;
		String name;
		String author;
		String date;
		float price;
		int amount;
		int i = 0;
		int flag = 1;// flag为检索到相同图书的标志变量，如果始终=1，说明没有相同的图书；如果=0，说明存在相同的图书
		System.out.print("请输入需要添加的书的书号:");
		Scanner input = new Scanner(System.in);
		num = input.next();
		while (flag == 1) {
			for (i = 0; i < books.size(); i++) {
				Book book1 = (Book) books.get(i);
				if (book1.getNum().equals(num)) {// 找到相同的立马输出警告，提示重新输入，同时立即跳出循环重新开始遍历
					System.out.print("警告！！该图书已经存在！！请重新输入:");
					num = input.next();
					break;// 找到相同的图书立即退出当前的for循环
				} else// 没有找到相同的继续执行当前循环
					;
			}
			if (i == books.size()) {// 完全执行了一次遍历也没有找到相同的图书的话，说明该书可以被添加
				flag = 0;// 只有输入不存在的图书，才推出while循环
			}
		}
		System.out.print("书名:");
		name = input.next();
		System.out.print("作者:");
		author = input.next();
		System.out.print("出版日期:");
		date = input.next();
		System.out.print("价格:");
		price = input.nextFloat();
		System.out.print("库存:");
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
			pstmt.execute();// 将新的图书加入到本地数据库中
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			JDBCUtils.release(pstmt, con);
		}
		System.out.print("添加图书成功！！");
		show();// 添加成功之后再输出一遍
	}

	public void in() {// 入库函数
		show();// 入库之前，打印库存
		String num;// 序号
		int amount = 0;
		int i = 0;
		int flag = 1;// flag为检索到相同图书的标志变量，如果始终=1，说明没有相同的图书；，如果=0，说明存在相同的图书
		Book book = new Book();// 新建对象book
		System.out.print("请输入需要入库的书号:");
		Scanner input = new Scanner(System.in);
		num = input.next();
		while (flag == 1) {
			for (i = 0; i < books.size(); i++) {
				book = (Book) books.get(i);
				if (book.getNum().equals(num)) {
					flag = 0;// 一旦找到相同的图书，将标志变量=0，并且退出当前遍历
					break;
				} else
					;
			}
			if (flag == 1) {// 完全执行了一次遍历也没有找到相同的图书的话，说明该书入不了库
				System.out.print("警告！！该图书不存在！！请重新输入:");
				num = input.next();
			} else
				;
		}
		System.out.print("请输入需要入库的数量:");
		amount = input.nextInt();
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement("update book set amount=? where num=?");
			pstmt.setInt(1, book.getAmount() + amount);// 更新输入的书号对应的库存
			pstmt.setString(2, num);
			pstmt.execute();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			JDBCUtils.release(pstmt, con);
		}
		System.out.println("入库成功！！！");
		show();// 入库成功之后再输出一遍
	}

	public void out() {// 输出函数
		show();// 出库之前，打印库存
		String num;
		int amount = 0;
		int i = 0;
		int flag = 1;// flag为检索到相同图书的标志变量，如果始终=1，说明没有相同的图书，如果=0，说明存在相同的图书
		Book book = new Book();
		System.out.print("请输入需要出库的书号:");
		Scanner input = new Scanner(System.in);
		num = input.next();
		while (flag == 1) {
			for (i = 0; i < books.size(); i++) {
				book = (Book) books.get(i);
				if (book.getNum().equals(num)) {
					flag = 0;// 一旦找到相同的图书，将标志变量=0，并且退出当前遍历
					break;
				} else
					;
			}
			if (flag == 1) {// 完全执行了一次遍历也没有找到相同的图书的话，说明该书不可以出库，重新进行输入执行新的遍历
				System.out.print("警告！！该图书不存在！！请重新输入:");
				num = input.next();
			} else
				;
		}
		System.out.print("请输入需要出库的数量:");
		amount = input.nextInt();
		while ((book.getAmount() - amount) < 0) {// 判断输入是否超库存
			System.out.print("库存不足，请重新输入:");
			amount = input.nextInt();// 只有输入不超库存才退出循环
		}
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement("update book set amount=? where num=?");
			pstmt.setInt(1, book.getAmount() - amount);// 更新库存
			pstmt.setString(2, num);
			pstmt.execute();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			JDBCUtils.release(pstmt, con);
		}
		System.out.println("出库成功！！！");
		show();// 出库成功后再输出库存
	}

	public void delete() {// 删除功能
		show();// 删除之前，打印库存
		String num;
		int i = 0;
		int flag = 1;// flag为检索到相同图书的标志变量，如果始终=1，说明没有相同的图书，如果=0，说明存在相同的图书
		Book book = new Book();
		System.out.print("请输入需要删除的书号:");
		Scanner input = new Scanner(System.in);
		num = input.next();
		while (flag == 1) {
			for (i = 0; i < books.size(); i++) {
				book = (Book) books.get(i);
				if (book.getNum().equals(num)) {
					flag = 0;// 一旦找到相同的图书，将标志变量=0，并且退出当前遍历
					break;
				} else
					;
			}
			if (flag == 1) {// 完全执行了一次遍历也没有找到相同的图书的话，说明该书不存在，重新输入执行新的遍历
				System.out.print("警告！！该图书不存在！！请重新输入:");
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
		System.out.println("图书删除成功！！！");
		show();// 删除成功后再输出库存
	}

	public void show() {// 查看功能
		int num = 1;
		books.clear();
		try {
			con = JDBCUtils.getConnection();
			stat = con.createStatement();
			rs = stat.executeQuery("select * from book");// 从本地数据库中获取图书信息保存进Arraylist类的books中
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
		System.out.println("仓库总共有" + books.size() + "种书");
		System.out.println("[序号]\t[书号]\t\t[书名]\t\t\t [作者]\t\t[出版日期]\t[价格]\t[库存]");
		for (int i = 0; i < books.size(); i++) {// 输出库存
			Book book1 = (Book) books.get(i);
			System.out.println("  " + num + "\t " + book1.getNum() + "\t     " + book1.getName() + "\t      " + book1.getAuthor() + "\t" + book1.getDate() + "\t " + book1.getPrice() + "\t " + book1.getAmount());
			num++;
		}
	}

	// 快速排序算法
	public void quick_sort(int[][] a, int left, int right) {// 传入参数是二维数组，左边的索引，右边的索引
		int l = left;// 左下标
		int r = right;// 右下标
		int pivot = a[0][(l + r) / 2];// 找到中间的值
		int[][] temp = new int[2][1];// 中间的交换变量
		// while循环的目的是将比pivot小的放到右边，比pivot大的放到左边
		while (l < r) {
			while (a[0][l] > pivot) {// 在pivot的左边一直找，找到<=pivot的值才退出
				l++;
			}
			while (a[0][r] < pivot) {// 在pivot的右边一直找，找到>=pivot的值才退出
				r--;
			}
			if (l >= r) {// 如果l >= r成立，说明左边都比pivot大，右边都比pivot小
				break;
			}
			temp[0][0] = a[0][l];
			temp[1][0] = a[1][l];// 数值下标必须一起交换
			a[0][l] = a[0][r];
			a[1][l] = a[1][r];// 数值下标必须一起交换
			a[0][r] = temp[0][0];
			a[1][r] = temp[1][0];// 数值下标必须一起交换
			if (a[0][l] == pivot) {// 如果交换完后发现a[0][l]==privot，需要让r--,前移
				r--;
			}
			if (a[0][r] == pivot) {// 如果交换完后发现a[0][l]==privot，需要让l++,后移
				l++;
			}
		}
		// 如果l==r，必须使得l++,r--,否则将会出现栈溢出
		if (l == r) {
			l++;
			r--;
		}
		// 向左递归
		if (left < r) {
			quick_sort(a, left, r);
		}
		// 向右递归
		if (right > l) {
			quick_sort(a, l, right);
		}
	}

	public void rank() {// 库存排行榜功能
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
		int[][] a = new int[2][books.size()];// 用二维数组对库存进行排序，原因是因为将数值排序的同时带着下标也一起排序，最后根据下标来输出
		for (i = 0; i < books.size(); i++) {
			Book book = (Book) books.get(i);
			a[0][i] = book.getAmount();
			a[1][i] = i;
		}

		quick_sort(a, 0, books.size() - 1);// 执行快速排序

		System.out.println("仓库总共有" + books.size() + "种书");
		System.out.println("[序号]\t[书号]\t\t[书名]\t\t\t [作者]\t\t[出版日期]\t[价格]\t[库存]");
		for (i = 0; i < books.size(); i++) {
			Book book1 = (Book) books.get(a[1][i]);// 根据之前排序好的下标进行输出
			System.out.println("  " + num + "\t " + book1.getNum() + "\t     " + book1.getName() + "\t      " + book1.getAuthor() + "\t" + book1.getDate() + "\t " + book1.getPrice() + "\t " + book1.getAmount());
			num++;
		}
	}

	public void buy() {// 购买功能
		show();// 购买之前，输出库存
		String num;
		int amount = 0;
		int i = 0;
		int flag = 1;// flag为检索到相同图书的标志变量，如果始终=1，说明没有相同的图书，如果=0，说明存在相同的图书
		char confirm1 = 0;// 继续购买循环确认变量
		int cycle = 1;// 继续购买图书循环变量
		float money = 0;// 一种图书的结算
		float total_money = 0;// 所有图书的结算
		while (cycle == 1) {
			Book book = new Book();
			good = new Customer();
			System.out.print("请输入需要购买的书号:");
			Scanner input = new Scanner(System.in);
			num = input.next();
			while (flag == 1) {
				for (i = 0; i < books.size(); i++) {
					book = (Book) books.get(i);
					if (book.getNum().equals(num)) {
						flag = 0;// 一旦找到相同的图书，将标志变量=1，将图书信息赋值给购物车中间变量，并且退出当前遍历
						good.setNum(num);
						good.setName(book.getName());
						good.setAuthor(book.getAuthor());
						good.setDate(book.getDate());
						good.setPrice(book.getPrice());
						break;
					} else
						;
				}
				if (flag == 1) {// 完全执行了一次遍历也没有找到相同的图书的话，说明该书不存在，重新输入执行新的遍历
					System.out.print("警告！！该图书不存在！！请重新输入:");
					num = input.next();
				} else
					;
			}
			System.out.print("请输入需要购买的数量:");
			amount = input.nextInt();
			while ((book.getAmount() - amount) < 0) {// 判断输入是否超库存
				System.out.print("库存不足，请重新输入:");
				amount = input.nextInt();// 只有输入不超库存才退出循环
			}
			good.setAmount(amount);
			money = amount * book.getPrice();// 计算价钱
			good.setMoney(money);// 将价钱记录进中间变量中
			trolly.add(good);// 将书添加到购物车里
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
			System.out.println("购买图书成功！！！");
			System.out.print("请问还需要购买图书么？（y or press any key）:");// 输入y继续购买，输入其他退出当前客户的租赁
			@SuppressWarnings("resource")
			Scanner input1 = new Scanner(System.in);
			confirm1 = input1.next().charAt(0);
			if (confirm1 != 'y') {
				cycle = 0;
			} else {// 选择继续购买图书后，必须将之前循环的变量和记录相同书本的变量复位置原值，程序才会工作正常
				confirm1 = 0;
				flag = 1;
			}
		}
		System.out.println("\n-----------以下是购物清单-----------");
		System.out.println("[序号]\t[书号]\t\t[书名]\t\t\t [作者]\t\t[出版日期]\t[价格]\t[数量]\t[金额]");
		for (i = 0; i < trolly.size(); i++) {
			Customer custom = (Customer) trolly.get(i);// 输出用户的购物清单
			System.out.println("  " + (i + 1) + "\t " + custom.getNum() + "\t     " + custom.getName() + "\t      " + custom.getAuthor() + "\t" + custom.getDate() + "\t " + custom.getPrice() + "\t " + custom.getAmount() + "\t " + custom.getMoney());
			total_money = total_money + custom.getMoney();
		}
		System.out.println("\n需支付" + "【" + total_money + "】" + "元");// 输出总价钱
		System.out.println("---------------------------\n");
	}

	public void admin() {// 管理员功能
		int choose = 0;
		int cycle = 1;
		String Username;
		String Password;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print(">>Administrator Username:");// 输入用户名和密码
		Username = input.next();
		System.out.print(">>Administrator Password:");
		Password = input.next();
		while ((Username.equals("admin") != true) && (Password.equals("admin") != true)) {// 用户名和密码=admin才能进入
			System.out.println("用户名密码输入错误，请重新输入！！！");
			System.out.print(">>Administrator Username:");
			Username = input.next();
			System.out.print(">>Administrator Password:");
			Password = input.next();
		}
		System.out.println("！！！登录成功！！！");
		while (cycle == 1) {
			System.out.print(">>请选择功能（1.出库    2.入库    3.库存列表    4.搜索图书    5.新增    6.删除    7.库存排行榜    8.退出账户）:");// 二级选择界面，8种功能
			choose = input.nextInt();
			while ((choose != 1) && (choose != 2) && (choose != 3) && (choose != 4) && (choose != 5) && (choose != 6) && (choose != 7) && (choose != 8)) {
				System.out.println("输入错误！！，请重新输入！！：");
				choose = input.nextInt();
			}
			switch (choose) {
			case 1:
				System.out.println("————>出库功能启动");
				out();// 出库功能
				break;
			case 2:
				System.out.println("————>入库功能启动");
				in();// 入库功能
				break;
			case 3:
				System.out.println("————>库存列表功能启动");
				show();// 查看功能
				break;
			case 4:
				System.out.println("————>搜索图书功能启动");
				search();// 搜索图书功能
				break;
			case 5:
				System.out.println("————>新增功能启动");
				add();// 新增功能
				break;
			case 6:
				System.out.println("————>删除功能启动");
				delete();// 删除功能
				break;
			case 7:
				System.out.println("————>库存排行榜功能启动");
				rank();// 库存排行榜功能
				break;
			case 8:
				cycle = 0;
				System.out.println("————>退出账户成功");
				break;
			default:
				break;
			}
		}

	}

	public void customer() {// 普通用户功能
		int choose = 0;
		int cycle = 1;
		String Username;
		String Password;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print(">>Username:");// 输入用户名和密码
		Username = input.next();
		System.out.print(">>Password:");
		Password = input.next();
		while (Username.equals(Password) != true) {
			System.out.println("用户名密码输入错误，请重新输入！！！");// 用户名和密码相同才能进入系统
			System.out.println(">>Username:");
			Username = input.next();
			System.out.println(">>Password:");
			Password = input.next();
		}
		System.out.println("！！！登录成功！！！");
		while (cycle == 1) {
			System.out.print(">>请选择功能（1.库存列表    2.搜索图书    3.购买图书    4.库存排行榜    5.退出账户）:");// 二级选择界面，5种功能
			choose = input.nextInt();
			while ((choose != 1) && (choose != 2) && (choose != 3) && (choose != 4) && (choose != 5)) {
				System.out.println("输入错误！！，请重新输入！！：");
				choose = input.nextInt();
			}
			switch (choose) {
			case 1:
				System.out.println("————>库存列表功能启动");
				show();// 查看功能
				break;
			case 2:
				System.out.println("————>搜索图书功能启动");
				search();// 搜索图书功能
				break;
			case 3:
				System.out.println("————>购买图书功能启动");
				buy();// 库存排行榜功能
				break;
			case 4:
				System.out.println("————>库存排行榜功能启动");
				rank();// 库存排行榜功能
				break;
			case 5:
				cycle = 0;
				trolly.clear();
				System.out.println("————>退出账户成功");
				break;
			default:
				break;
			}
		}

	}

	public int login_initial() {// 登陆初始化
		int choose = 0;
		System.out.print(">>请选择模式（1.库存管理员    2.顾客    3.退出系统）:");// 一级选择界面，3种功能
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		choose = input.nextInt();
		while ((choose != 1) && (choose != 2) && (choose != 3)) {
			System.out.println("输入错误！！，请重新输入！！:");
			choose = input.nextInt();
		}
		return choose;
	}

	public void menu() {
		int option = 0;// option用来保存用户的登陆模式选项
		int cycle = 1;// 登陆界面模式循环变量
		system_info();// 系统信息初始化
		while (cycle == 1) {
			option = login_initial();// option用来保存用户的登陆模式选项
			if (option == 1) {
				admin();// 执行管理员模式
			} else if (option == 2) {
				customer();// 执行顾客模式
			} else {
				cycle = 0;
				System.out.println("退出系统成功！！！");
			}
		}
	}
}
