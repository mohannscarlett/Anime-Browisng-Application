package main.frontend;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.utilities.LoadImage;
/**
 * 
 * @author Mohann Scarlett mohannscarlett3@gmail.com
 * @version 1/25/2023
 */
public class UserProfile {
	private JFrame frame = new JFrame();
	private  ImageIcon background; //background image
	private Image backgroundImage;
	private JLabel backgroundLabel;

	private JLabel usernameLabel; 
	private JLabel userBirthdayLabel;
	private JLabel userDescLabel = new JLabel("<html><U><span style='color:green'>User Description:</U></span><html>");
	private JTextArea userDescText = new JTextArea();
	private JScrollPane backGroundPanelScrollPane = new JScrollPane(userDescText);
	public  JButton changeDescButton = new JButton("Change Your Description");
	public  JButton changePictureButton = new JButton("Change Your Picture");
	private JLabel itemImageLabel;
	
	public UserProfile(String username, int loggedInUserID, String birthday, URL ImageURL, String userDesc, Connection connect, Statement st){
		LoadImage load = new LoadImage();
		Color invisible = new Color(0,0,0,0); //make JComponents invisible 
		backgroundLabel = new JLabel(load.loadImageIcon("resources/images/CloudBackground.png", "Failed To Load Image"));
		
		userDescText.setLineWrap(true);
		userDescText.setEditable(false);
		userDescText.setBackground(invisible);
		userBirthdayLabel = new JLabel("<html><U><span style='color:green'>Birthday: </U></span><html>" + birthday);
		usernameLabel = new JLabel("<html><U><span style='color:green'>Username: </U></span><html>" + username);
		backGroundPanelScrollPane.setViewportView(userDescText);
		
		int stringSizeLimit = 347;
		if(userDesc.length() >=  stringSizeLimit) {
			String itemDescFiltered = userDesc.substring(0,stringSizeLimit) + "...";
			userDescText.setText(itemDescFiltered);
		}else {
			userDescText.setText(userDesc);
		}
		
		//set userProfile Image qualities
		ImageIcon itemImage = new ImageIcon(new ImageIcon(ImageURL).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		itemImageLabel = new JLabel();
		itemImageLabel.setSize(new Dimension(50,50));
		itemImageLabel = new JLabel(itemImage);
		
		itemImageLabel.setBounds(175, 25, 100, 100);
		usernameLabel.setBounds(25, 110, 325, 50);
		userBirthdayLabel.setBounds(25, 160, 150, 50);
		userDescLabel.setBounds(25, 210, 100, 50);
		userDescText.setBounds(136, 229, 250, 140);
		changeDescButton.setBounds(15, 400, 175, 50);
		changeDescButton.setFocusable(false);
		changeDescButton.setBackground(Color.white);
		changePictureButton.setBounds(205, 400, 175, 50);
		changePictureButton.setFocusable(false);
		changePictureButton.setBackground(Color.white);
		
		//add elements directly to frame
		frame.setContentPane(backgroundLabel);
		frame.add(itemImageLabel);
		frame.add(userBirthdayLabel);
		frame.add(usernameLabel);
		frame.add(userDescLabel);
		frame.add(userDescText);
		frame.add(changeDescButton);
		frame.add(changePictureButton);

		//set frame attributes
		/*if(unknownScreenSize) {
			frame.setBounds(320, 110, 0, 0);
		}else {
			frame.setBounds(480, 180, 0, 0);
		}*/
		frame.setSize(415, 500);
		frame.setLayout(null);
		frame.setTitle("Profile");
		frame.setVisible(true);
		frame.setResizable(false);
		Image img = load.loadImage("resources/images/galaxy1920x1080.jpg","Failed To Load Image");
		frame.setIconImage(img);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		UserProfileEventHandler event = new UserProfileEventHandler(username,loggedInUserID, connect, st);
		changeDescButton.addActionListener(event);
		changePictureButton.addActionListener(event);
	}
	
	public UserProfile(String username, int loggedInUserID, String birthday, URL ImageURL, String userDesc){
		Color invisible = new Color(0,0,0,0); //make JComponents invisible 
		LoadImage load = new LoadImage();
		backgroundLabel = new JLabel(load.loadImageIcon("resources/images/CloudBackground.png", "Failed To Load Image"));

		userDescText.setLineWrap(true);
		userDescText.setEditable(false);
		userDescText.setBackground(invisible);
		userBirthdayLabel = new JLabel("<html><U><span style='color:green'>Birthday: </U></span><html>" + birthday);
		usernameLabel = new JLabel("<html><U><span style='color:green'>Username: </U></span><html>" + username);
		backGroundPanelScrollPane.setViewportView(userDescText);
		
		int stringSizeLimit = 347;
		if(userDesc.length() >=  stringSizeLimit) {
			String itemDescFiltered = userDesc.substring(0,stringSizeLimit) + "...";
			userDescText.setText(itemDescFiltered);
		}else {
			userDescText.setText(userDesc);
		}
		
		//set userProfile Image qualities
		ImageIcon itemImage = new ImageIcon(new ImageIcon(ImageURL).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		itemImageLabel = new JLabel();
		itemImageLabel.setSize(new Dimension(50,50));
		itemImageLabel = new JLabel(itemImage);
		
		itemImageLabel.setBounds(175, 25, 100, 100);
		usernameLabel.setBounds(25, 110, 325, 50);
		userBirthdayLabel.setBounds(25, 160, 150, 50);
		userDescLabel.setBounds(25, 210, 100, 50);
		userDescText.setBounds(136, 229, 250, 140);
		
		//add elements directly to frame
		frame.setContentPane(backgroundLabel);
		frame.add(itemImageLabel);
		frame.add(userBirthdayLabel);
		frame.add(usernameLabel);
		frame.add(userDescLabel);
		frame.add(userDescText);

		//set frame attributes
		/*if(unknownScreenSize) {
			frame.setBounds(320, 110, 0, 0);
		}else {
			frame.setBounds(480, 180, 0, 0);
		}*/
		frame.setSize(415, 500);
		frame.setLayout(null);
		frame.setTitle("Profile");
		frame.setVisible(true);
		frame.setResizable(false);
		load = new LoadImage();
		Image img = load.loadImage("resources/images/galaxy1920x1080.jpg","Failed To Load Image");
		frame.setIconImage(img);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private class UserProfileEventHandler implements ActionListener{
		private String username; // user's username
		private int loggedInUserID;
		Connection connect;
		Statement st;
		
		public UserProfileEventHandler(String username, int loggedInUserID, Connection connect, Statement st) {
			this.username = username;
			this.loggedInUserID = loggedInUserID;
			this.connect = connect;
			this.st = st;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == changePictureButton ) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				new UserProfileChangePicture(username,loggedInUserID, frame, connect, st,itemImageLabel);
			}
			if(event.getSource() == changeDescButton ) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				new UserProfileChangeDesc(username,loggedInUserID, frame, connect, st,itemImageLabel);
			}
		}
		
		
	}
	
}


