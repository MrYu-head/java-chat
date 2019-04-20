package com.yychatclient.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.yychat.model.Message;
import com.yychatclient.view.ClientLogin;
import com.yychatclient.view.FriendChat1;
import com.yychatclient.view.FriendList;

public class ClientRecieverThread extends Thread{
	
	private Socket s;
	
	public ClientRecieverThread(Socket s){
		this.s=s;
	}
	public void run(){
		ObjectInputStream ois;
		while(true){
		try {
			ois = new ObjectInputStream(s.getInputStream());
			Message mess = (Message)ois.readObject();
			String showMessage=mess.getSender()+"对"+mess.getReceiver()+"说："+mess.getContent();
			System.out.println(showMessage);
			//jta.append(showMessage+"\r\n");
			if(mess.getMessageType().equals(Message.message_Common)){
			FriendChat1 friendChat1=(FriendChat1)FriendList.hmFriendChat1.get(mess.getReceiver()+"to"+mess.getSender());
			
			friendChat1.appendJta(showMessage);
			}
			if(mess.getMessageType().equals((Message.message_OnlineFriend))){
                System.out.println("在线好友"+mess.getContent());
				
				//
				FriendList friendList=(FriendList)ClientLogin.hmFirendlist.get(mess.getReceiver());
				//
				friendList.setEnableFriendIcon(mess.getContent());
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
				
	}
		
	
	
	}


