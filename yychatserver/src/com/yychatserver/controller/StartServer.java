package com.yychatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.yychat.model.Message;
import com.yychat.model.User;

	public class StartServer {
	public static HashMap hmSocket=new HashMap<String,Socket>();
	
		Socket s;
		ServerSocket ss;
		String userName;
		String passWord;
		Message mess;
		public StartServer() {
			
		
		try {//
			ss=new ServerSocket(3456);
			System.out.println("�������Ѿ�����������3456�˿�");
		
			while(true){

			s=ss.accept();//
			System.out.println("���ӳɹ�:"+s);
			//
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			User user=(User)ois.readObject();
			this.userName=user.getUserName();
			this.passWord=user.getPassWord();
			System.out.println(userName);
			System.out.println(passWord);
			
		
		
		//ʹ�����ݿ�����û������֤
		//1��������������
		/*Class.forName("com.mysql.jdbc.Driver");
		System.out.println("�Ѿ����������ݿ�������");
		//2���������ݿ�
		String url="jdbc:mysql://127.0.0.1:3306/yychat";
		//�����û��������������url
		//String url="jdbc:mysql://127.0.0.1:3306/yychat?useUnicode=true&characterEncoding=UTF-8";
		String dbUser="root";
		String dbPass="";				
		Connection conn=DriverManager.getConnection(url,dbUser,dbPass);
		
		//3������PreparedStatement��������ִ��SQL���
		String user_Login_Sql="select * from user where username=? and password=?";
		PreparedStatement ptmt=conn.prepareStatement(user_Login_Sql);
		ptmt.setString(1, userName);
		ptmt.setString(2, passWord);
		
		//4��ִ�в�ѯ�����ؽ����
		ResultSet rs=ptmt.executeQuery();
		
		//5�����ݽ�������ж��Ƿ��ܵ�¼
		boolean loginSuccess=rs.next();	*/
		boolean loginSuccess=YychatDbUtil.loginValidate(userName, passWord);

		
		//
		Message mess=new Message();
		mess.setSender("Server");
		mess.setReceiver(user.getUserName());
		if(user.getPassWord().equals("123456")){
			//
			
			mess.setMessageType(Message.message_LoginSuccess);//
			
			
			/*String friend_Relation_Sql="select * from relation where majoruser=? and relationtype='1'";
			ptmt=conn.prepareStatement(friend_Relation_Sql);
			ptmt.setString(1, userName);
			rs=ptmt.executeQuery();
			String friendString="";
			while(rs.next()){                                 
				friendString=friendString+rs.getString("slaveuser")+" ";
			}*/
			String friendString=YychatDbUtil.getFriendString(userName);
			mess.setContent(friendString);
			System.out.println(userName+"��relation���ݱ��к���;"+friendString);
			
			
		}else{
			
			mess.setMessageType(Message.message_LoginFailure);//
			
		}
		sendMessage(s,mess);
		//
		if(user.getPassWord().equals("123456")){
			//�ڴ˴����Լ���½�ɹ�����Ϣ���͵����û�֮ǰ���û�
			    mess.setMessageType(Message.message_NewOnlineFriend);
				mess.setSender("Server");
				mess.setContent(userName);
				
				Set onlineFriendSet=hmSocket.keySet();
				Iterator it=onlineFriendSet.iterator();
				String friendName;
				while(it.hasNext()){
					friendName=(String)it.next();
					mess.setReceiver(friendName);
					Socket s1=(Socket)hmSocket.get(friendName);
					sendMessage(s1,mess);
				}
			}
			hmSocket.put(userName, s);
		new ServerReceiverThread(s).start();
		}
						
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(Socket s,Message mess) throws IOException {
		ObjectOutputStream oos =new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(mess);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}