package com.yychatclient.controller;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

import com.yychat.*;
import com.yychat.model.Message;
import com.yychat.model.User;

public class ClientConnect {
	
	//public static Socket s;
	public Socket s;
	
	public static HashMap hmSocket=new HashMap<String,Socket>();
	
	public ClientConnect() {
		try {
			s=new Socket("127.0.0.1",3456);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public boolean loginValidate(User user){
		boolean loginSuccess=false;
		ObjectOutputStream oos;
		ObjectInputStream ois;
		Message mess=null;
		try {
			oos =new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(user);
			
			ois=new ObjectInputStream(s.getInputStream());
			mess=(Message)ois.readObject();
			
			
			if(mess.getMessageType().equals(Message.message_LoginSuccess)){
				loginSuccess=true;
				System.out.println(user.getUserName()+"µÇÂ½³É¹¦");
				hmSocket.put(user.getUserName(),s);
				new ClientRecieverThread(s).start();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return loginSuccess;
}
	}
