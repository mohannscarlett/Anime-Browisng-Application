package main.frontend;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
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
public class UserProfileChangePicture  {
	JFrame frame = new JFrame();
	JPanel backkgroundPanel = new JPanel();
	private JLabel backgroundLabel;

	JLabel insertImageLabel = new JLabel("<html><span style='color:blue'>Insert Image URL<html><span style='color:blue'>");
	JTextField imageLinkField = new JTextField(24);
	JPanel innerPanel = new JPanel();
	JLabel finishLabel = new JLabel("<html><span style='color:blue'>Finish</span style='color:blue'></html>");
	JButton finishUpdateButton = new JButton("Update Image");
	
	String username;
	int loggedInUserID;
	JFrame profileFrame;
	
	Connection connect;
	Statement st;
	JLabel itemImageLabel;
	
	public UserProfileChangePicture(String username, int loggedInUserID, JFrame profileFrame, Connection connect, Statement st, JLabel itemImageLabel){
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
		backkgroundPanel.add(imageLinkField);
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
		Image img = load.loadImage("resources/images/galaxy1920x1080.jpg", username);
		frame.setIconImage(img);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		UserProfileChangeEventHandlerURL event = new UserProfileChangeEventHandlerURL();
		
		finishUpdateButton.addActionListener(event);
		imageLinkField.addActionListener(event);
	}
	public class UserProfileChangeEventHandlerURL implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == imageLinkField || event.getSource() == finishUpdateButton) {
				boolean validImage = false;
				String picture_link;
				try {
					String userPictureURL = imageLinkField.getText().replaceAll("\\s","");
					if(!userPictureURL.contains(".")) {
						throw new IllegalArgumentException();
					}

					URL url = new URL(userPictureURL);
					ImageIcon itemImage = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
					int s = itemImage.getIconHeight(); //check size of the image gotten, if -1 no image has been gotten
					if(s <= 0) {
						throw new IllegalArgumentException();
					}
					picture_link = userPictureURL;
					validImage = true;
				}catch(Exception e) {
					String query = "INSERT INTO application_errors (error_message) VALUES ('"+e+"')";
					try {
						st.executeUpdate(query);
					} catch (SQLException e1) {
						System.out.println(e1);
					}
					JOptionPane.showMessageDialog(null, "Invalid URL Entered");
					return;
				}
				
				if(validImage) {
					try{						
						String updateUserInfoSQL = "UPDATE user_information SET picture_link ='"+picture_link+"' WHERE userID = "+loggedInUserID+"";
						st.executeUpdate(updateUserInfoSQL);
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						}catch(Exception e) {
							JOptionPane.showMessageDialog(null, "Failed to Update Image");
					}
				}
			}
		}		
	}
}
