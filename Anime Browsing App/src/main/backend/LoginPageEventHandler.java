package main.backend;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import main.frontend.ConnectionFrame;
import main.frontend.LoginPage;
import main.utilities.FileLoader;
import main.utilities.LoadImage;
/**
 * 
 * @author Mohann Scarlett mohannscarlett3@gmail.com
 * @version 1/25/2023
 */
public class LoginPageEventHandler implements ActionListener{
		LoginPage loginPage;
		public LoginPageEventHandler(LoginPage loginPage) {
			this.loginPage = loginPage;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			//IF LOGIN BUTTON IS CLICKED DO ACTIONS SPECIFED BELOW
			if(event.getSource() == loginPage.loginButton || event.getSource() == loginPage.loginPasswordField ) {
				
				//if any of the info fields are blank prompt the user to enter input
				if(loginPage.loginUsernameField.getText().equals("") || String.valueOf(loginPage.loginPasswordField.getPassword()).equals("")) {
	                UIManager.put("OptionPane.background", Color.white);
	                UIManager.put("Panel.background", Color.white);
	                JOptionPane.showMessageDialog(null,"Please enter a username AND password","", JOptionPane.INFORMATION_MESSAGE);
	                return;
	                
	            } else if(userAuthentication(loginPage.loginUsernameField.getText().replaceAll("\s+","").toLowerCase())) { //if user exists in databse with correct credentials, let them into app
	            	loginPage.frame.dispatchEvent(new WindowEvent(loginPage.frame, WindowEvent.WINDOW_CLOSING)); 
	            	try{ 
	        			ConnectionFrame loadingPage = new ConnectionFrame(loginPage,loginPage.unknownScreenSize);
	        			loadingPage.execute();

	            		}catch(Exception e) {
	            			System.out.println(e);
	        				String query = "INSERT INTO application_errors (error_message) VALUES ('"+e+"')";
	        				try {
	        					loginPage.st.executeUpdate(query);
	        				} catch (SQLException e1) {
	        					System.out.println(e1);
	        					}
	            			}
	            	return;
	            }else {
	            	loginPage.loginError.setVisible(true);	
	            	loginPage.loginError1.setVisible(true);
	            	return;
	            }
			}
			
			//IF THE SIGN UP BUTTON IS CLICKED ON LOGIN PAGE, OPEN SIGNUP PAGE TO LET USER MAKE A NEW ACCOUNT
			if(event.getSource() == loginPage.signup) {
				//remove all items from the JFrame
				loginPage.frame.getContentPane().removeAll();
				loginPage.frame.repaint();
				loginPage.frame.validate();
				
				//Set size, position, and qualities of objects used in signup page frame
				loginPage.backButton.setBounds(0, 0, 50, 25);
				
				loginPage.email.setBounds(20, 40, 120, 120);
				loginPage.email.setFont(new Font("Verdana", Font.PLAIN, 17));
				loginPage.email.setForeground(Color.white);
				
				loginPage.emailField.setBounds(20, 120, 275, 30);
				loginPage.emailField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				loginPage.emailField.setFont(new Font("Verdana", Font.BOLD, 14));
				
				loginPage.usernameLoginLabel.setBounds(20, 120, 120, 120);
				loginPage.usernameLoginLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
				loginPage.usernameLoginLabel.setForeground(Color.white);
				
				loginPage.signupUsernameField.setBounds(20, 200, 275, 30);
				loginPage.signupUsernameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				loginPage.signupUsernameField.setFont(new Font("Verdana", Font.BOLD, 14));
				
				loginPage.usernameSpecs.setBounds(20, 177, 325, 120);
				loginPage.usernameSpecs.setFont(new Font("Verdana", Font.BOLD, 12));
				loginPage.usernameSpecs.setForeground(Color.green);

				loginPage.passwordSpecs.setBounds(20, 257, 250, 120);
				loginPage.passwordSpecs.setFont(new Font("Verdana", Font.BOLD, 12));
				loginPage.passwordSpecs.setForeground(Color.green);
				
				loginPage.emailSpecs.setBounds(20, 97, 250, 120);
				loginPage.emailSpecs.setFont(new Font("Verdana", Font.BOLD, 12));
				loginPage.emailSpecs.setForeground(Color.red);
				
				loginPage.termsAndPrivacySpecs.setBounds(25, 439, 400, 30);
				loginPage.termsAndPrivacySpecs.setFont(new Font("Verdana", Font.PLAIN, 12));
				loginPage.termsAndPrivacySpecs.setForeground(Color.red);
				
				loginPage.passwordLoginLabel.setBounds(20, 200, 120, 120);
				loginPage.passwordLoginLabel.setFont(new Font("Verdana", Font.PLAIN, 17));
				loginPage.passwordLoginLabel.setForeground(Color.white);
				
				loginPage.signupPasswordField.setBounds(20, 280, 275, 30);
				loginPage.signupPasswordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				
				loginPage.birthday.setBounds(20, 280, 120, 120);
				loginPage.birthday.setFont(new Font("Verdana", Font.PLAIN, 17));
				loginPage.birthday.setForeground(Color.white);
				
				loginPage.birthMonth.setBounds(20, 360, 87, 30);
				
				loginPage.birthDate.setBounds(110, 360, 90, 30);
				
				loginPage.birthYear.setBounds(203, 360, 90, 30);
				
				loginPage.createAccount.setBounds(57, 480, 200, 30);
				loginPage.createAccount.setBackground(Color.green);
				loginPage.createAccount.setFocusPainted(false);
				loginPage.createAccount.setForeground(Color.white);
				
				loginPage.animeGirl.setBounds(0, 0, 952, 650);
				
				loginPage.cosmosPicture.setBounds(0, 0, 951, 650);
				
				loginPage.terms.setBounds(20, 420, 20, 30);
				loginPage.terms.setOpaque(false);
				loginPage.terms.setContentAreaFilled(false);
				loginPage.terms.setBorderPainted(false);
				loginPage.terms.setForeground(Color.white);
				loginPage.terms.setFocusable(false);
				
				loginPage.termsText.setBounds(40, 420, 165, 30);
				loginPage.termsText.setForeground(Color.white);
				
				loginPage.termsText1.setBounds(302, 420, 165, 30);
				loginPage.termsText1.setForeground(Color.white);
				
				loginPage.termsService.setBounds(175, 420, 150, 30);	
				loginPage.termsService.setForeground(Color.cyan);
				loginPage.termsService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				loginPage.termsService.setBorderPainted(false);
				loginPage.termsService.setOpaque(false);
				loginPage.termsService.setContentAreaFilled(false);
				
				loginPage.termsPrivacy.setBounds(290, 425, 150, 20);
				loginPage.termsPrivacy.setForeground(Color.cyan);
				loginPage.termsPrivacy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				loginPage.termsPrivacy.setBorderPainted(false);
				loginPage.termsPrivacy.setOpaque(false);
				loginPage.termsPrivacy.setContentAreaFilled(false);
				
				loginPage.animePlanet.setBounds(0, 0, 391, 100);
				
				//adding image to backButton
				LoadImage load = new LoadImage();
				loginPage.backButton.setIcon(loginPage.arrowPicture); 

				//add GUI objects to frame
				loginPage.frame.add(loginPage.emailSpecs);
				loginPage.emailSpecs.setVisible(false);
				loginPage.frame.add(loginPage.termsAndPrivacySpecs);
				loginPage.termsAndPrivacySpecs.setVisible(false);
				loginPage.frame.add(loginPage.passwordSpecs);
				loginPage.frame.add(loginPage.createAccount);
				loginPage.frame.add(loginPage.termsService);
				loginPage.frame.add(loginPage.termsPrivacy);
				loginPage.frame.add(loginPage.terms);
				loginPage.frame.add(loginPage.termsText);
				loginPage.frame.add(loginPage.termsText1);
				loginPage.frame.add(loginPage.backButton);
				loginPage.frame.add(loginPage.email);
				loginPage.frame.add(loginPage.emailField);
				loginPage.frame.add(loginPage.usernameLoginLabel);
				loginPage.frame.add(loginPage.signupUsernameField);
				loginPage.frame.add(loginPage.passwordLoginLabel);
				loginPage.frame.add(loginPage.signupPasswordField);
				loginPage.frame.add(loginPage.birthday);
				loginPage.frame.add(loginPage.usernameSpecs);
				loginPage.frame.add(loginPage.birthMonth);
				loginPage.frame.add(loginPage.birthDate);
				loginPage.frame.add(loginPage.birthYear);
				
				//add GUi image objects
				loginPage.frame.add(loginPage.animePlanet);
				loginPage.frame.add(loginPage.animeGirl);
				loginPage.frame.add(loginPage.cosmosPicture);
				
				//fix any frame visibility problems
				loginPage.frame.setResizable(false);
				loginPage.frame.setVisible(true);				
			}
			//IF CREATE ACCOUNT BUTTON ON SIGNUP PAGE IS CLICKED
			if(event.getSource() == loginPage.createAccount) {

				/* if usernameLoginLabel is between 4 & 16 characters, passwordLoginLabel is more than 5 characters, email is a valid email by means of emailPatter, terms of service checkbox is checked,
				 * usernameLoginLabel does not have any spaces or special characters, usernameLoginLabel doesn't already exist, email doesn't already exist,
				 * then allow user to create an account ( write their information to specified files to be held ) */
				if(loginPage.signupUsernameField.getText().length() <= 16 &&
						loginPage.signupUsernameField.getText().length() >= 4 &&
						String.valueOf(loginPage.signupPasswordField.getPassword()).length() > 5 &&
						loginPage.emailField.getText().matches(loginPage.emailPattern) && 
						loginPage.terms.isSelected() &&
						((loginPage.signupUsernameField.getText().matches("\\S+") && (loginPage.signupUsernameField.getText().length() > 0)) && !loginPage.characterCheck.matcher(loginPage.signupUsernameField.getText()).find()) &&
						!userExists(loginPage.signupUsernameField.getText().replaceAll("\s+","")) &&
						!emailExists(loginPage.emailField.getText().replaceAll("\s+",""))) {
					new LoginPage();
					loginPage.frame.dispatchEvent(new WindowEvent(loginPage.frame, WindowEvent.WINDOW_CLOSING));
					//create a new user account
					createUserAccount(loginPage.signupUsernameField.getText().replaceAll("\s+","").toLowerCase());

				}else {
				//if usernameLoginLabel input on signup page is more than 16 characters/less than 4 characters, tell the user to fix it via JLabel, otherwise set JLabel back to original state
				if(loginPage.signupUsernameField.getText().length() > 16 || loginPage.signupUsernameField.getText().length() < 4) {
					loginPage.usernameSpecs.setForeground(Color.red);
				} else if (loginPage.signupUsernameField.getText().length() <= 16 || loginPage.signupUsernameField.getText().length() >= 4) {
					loginPage.usernameSpecs.setForeground(Color.green);
				}
				//if usernameLoginLabel on signup page has a space character or a special character, tell the user to fix it via JLabel, otherwise set JLabel back to original state
				if ((!loginPage.signupUsernameField.getText().matches("\\S+") && (loginPage.signupUsernameField.getText().length() > 0)) || loginPage.characterCheck.matcher(loginPage.signupUsernameField.getText()).find()){
					loginPage.usernameSpecs.setText("Username can not contain special characters");
					loginPage.usernameSpecs.setForeground(Color.red);
				} else {
					loginPage.usernameSpecs.setText("Must be between 4 and 16 characters");
				}
				//if passwordLoginLabel on signup page is less than 6 characters, tell user to fix it via JLabel, otherwise set JLabel back to originals state
				if(String.valueOf(loginPage.signupPasswordField.getPassword()).length() < 6) {
					loginPage.passwordSpecs.setForeground(Color.red);
				} else if (String.valueOf(loginPage.signupPasswordField.getPassword()).length() >= 6) {
					loginPage.passwordSpecs.setForeground(Color.green);
				}
				/*if email on signup page is not arranged as a valid email ( checked with emailPattern defined in LoginPage class), tell user to fix it via JLabel
				otherwise if email already exists, tell user to use a different email via JLabel
				otherwise set JLabel beneath email field to visible(false); */
				if(!loginPage.emailField.getText().matches(LoginPage.emailPattern)) { 
					loginPage.emailSpecs.setText("Not a valid email");
					loginPage.emailSpecs.setVisible(true);
				}else if(emailExists(loginPage.emailField.getText().replaceAll("\s+",""))) {
					loginPage.emailSpecs.setText("Email already in use");
					loginPage.emailSpecs.setVisible(true);
					loginPage.emailExists = false;
				} else {
					loginPage.emailSpecs.setVisible(false);
				}
				//if terms checkbox is not selected, tell user via JLabel( currently visible(false); ) to check it, otherwise set JLabel back to original state
				if(!loginPage.terms.isSelected()) {
					loginPage.termsAndPrivacySpecs.setVisible(true);
				} else {
					loginPage.termsAndPrivacySpecs.setVisible(false);
				}
				//if usernameLoginLabel already exists, tell user to choose another usernameLoginLabel and set usernameLoginLabel exists false
				if(userExists(loginPage.signupUsernameField.getText().replaceAll("\s+",""))) {
					loginPage.usernameSpecs.setText("Username already in use");
					loginPage.usernameSpecs.setForeground(Color.red);
					loginPage.usernameExists = false;
				}
			}
			}
			//IF RED ARROW (BACK BUTTON) IS CLICKED 
			if(event.getSource() == loginPage.backButton) {
				new LoginPage();
				loginPage.frame.dispatchEvent(new WindowEvent(loginPage.frame, WindowEvent.WINDOW_CLOSING));
			}
			//IF TERMS OF SERVICE LABEL IS CLICKED ON SIGNUP PAGE
			if(event.getSource() == loginPage.termsService) {	
				loginPage.frame.getContentPane().removeAll();
				loginPage.frame.validate();
				loginPage.frame.repaint();
				
				//try to get fetch file at the location of the specified path and store it into string "textHolder"
				FileLoader load = new FileLoader();
				loginPage.textHolder = load.txtToString("resources/textfiles/termsofService.txt");

				//set preferred sizes of objects used in frame
				loginPage.animePlanet1.setPreferredSize(new Dimension(800,25));
			    loginPage.backButton.setPreferredSize(new Dimension(50,25));
				loginPage.termsOfServiceTextPane.setPreferredSize(new Dimension(914,570));
				//set qualities of objects used in frame 
				loginPage.termsOfServiceTextPane.setText(loginPage.textHolder);
				loginPage.termsOfServiceTextPane.setEditable(false);
				loginPage.termsOfServiceScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				//add objects to frame
				loginPage.frame.add(loginPage.backButton);
				loginPage.frame.add(loginPage.animePlanet1);
				//set qualities of frame
				loginPage.frame.getContentPane().add(loginPage.termsOfServiceScrollPanel);
				loginPage.frame.setLayout(new FlowLayout(FlowLayout.LEFT));
				loginPage.frame.setResizable(false);
				loginPage.frame.setVisible(true);
			}
			//IF PRIVACY POLICY LABEL IS CLICKED ON SIGNUP PAGE
			if(event.getSource() == loginPage.termsPrivacy) {
				loginPage.frame.getContentPane().removeAll();
				loginPage.frame.validate();
				loginPage.frame.repaint();
				
				 //try to get fetch file at the location of the specified path and store it into string "textHolder"
				 FileLoader load = new FileLoader();
				 loginPage.textHolder = load.txtToString("resources/textfiles/privacyPolicy.txt");

				 //set preferred sizes of objects used in frame
				 loginPage. animePlanet1.setPreferredSize(new Dimension(800,25));
				 loginPage.backButton.setPreferredSize(new Dimension(50,25));
				 loginPage. privacyPolicyTextPane.setPreferredSize(new Dimension(914,570));
				 //set qualities of objects used in frame 
				 loginPage. privacyPolicyTextPane.setText(loginPage.textHolder);
				 loginPage.privacyPolicyTextPane.setEditable(false);
				 loginPage.privacyPolicyScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				 //add objects to frame
				 loginPage.frame.add(loginPage.backButton);
				 loginPage.frame.add(loginPage.animePlanet1);
				 //set qualities of frame
				 loginPage.frame.getContentPane().add(loginPage.privacyPolicyScrollPanel);
				 loginPage.frame.setLayout(new FlowLayout(FlowLayout.LEFT));
				 loginPage.frame.setResizable(false);
				 loginPage.frame.setVisible(true);
			}
			//reset scroll bar on Privacy Policy page & terms of service page back to the top
			SwingUtilities.invokeLater(new Runnable() {
			     public void run() {
			    	 loginPage.termsOfServiceScrollPanel.getViewport().setViewPosition(new Point(0, 0));
			    	 loginPage. privacyPolicyScrollPanel.getViewport().setViewPosition(new Point(0, 0));
			   }
			});//swingUtilities Method
		}//actionPerformed Method
		
