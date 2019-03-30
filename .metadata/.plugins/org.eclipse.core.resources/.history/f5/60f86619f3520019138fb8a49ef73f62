package com.chatclient.view;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import com.yychat.model.Message;
import com.yychat.model.User;
import com.yychatclient.control.ClientConnet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class ClientLogin extends JFrame implements ActionListener{
	
	JLabel jlbl1;
		
	
	JTabbedPane jtp1;
	JPanel jp2,jp3,jp4;
	JLabel jlbl2,jlbl3,jlbl4,jlbl5,jlbl20,jlbl30,jlbl40,jlbl50,jlbl21,jlbl31,jlbl41,jlbl51;
	JTextField jtf1,jtf10,jtf11;
	JPasswordField jpf1,jpf10,jpf11;
	JButton jb4,jb40,jb41;
	JCheckBox jcb1,jcb2,jcb10,jcb20,jcb11,jcb21;
	
	JButton jb1,jb2,jb3;
	JPanel jp1;
	
	public ClientLogin(String userName){

		jlbl1=new JLabel(new ImageIcon("images/tou.gif"));
		this.add(jlbl1,"North");
	
		
		//创建中部组建
		jp2=new JPanel(new GridLayout(3,3));//布局的问题
		jlbl2=new JLabel("嘤嘤号码",JLabel.CENTER);jlbl3=new JLabel("嘤嘤密码",JLabel.CENTER);
		jlbl4=new JLabel("忘记密码",JLabel.CENTER);
		jlbl4.setForeground(Color.blue);
		jlbl5=new JLabel("申请密码保护",JLabel.CENTER);
		jtf1=new JTextField();
		jpf1=new JPasswordField();
		jb4=new JButton(new ImageIcon("images/clear.gif"));
		jcb1=new JCheckBox("隐身登录");jcb2=new JCheckBox("记住密码");
		jp2.add(jlbl2);	jp2.add(jtf1);	jp2.add(jb4);	
		jp2.add(jlbl3);	jp2.add(jpf1);	jp2.add(jlbl4);
		jp2.add(jcb1);	jp2.add(jcb2);	jp2.add(jlbl5);	
		
		jp3=new JPanel(new GridLayout(3,3));
		jlbl20=new JLabel("手机号码",JLabel.CENTER);jlbl30=new JLabel("验证码",JLabel.CENTER);
		jlbl40=new JLabel("获取验证码",JLabel.CENTER);
		jlbl40.setForeground(Color.red);
		jlbl50=new JLabel("申请找回",JLabel.CENTER);
		jtf10=new JTextField();
		jpf10=new JPasswordField();
		jb40=new JButton(new ImageIcon("images/clear.gif"));
		jcb10=new JCheckBox("自动登录");jcb20=new JCheckBox("记住号码");
		jp3.add(jlbl20);	jp3.add(jtf10);	jp3.add(jb40);	
		jp3.add(jlbl30);	jp3.add(jpf10);	jp3.add(jlbl40);
		jp3.add(jcb10);		jp3.add(jcb20);	jp3.add(jlbl50);
		
		jp4=new JPanel(new GridLayout(3,3));
		jlbl21=new JLabel("邮箱号码",JLabel.CENTER);jlbl31=new JLabel("邮箱密码",JLabel.CENTER);
		jlbl41=new JLabel("忘记密码",JLabel.CENTER);
		jlbl41=new JLabel("忘记密码",JLabel.CENTER);
		jlbl41.setForeground(Color.red);
		jlbl51=new JLabel("申请找回",JLabel.CENTER);
		jtf11=new JTextField();
		jpf11=new JPasswordField();
		jb41=new JButton(new ImageIcon("images/clear.gif"));
		jcb11=new JCheckBox("自动登录");jcb21=new JCheckBox("记住号码");
		jp4.add(jlbl21);	jp4.add(jtf11);	jp4.add(jb41);	
		jp4.add(jlbl31);	jp4.add(jpf11);	jp4.add(jlbl41);
		jp4.add(jcb11);		jp4.add(jcb21);	jp4.add(jlbl51);
		
		
		
		jtp1=new JTabbedPane();
		jtp1.add(jp2,"嘤嘤号码");jtp1.add(jp3,"Phone");jtp1.add(jp4,"Email");
		this.add(jtp1);
		
		//创建南部组建
		jb1=new JButton(new ImageIcon("images/denglu.gif"));
		jb2=new JButton(new ImageIcon("images/zhuce.gif"));
		jb3=new JButton(new ImageIcon("images/quxiao.gif"));
		jp1=new JPanel();
		jp1.add(jb1);jp1.add(jb2);jp1.add(jb3);
		this.add(jp1,"South" );
				
		jb1.addActionListener(this);
		this.setSize(350,240);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	public static void main(String[] args){
			ClientLogin clientLogin=new ClientLogin(null);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1);{
			String userName= jtf1.getText();
			String password=new String(jpf1.getPassword());
			//创建User对象 
			User user =new User();
			user.setUserName(userName);
			user.setPassWord(password);
			
			Message mess=new ClientConnet().loginValidate(user);
			if(mess.getMessageType().equals("1")){
				new FriendList(userName);
				this.dispose();
				
			}else{
				JOptionPane.showMessageDialog(this, "密码错误");
				
				
			}
			

		}
		
		
		
	}
}
