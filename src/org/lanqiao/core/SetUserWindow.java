package org.lanqiao.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import javax.swing.JButton;

/**
 * 修改联系人信息，
 * 
 * @author qilixiang
 *
 */
public class SetUserWindow extends JFrame {
	private JTextField textField;

	public SetUserWindow() {
		setTitle("修改联系人信息");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(53, 71, 67, 21);
		getContentPane().add(comboBox);

		textField = new JTextField();
		textField.setBounds(177, 71, 170, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = comboBox.getSelectedIndex();// 返回下拉框中的索引
				// String select = (String) comboBox.getSelectedItem();
				String newMessage = textField.getText();
				if (newMessage.equals("") || newMessage.contains(" ")) {
					JOptionPane.showMessageDialog(SetUserWindow.this, "请输入有效的字符");
				} else {
					String email = TXLWindow.list.get(TXLWindow.index).getEmail();// 要需改的联系人的email
					JDBCOperationImpl.me.update(i, email, newMessage);
					JOptionPane.showMessageDialog(SetUserWindow.this, "修改成功！");
				}
			}
		});
		btnNewButton.setBounds(53, 194, 67, 23);
		getContentPane().add(btnNewButton);

		JButton button = new JButton("取消");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetUserWindow.this.dispose();
			}
		});
		button.setBounds(177, 194, 67, 23);
		getContentPane().add(button);
		comboBox.addItem("姓名");
		comboBox.addItem("职务");
		comboBox.addItem("昵称");
		comboBox.addItem("邮箱");
	}
}
