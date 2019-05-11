package com.yychatclient.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.*;

import com.yychat.model.Message;
import com.yychat.model.User;
import com.yychatclient.controller.ClientConnect;

	public class ClientLogin extends JFrame implements ActionListener{//������ClientLogin��ģ�壬�����ģ��
		public static HashMap hmFirendlist=new HashMap<String,FriendList>();
		
		
		//���山�����
		JLabel jlbl1;//�����ǩ
		
		//�����в����
		JTabbedPane jtp1;//ѡ�
		JPanel jp2,jp3,jp4;
		JLabel jlbl2,jlbl3,jlbl4,jlbl5;
		JTextField jtf1;
		JPasswordField jpf1;
		JButton jb4;
		JCheckBox jcb1,jcb2;
		
		//�����ϲ����
		JButton jb1,jb2,jb3;
		JPanel jp1;//��壬����
		
		public ClientLogin(){//���췽��
			//�����������
			jlbl1=new JLabel(new ImageIcon("image/tou.gif"));//��ǩ���
			this.add(jlbl1,"North");
			
			//�����в����
			jp2=new JPanel(new GridLayout(3,3));//���ֵ�����
			jp3=new JPanel();jp4=new JPanel();
			jlbl2=new JLabel("YY����",JLabel.CENTER);jlbl3=new JLabel("YY����",JLabel.CENTER);
			jlbl4=new JLabel("��������",JLabel.CENTER);
			jlbl4.setForeground(Color.blue);
			jlbl5=new JLabel("�������뱣��",JLabel.CENTER);
			jtf1=new JTextField();
			jpf1=new JPasswordField();
			jb4=new JButton(new ImageIcon("image/clear.gif"));
			jcb1=new JCheckBox("�����¼");jcb2=new JCheckBox("��ס����");
			jp2.add(jlbl2);	jp2.add(jtf1);	jp2.add(jb4);	
			jp2.add(jlbl3);	jp2.add(jpf1);	jp2.add(jlbl4);
			jp2.add(jcb1);	jp2.add(jcb2);	jp2.add(jlbl5);		
			jtp1=new JTabbedPane();
			jtp1.add(jp2,"YY����");jtp1.add(jp3,"�ֻ�����");jtp1.add(jp4,"��������");
			this.add(jtp1);
			
			//�����ϲ����
			jb1=new JButton(new ImageIcon("image/denglu.gif"));
			jb1.addActionListener(this);
			jb2=new JButton(new ImageIcon("image/zhuce.gif"));
			jb3=new JButton(new ImageIcon("image/quxiao.gif"));
			jp1=new JPanel();
			jp1.add(jb1);jp1.add(jb2);jp1.add(jb3);
			this.add(jp1,"South");
			getRootPane().setDefaultButton(jb1);
			
			this.setSize(350,240);//���ô��ڴ�С
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setVisible(true);		
		}
		
	
		public static void main(String[] args) {
			ClientLogin clientLogin=new ClientLogin();//�������󣬹��췽��
		}
	
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jb1){
				String userName = jtf1.getText().trim();
				String passWord=new String(jpf1.getPassword());
				User user=new User();
				user.setUserName(userName);
				user.setPassWord(passWord);
				
				boolean loginSuccess=new ClientConnect().loginValidate(user);
				if(loginSuccess){
					FriendList friendList=new FriendList(userName);
					hmFirendlist.put(userName, friendList);
					//��1��������������ͻ�ȡ�����û���Ϣ������Message��,���ͣ�message_RequestOnlineFriend
					Message mess=new Message();
					mess.setSender(userName);
					mess.setReceiver("Server");
					mess.setMessageType(Message.message_RequestOnlineFriend);
					Socket s=(Socket)ClientConnect.hmSocket.get(userName);
					ObjectOutputStream oos;
					try {
						oos = new ObjectOutputStream(s.getOutputStream());
						oos.writeObject(mess);
						
					} catch (IOException e1) {
					
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
						
					
					this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(this,"�������");
				}
				
			}
		}
		
	}
