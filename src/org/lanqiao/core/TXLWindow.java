package org.lanqiao.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TXLWindow extends JFrame {

	public static void main(String[] args) {
		new TXLWindow();
	}

	static int index = -1;// 需改的联系人的下标，初始化为-1；
	static String keyword = null;
	static List<User> list = JDBCOperationImpl.me.select();// mysql服务未开启时会报错
	private JTable table;
	private JTextField textField;

	public TXLWindow() {

		Image image = Picture.getImage("docs/logo.jpg");
		setIconImage(image);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddUserWindow();
			}
		});
		btnNewButton.setBounds(10, 301, 93, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 通过邮箱去找到要修改的联系人，判断文本框是否为空
				String email = textField.getText();
				if (email.equals("") || email.contains(" ")) {
					JOptionPane.showMessageDialog(TXLWindow.this, "请输入联系人邮箱！");
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (email.equals(list.get(i).getEmail())) {
							index = i;
							new SetUserWindow();
							return;
						}
					}
					JOptionPane.showMessageDialog(TXLWindow.this, "请确定后再输入");
				}
			}
		});
		btnNewButton_1.setBounds(113, 301, 93, 23);
		getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 464, 233);
		getContentPane().add(scrollPane);

		JButton button = new JButton("删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textField.getText();
				if (email.equals("") || email.contains(" ")) {
					JOptionPane.showMessageDialog(TXLWindow.this, "请输入联系人邮箱！");
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (email.equals(list.get(i).getEmail())) {
							JDBCOperationImpl.me.delete(email);// 删除联系人
							JOptionPane.showMessageDialog(TXLWindow.this, "删除成功  请刷新！");
							return;
						}
					}
					JOptionPane.showMessageDialog(TXLWindow.this, "请确定后再输入");
				}

			}
		});
		button.setBounds(216, 301, 93, 23);
		getContentPane().add(button);

		JButton button_1 = new JButton("关键字");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyword = textField.getText();
				if (keyword.equals("") || keyword.contains(" ")) {
					JOptionPane.showMessageDialog(TXLWindow.this, "请输入联系人邮箱！");
				} else {
					int count = 0;
					for (int i = 0; i < list.size(); i++) {
						String name = list.get(i).getName();
						String job = list.get(i).getJob();
						String nickName = list.get(i).getNickName();
						String email = list.get(i).getEmail();
						if (name.contains(keyword) || job.contains(keyword) || nickName.contains(keyword)
								|| email.contains(keyword)) {
							new FindKeywordWindow();//如果有符合关键字的就跳转到FindKeywordWindow窗口
							break;
						} else {
							count++;
							if (count == list.size()) {
								JOptionPane.showMessageDialog(TXLWindow.this, "未找到相关信息！");
							}
						}
					}
				}
			}
		});
		button_1.setBounds(319, 301, 93, 23);
		getContentPane().add(button_1);

		JButton button_2 = new JButton("退出");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TXLWindow.this.dispose();
			}
		});
		button_2.setBounds(319, 334, 93, 23);
		getContentPane().add(button_2);

		JButton button_3 = new JButton("查询");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 通过邮箱去查找
				String email = textField.getText();
				User user = null;
				for (int i = 0; i < list.size(); i++) {
					if (email.equals(list.get(i).getEmail())) {
						user = list.get(i);
						JOptionPane.showMessageDialog(TXLWindow.this, user.getName() + " " + user.getJob() + " "
								+ user.getNickName() + " " + user.getEmail());
						break;
					}
				}
				if (user == null) {
					JOptionPane.showMessageDialog(TXLWindow.this, "查无此人！");
				}
			}
		});
		button_3.setBounds(319, 268, 93, 23);
		getContentPane().add(button_3);

		String[] headers = { "编号", "姓名", "职务", "昵称", "邮箱" };
		String[][] lists = new String[list.size()][5];
		for (int i = 0; i < lists.length; i++) {
			lists[i][0] = String.valueOf(i + 1);
			lists[i][1] = list.get(i).getName();
			lists[i][2] = list.get(i).getJob();
			lists[i][3] = list.get(i).getNickName();
			lists[i][4] = list.get(i).getEmail();
		}
		table = new JTable(lists, headers);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setBounds(10, 270, 299, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton button_4 = new JButton("刷新");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TXLWindow.this.dispose();
				new TXLWindow();
			}
		});
		button_4.setBounds(10, 334, 93, 23);
		getContentPane().add(button_4);

		setTitle("通讯录");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
