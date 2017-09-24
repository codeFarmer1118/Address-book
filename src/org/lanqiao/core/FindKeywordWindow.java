package org.lanqiao.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FindKeywordWindow extends JFrame {
	private JTable table;
	List<User> list = TXLWindow.list;

	public FindKeywordWindow() {
		setTitle("关键字查询");
		setSize(500, 200);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 464, 110);
		getContentPane().add(scrollPane);

		List<User> findUsers = searchUser(TXLWindow.keyword);
		String[][] fLists = new String[findUsers.size()][5];
		for (int j = 0; j < fLists.length; j++) {
			fLists[j][0] = String.valueOf(j + 1);
			fLists[j][1] = findUsers.get(j).getName();
			fLists[j][2] = findUsers.get(j).getJob();
			fLists[j][3] = findUsers.get(j).getNickName();
			fLists[j][4] = findUsers.get(j).getEmail();
		}

		String[] headers = { "编号", "姓名", "职务", "昵称", "邮箱" };
		table = new JTable(fLists, headers);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindKeywordWindow.this.dispose();
			}
		});
		btnNewButton.setBounds(180, 128, 79, 23);
		getContentPane().add(btnNewButton);

	}

	private List<User> searchUser(String keyWord) {
		List<User> findUsers = new ArrayList<User>();
		for (int i = 0; i < list.size(); i++) {
			User user = null;
			if (list.get(i).getName().contains(keyWord) || list.get(i).getJob().contains(keyWord)
					|| list.get(i).getNickName().contains(keyWord) || list.get(i).getEmail().contains(keyWord)) {
				String name = list.get(i).getName();
				String job = list.get(i).getJob();
				String nickName = list.get(i).getNickName();
				String email = list.get(i).getEmail();
				user = new User(name, job, nickName, email);
			}
			if(null!=user){
				findUsers.add(user);
			}
		}
		return findUsers;
	}
}
