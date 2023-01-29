package main.frontend;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.utilities.LoadImage;
/**
 * 
 * @author Mohann Scarlett mohannscarlett3@gmail.com
 * @version 1/25/2023
 */
public class UserProfileChangeDesc  {
	JFrame frame = new JFrame();
	JPanel backkgroundPanel = new JPanel();
	private JLabel backgroundLabel;

	JLabel insertImageLabel = new JLabel("<html><span style='color:blue'>Insert New Description: <html><span style='color:blue'>");
	JTextField descLinkField = new JTextField(24);
	JPanel innerPanel = new JPanel();
	JLabel finishLabel = new JLabel("<html><span style='color:blue'>Finish</span style='color:blue'></html>");
	JButton finishUpdateButton = new JButton("Update Description");
	
	String username;
	int loggedInUserID;
	JFrame profileFrame;
	
	Connection connect;
	Statement st;
	JLabel itemImageLabel;
	
	public UserProfileChangeDesc(String username, int loggedInUserID, JFrame profileFrame, Connection connect, Statement st, JLabel itemImageLabel){
		this.username = username;
		this.loggedInUserID = loggedInUserID;
		this.profileFrame = profileFrame;
		this.connect = connect;
		this.st = st;
		this.itemImageLabel = itemImageLabel;
		
		backkgroundPanel.setPreferredSize(new Dimension(399,124));
		innerPanel.setPreferredSize(new Dimension(399,20));
		
		finishUpdateButton.setFocusable(false);
		finishUpdateButton.setOpaque(true);
		finishUpdateButton.setBackground(Color.white);
			
		Color invisible = new Color(0,0,0,0);
		innerPanel.setBackground(invisible);
		backkgroundPanel.setBackground(invisible);
		
		innerPanel.add(finishLabel);

		backkgroundPanel.add(insertImageLabel);
		backkgroundPanel.add(descLinkField);
		backkgroundPanel.add(innerPanel);
		backkgroundPanel.add(finishUpdateButton);
		
		LoadImage load = new LoadImage();
		backgroundLabel = new JLabel(load.loadImageIcon("resources/images/CloudBackground.png", "Failed To Load Image"));
		
		frame.setContentPane(backgroundLabel);
		frame.add(backkgroundPanel);
		
		frame.setLayout(new FlowLayout());
		/*if(unknownScreenSize) {
			frame.setBounds(570, 345, 0, 0);
		}else {
			frame.setBounds(730, 440, 0, 0);
		}*/
		frame.setSize(new Dimension(400,155));
		frame.setTitle("Fetch Item Details");
		Image img = load.loadImage("resources/images/galaxy1920x1080.jpg","Failed to Load Image");
		frame.setIconImage(img);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		UserProfileChangeEventHandlerDesc event = new UserProfileChangeEventHandlerDesc();
		
		finishUpdateButton.addActionListener(event);
		descLinkField.addActionListener(event);
	}
	private class UserProfileChangeEventHandlerDesc implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == descLinkField || event.getSource() == finishUpdateButton) {
				boolean validDesc = false;
				String user_desc;
				try {
					String userDesc = descLinkField.getText();
					if(userDesc.isBlank() || userDesc.isEmpty()) {
						throw new IllegalArgumentException();
					}

					user_desc = userDesc;
					validDesc = true;
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Invalid Description");
					return;
				}
				
				if(validDesc) {
					try{						
						String updateUserInfoSQL = "UPDATE user_information SET user_desc ='"+user_desc+"' WHERE userID = "+loggedInUserID+"";
						st.executeUpdate(updateUserInfoSQL);
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						}catch(Exception e) {
							String query = "INSERT INTO application_errors (error_message) VALUES ('"+e+"')";
							try {
								st.executeUpdate(query);
							} catch (SQLException e1) {
								System.out.println(e1);
							}
							JOptionPane.showMessageDialog(null, "Failed to Update Description");
					}
				}
			}
		}		
	}
}
