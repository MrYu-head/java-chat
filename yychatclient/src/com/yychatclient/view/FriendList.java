package com.yychatclient.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.*;

	public class FriendList extends JFrame implements ActionListener,MouseListener{//顶层容器
		public static HashMap hmFriendChat1=new HashMap<String,FriendChat1>();
		
		CardLayout cardLayout;//卡片布局
		
		JScrollPane myFriendScrollPane,myStrangerScrollPane,blacklistScrollPane;
		
		
		JPanel myFriendPanel;
		JButton myFriendJButton;
		
		JPanel myStrangerBlackListJPanel;
		JButton myStrangerJButton;
		JButton blacklistJButton;
		
		JPanel myFriendListJPanel;
		static final int FRIENDCOUNT=51;
		JLabel[] myFriendJLabel=new JLabel[FRIENDCOUNT];//对象数组
		
		
		
		JPanel myStrangerPanel;
		
		JPanel myFriendStrangerPanel;
		JButton myFriendJButton1;
		JButton myStrangerJButton1;
		
		JButton blacklistJButton1;
		
		JPanel myStrangerListJPanel;
		static final int STRANGER=21;
		JLabel[] myStrangerJLabel=new JLabel[STRANGER];
		
		
		JPanel blacklistPanel;
		
		JPanel myFriendStrangerblacklistPanel;
		JButton myFriendJButton2;
		JButton myStrangerJButton2;
		JButton blacklistJButton2;
		
		JPanel blacklistJPanel;
		static final int BLACKLIST=21;
		JLabel[] blacklistJLabel=new JLabel[BLACKLIST];
		
		String userName;
		
		public FriendList(String userName){
			this.userName=userName;
			
			//第一张卡片
			myFriendPanel=new JPanel(new BorderLayout());//边界布局
			
			myFriendJButton=new JButton("我的好友");
			myFriendPanel.add(myFriendJButton,"North");
			
			//中部
			myFriendListJPanel=new JPanel(new GridLayout(FRIENDCOUNT-1,1));
			for(int i=1;i<FRIENDCOUNT;i++)
			{
				myFriendJLabel[i]=new JLabel(i+"",new ImageIcon("image/YY1.gif"),JLabel.LEFT);//"1"
				myFriendJLabel[i].setEnabled(false);
				//if(Integer.parseInt(userName)==i) myFriendJLabel[i].setEnabled(true);
				
				myFriendJLabel[i].addMouseListener(this);
				myFriendListJPanel.add(myFriendJLabel[i]);
			}
			    //myFriendJLabel[Integer.parseInt(userName)].setEnabled(true);
			myFriendScrollPane=new JScrollPane(myFriendListJPanel);
			myFriendPanel.add(myFriendScrollPane);
			
			myStrangerBlackListJPanel=new JPanel(new GridLayout(2,1));//网络布局
			myStrangerJButton=new JButton("我的陌生人");
			//添加事件监听器
			myStrangerJButton.addActionListener(this);
			
			blacklistJButton=new JButton("黑名单");
			blacklistJButton.addActionListener(this);
			myStrangerBlackListJPanel.add(myStrangerJButton);
			myStrangerBlackListJPanel.add(blacklistJButton);
			myFriendPanel.add(myStrangerBlackListJPanel,"South");
			
			
			//另一张卡片
			myStrangerPanel = new JPanel(new BorderLayout());
			
			myFriendStrangerPanel=new JPanel(new GridLayout(2,1));
			
			myFriendJButton1=new JButton("我的好友");//添加监听器
			myFriendJButton1.addActionListener(this);
			myStrangerJButton1=new JButton("我的陌生人");
			myFriendStrangerPanel.add(myFriendJButton1);
			myFriendStrangerPanel.add(myStrangerJButton1);
			myStrangerPanel.add(myFriendStrangerPanel,"North");
			
			myStrangerListJPanel=new JPanel(new GridLayout(STRANGER-1,1));
			for(int i=1;i<STRANGER;i++)
			{
				myStrangerJLabel[i]=new JLabel(i+"",new ImageIcon("image/YY1.gif"),JLabel.LEFT);//"2"
				myStrangerJLabel[i].addMouseListener(this);
				myStrangerListJPanel.add(myStrangerJLabel[i]);
			}
			myStrangerScrollPane=new JScrollPane(myStrangerListJPanel);
			myStrangerPanel.add(myStrangerScrollPane);
			
			
			blacklistJButton1=new JButton("黑名单");
			blacklistJButton1.addActionListener(this);
			myStrangerPanel.add(blacklistJButton1,"South");
			
			
			//第三张卡片
			blacklistPanel=new JPanel(new BorderLayout());
			
			myFriendStrangerblacklistPanel=new JPanel(new GridLayout(3,1));
			myFriendJButton2=new JButton("我的好友");
			myFriendJButton2.addActionListener(this);
			myStrangerJButton2=new JButton("我的陌生人");
			myStrangerJButton2.addActionListener(this);
			blacklistJButton2=new JButton("黑名单");
			myFriendStrangerblacklistPanel.add(myFriendJButton2);
			myFriendStrangerblacklistPanel.add(myStrangerJButton2);
			myFriendStrangerblacklistPanel.add(blacklistJButton2);
			blacklistPanel.add(myFriendStrangerblacklistPanel,"North");
			
			blacklistJPanel=new JPanel(new GridLayout(BLACKLIST-1,1));
			for(int i=1;i<BLACKLIST;i++)
			{
				blacklistJLabel[i]=new JLabel(i+"",new ImageIcon("image/YY1.gif"),JLabel.LEFT);//"2"
				blacklistJLabel[i].addMouseListener(this);
				blacklistJPanel.add(blacklistJLabel[i]);
			}
			blacklistScrollPane=new JScrollPane(blacklistJPanel);
			blacklistPanel.add(blacklistScrollPane);
			
			
			
			
			cardLayout=new CardLayout();
			this.setLayout(cardLayout);
			this.add(myFriendPanel,"1");
			this.add(myStrangerPanel,"2");
			this.add(blacklistPanel,"3");
			
			this.setSize(150,500);
			this.setTitle(this.userName+" 的好友列表");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
		
		public static void main(String[] args){
			//FriendList friendList=new FriendList();
			
		}
		public void setEnableFriendIcon(String friendString){
			//
			String[] friendName=friendString.split(" ");
			int count=friendName.length;
			for(int i=0;i<count;i++){
				myFriendJLabel[Integer.parseInt(friendName[i])].setEnabled(true);
			}
		}
		@Override
		public void actionPerformed(ActionEvent argo){
			if(argo.getSource()==myStrangerJButton){
				cardLayout.show(this.getContentPane(), "2");
			}
			if(argo.getSource()==myStrangerJButton2){
				cardLayout.show(this.getContentPane(), "2");
			}
			if(argo.getSource()==myFriendJButton1){
				cardLayout.show(this.getContentPane(), "1");
				
			}
			if(argo.getSource()==myFriendJButton2){
				cardLayout.show(this.getContentPane(), "1");
				
			}
			if(argo.getSource()==blacklistJButton){
				cardLayout.show(this.getContentPane(), "3");
				
			}
			if(argo.getSource()==blacklistJButton1){
				cardLayout.show(this.getContentPane(), "3");
				
			}
			
			
		}
	
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount()==2){
				 JLabel jlbl=(JLabel)arg0.getSource();
				 String receiver=jlbl.getText();
				//new FriendChat(this.userName,receiver);
				 //new Thread(new FriendChat(this.userName,receiver)).start();
				 FriendChat1 friendChat1=(FriendChat1)hmFriendChat1.get(userName+"to"+receiver);
				 if(friendChat1==null){
				 friendChat1=new FriendChat1(this.userName,receiver);
				 hmFriendChat1.put(userName+"to"+receiver,friendChat1 );
				 
				 }else{
					 friendChat1.setVisible(true);
				 }
			
			}
			
		}
	
		@Override
		public void mouseEntered(MouseEvent arg0) {
			JLabel jLabel=(JLabel)arg0.getSource();
			jLabel.setForeground(Color.red);
		}
	
		@Override
		public void mouseExited(MouseEvent arg0) {
			JLabel jLabel=(JLabel)arg0.getSource();
			jLabel.setForeground(Color.black);
			
		}
	
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}