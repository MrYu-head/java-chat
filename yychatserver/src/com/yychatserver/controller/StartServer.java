package com.yychatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


import com.mysql.jdbc.PreparedStatement;
import com.yychat.model.Message;
import com.yychat.model.User;

public class StartServer {
	public static HashMap hmSocket=new HashMap<String,Socket>();
	
	Socket s;
	ServerSocket ss;
	String userName;
	String passWord;
	public StartServer() {
		
		
		try {//
			ss=new ServerSocket(3456);
			System.out.println("服务器已经启动，监听3456端口");
		
			while(true){

			s=ss.accept();//
			System.out.println("连接成功:"+s);
		//
		ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
		User user=(User)ois.readObject();
		this.userName=user.getUserName();
		this.passWord=user.getPassWord();
		System.out.println(userName);
		System.out.println(passWord);
		
		//从数据库中实现用户的登录验证
		//1、加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("已经加载了数据库驱动！");
	
		//2、连接数据库
		String url="jdbc:mysql://127.0.0.1:3306/yychat";
		//中文用户名必须用下面的url
		//String url="jdbc:mysql://127.0.0.1:3306/yychat?useUnicode=true&characterEncoding=UTF-8";
		String dbUser="root";
		String dbPass="";				
		Connection conn=DriverManager.getConnection(url,dbUser,dbPass);
		
		//3、创建PreparedStatement对象，用来执行SQL语句
		String user_Login_Sql="select * from user where username=? and password=?";
		java.sql.PreparedStatement ptmt=conn.prepareStatement(user_Login_Sql);
		ptmt.setString(1, userName);
		ptmt.setString(2, passWord);
		
		//4、执行查询，返回结果集
		ResultSet rs=ptmt.executeQuery();
		
		//5、根据结果集来判断是否能登录
		boolean loginSuccess=rs.next();	
//		//2、获取连接对象
//		String url="";
//		String dbuser="root";
//		String dbpass="";
//		Connection conn=DriverManager.getConnection(url,dbuser,dbpass);
//		
//		
		
		
		
		
		
		
		//
		Message mess=new Message();
		mess.setSender("Server");
		mess.setReceiver(user.getUserName());
		if(user.getPassWord().equals("123456")){
			//
			
			mess.setMessageType(Message.message_LoginSuccess);//
			
		}else{
			
			mess.setMessageType(Message.message_LoginFailure);//
			
		}
		ObjectOutputStream oos =new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(mess);
		//
		if(user.getPassWord().equals("123456")){
			hmSocket.put(userName, s);
		new ServerReceiverThread(s).start();
		}
						}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}