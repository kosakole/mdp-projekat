package org.unibl.etf.mdp.workwithusers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.unibl.etf.mdp.workwithusers.model.User;
import org.unibl.etf.mdp.workwithusers.model.UserStatus;
import org.unibl.etf.mdp.workwithusers.service.UserService;

public class UsersForm extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int WIDTH = 900;
	private static int HEIGHT = 600;
	private static int X = 450;
	private static int Y = 150;
	private static final String TITLE = "Users";
	
	public UsersForm() {
		setTitle(TITLE);
		setBounds(X, Y, WIDTH, HEIGHT);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}
	
	private void init() {
		
		panel = new JPanel();
		buttonApprove = new JButton(APPROVE);
		buttonReject = new JButton(REJECT);
		buttonDelete = new JButton(DELETE);
		buttonBlock = new JButton(BLOCK);
		panel.setLayout(null);
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		buttonApprove.setBounds(WIDTH - 180, 15, 150, 40);
		buttonReject.setBounds(WIDTH - 180, 60, 150, 40);
		buttonDelete.setBounds(WIDTH - 180, 105, 150, 40);
		buttonBlock.setBounds(WIDTH - 180, 150, 150, 40);
		
		panel.add(buttonApprove);
		panel.add(buttonReject);
		panel.add(buttonDelete);
		panel.add(buttonBlock);
		
		setTable();
		add(panel);
		
		buttonApprove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = tableUsers.getSelectedRow();
				if(index < 0)
					return;
				User user = users.get(index);
				user.setStatus(UserStatus.APPROVED);
				service.update(user);
				new UsersForm().setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		buttonReject.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int index = tableUsers.getSelectedRow();
						if(index < 0)
							return;
						User user = users.get(index);
						user.setStatus(UserStatus.REJECTED);
						service.update(user);
						new UsersForm().setVisible(true);
						setVisible(false);
						dispose();
					}
				});
		
		buttonBlock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = tableUsers.getSelectedRow();
				if(index < 0)
					return;
				User user = users.get(index);
				user.setStatus(UserStatus.BLOCKED);
				service.update(user);
				new UsersForm().setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = tableUsers.getSelectedRow();
				if(index < 0)
					return;
				User user = users.get(index);
				user.setStatus(UserStatus.DELETED);
				service.delete(user);
				new UsersForm().setVisible(true);
				setVisible(false);
				dispose();
			}
		});
	}
	
	private void setTable() {
		users = service.getAll();
		String[][] mat = new String[users.size()][];
		for(int i = 0; i < users.size(); i++) {
			mat[i] = users.get(i).getUserStringArray();
		}
		
		tableUsers = new JTable(mat, headers){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		scPane = new JScrollPane(tableUsers);
		scPane.setBounds(5, 15, WIDTH - 200, HEIGHT - 100);
		tableUsers.setBounds(5, 15, WIDTH - 200, HEIGHT - 100);
		panel.add(scPane);
	}

	public static void main(String[] args) {
		new UsersForm().setVisible(true);

	}
	
	private UserService service = new UserService();
	private JTable tableUsers;
	private JScrollPane scPane;
	private JPanel panel;

	private JButton buttonApprove;
	private JButton buttonReject;
	private JButton buttonDelete;
	private JButton buttonBlock;
	
	private List<User> users;
	private static final String APPROVE = "Approve";
	private static final String REJECT = "Reject";
	private static final String DELETE = "Delete";
	private static final String BLOCK = "Block";
	private String[] headers = {"Company name", "Address", "Phone", "Username", "Status"};
}