		//Encrypts passwordLoginLabel string
		private void createUserAccount(String username) {
			try {
				//insert new user into database assuming they do not exist
				String insertUserSQL = "INSERT INTO user_identification (username) VALUES ('"+username+"')";
				loginPage.st.executeUpdate(insertUserSQL);
				
				//get the userID of the user with the input username
				String getUserIDSQL = "SELECT get_userID('"+username+"') FROM dual";
				ResultSet rs = loginPage.st.executeQuery(getUserIDSQL);
				rs.next();
				int userID = Integer.parseInt(rs.getString("get_userID('"+username+"')"));
				
				//Insert login credentials into appropriate table (user's id and password hash)
				Base64.Encoder enc = Base64.getEncoder();
				String encodedPassword = enc.encodeToString(String.valueOf(loginPage.signupPasswordField.getPassword()).getBytes());
				String insertPassSQL = "INSERT INTO login_credentials (userID,u_password) VALUES ("+userID+",'"+encodedPassword+"')";
				loginPage.st.executeUpdate(insertPassSQL);
				
				//insert user information into appropriate table
				String userBirthday = loginPage.birthMonth.getSelectedItem().toString() + " " + loginPage.birthDate.getSelectedItem().toString() + ", " + loginPage.birthYear.getSelectedItem().toString();
				String userDefaultImage = "https://img.freepik.com/free-icon/user_318-790139.jpg?w=2000";
				String userDefaultDesc = "No description added yet";
				String insertUserInfoSQL = "INSERT INTO user_information (userID,email,birthdate,picture_link,user_desc) VALUES ("+userID+",'"+loginPage.emailField.getText()+"','"+userBirthday+"','"+userDefaultImage+"','"+userDefaultDesc+"')";                                     
				loginPage.st.executeUpdate(insertUserInfoSQL);
				
			}catch (Exception e){
				String query = "INSERT INTO application_errors (error_message) VALUES ('"+e+"')";
				try {
					loginPage.st.executeUpdate(query);
				} catch (SQLException e1) {
					e.printStackTrace();;
				}
				JOptionPane.showMessageDialog(null, "Failed to Create Account, Please Try Again");
			}
		}
		private boolean emailExists(String s){
			try {

				//check if user exists within databsae
				String selectEmailSQL = "SELECT email FROM user_information WHERE email = '"+s+"'";
				ResultSet rs =  loginPage.st.executeQuery(selectEmailSQL);
				rs.next();
				String email = rs.getString("email");
				return true;
			}catch(Exception e) {
				String query = "INSERT INTO application_errors (error_message) VALUES ('"+e+"')";
				try {
					loginPage.st.executeUpdate(query);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Failed to Access Database");
				}
				return false;
			}
		}
		private boolean userAuthentication(String username){
			try {
				if(userExists(username)) {
					//get the userID of the user with the input username from database
					String getUserIDSQL = "SELECT get_userID('"+username+"') FROM dual";
					ResultSet rs = loginPage.st.executeQuery(getUserIDSQL);
					rs.next();
					int userID = Integer.parseInt(rs.getString("get_userID('"+username+"')"));
					
					String selectPassHash = "SELECT u_password FROM login_credentials WHERE userID = '"+userID+"'";
					ResultSet hashrs =  loginPage.st.executeQuery(selectPassHash);
					hashrs.next();
					Base64.Encoder enc = Base64.getEncoder();
					String encodedPassword = enc.encodeToString(String.valueOf(loginPage.loginPasswordField.getPassword()).getBytes());
					
					if(encodedPassword.equals(hashrs.getString("u_password"))) {
						loginPage.loggedInUser = username;
						loginPage.loggedInUserID = userID;
						
						//getbirthday  of the user with the input username from database
						String getUserbirthdate = "SELECT birthdate FROM user_information WHERE userID ='"+userID+"'";
						ResultSet birthdateRS = loginPage.st.executeQuery(getUserbirthdate);
						birthdateRS.next();
						loginPage.loggedInUserBirthdate = birthdateRS.getString("birthdate");
						
						//get picture URL  of the user with the input username from database
						String getUserPictureURL = "SELECT picture_link FROM user_information WHERE userID ='"+userID+"'";
						ResultSet PictureRS = loginPage.st.executeQuery(getUserPictureURL);
						PictureRS.next();
						loginPage.loggedInUserPictureURL = new URL(PictureRS.getString("picture_link"));
						
						//get  desc of the user with the input username from database
						String getUserDesc = "SELECT user_desc FROM user_information WHERE userID ='"+userID+"'";
						ResultSet DescRS = loginPage.st.executeQuery(getUserDesc);
						DescRS.next();
						loginPage.loggedInUserDesc = DescRS.getString("user_desc");
						return true;
					}
				}
			}catch (Exception e){
				JOptionPane.showMessageDialog(null,"Failed to Connect to Database");
				String query = "INSERT INTO application_errors (error_message) VALUES ('"+e+"')";
				try {
					loginPage.st.executeUpdate(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return false;
		}//UserAuthentication Method
		private boolean userExists(String s){
			try {

				//check if user exists within databsae
				String selectUsernameSQL = "SELECT username FROM user_identification WHERE username = '"+s+"'";
				ResultSet rs =  loginPage.st.executeQuery(selectUsernameSQL);
				rs.next();
				String user = rs.getString("username");
				return true;
			}catch(Exception e) {
				return false;
			}
		}//user exists method
	}//EventHandler Class