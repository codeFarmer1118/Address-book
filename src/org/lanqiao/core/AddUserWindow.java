package org.lanqiao.core;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUserWindow extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public AddUserWindow() {
		
		Image image = Picture.getImage("docs/logo.jpg");
		setIconImage(image);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("姓名");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(25, 28, 39, 15);
		getContentPane().add(label);

		textField = new JTextField();
		textField.setBounds(86, 25, 198, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("职务");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(25, 75, 39, 15);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("昵称");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(25, 126, 39, 15);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("邮箱");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(25, 175, 39, 15);
		getContentPane().add(label_3);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(86, 72, 198, 21);
		getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(86, 123, 198, 21);
		getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(86, 172, 198, 21);
		getContentPane().add(textField_3);

		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textField.getText();
				String job = textField_1.getText();
				String nickName = textField_2.getText();
				String email = textField_3.getText();
				String c = name + job + nickName + email;
				if (Utils.isExistent(email)){
					JOptionPane.showMessageDialog(AddUserWindow.this, "该联系人已存在！");
				}
				else {
					// 判断非法字符的输入
					if (c.equals("") || c.contains(" ")) {
						JOptionPane.showMessageDialog(AddUserWindow.this, "信息不为空！");
					} else {
						User user = new User(name, job, nickName, email);
						// 向数据库里传递信息
						JDBCOperationImpl.me.insert(user);
						JOptionPane.showMessageDialog(AddUserWindow.this, "添加成功   请刷新！");
						AddUserWindow.this.dispose();
					}
				}
			}
		});
		button.setBounds(103, 228, 70, 23);
		getContentPane().add(button);

		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUserWindow.this.dispose();
			}
		});
		button_1.setBounds(183, 228, 70, 23);
		getContentPane().add(button_1);

		setTitle("添加联系人");
		setSize(400, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
