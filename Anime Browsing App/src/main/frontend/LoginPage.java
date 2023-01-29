package main.frontend;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import main.backend.LoginPageEventHandler;
import main.utilities.LoadImage;
/**
 * 
 * @author Mohann Scarlett mohannscarlett3@gmail.com
 * @version 1/25/2023
 */
public class LoginPage {
	
	//arrays for objects used in subclass EventHandler 
	private String[] birthMonthOptions = {"January","Febuary","March","April","May","June","July","August","September",
			"October","November","December"};
	private String[] birthDateOptions = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
			"18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String[] birthYearOptions = {"2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997","1996","1995",
			"1994","1993","1992","1991","1990","1989","1988","1987","1986","1985","1984","1983","1982","1981","1980","1979","1978","1977","1976","1975","1974",
			"1973","1972","1971","1970","1969","1968","1967","1966","1965","1964","1963","1962","1961","1960","1959","1958","1957","1956","1955","1954","1953",
			"1952","1951","1950","1949","1948","1947","1946","1945","1944","1943","1942","1941","1940","1939","1938","1937","1936","1935","1934","1933","1932",
			"1931","1930","1929","1928","1927","1926","1925","1924","1923","1922","1921"};
	public static final String emailPattern = 
		    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public final Pattern characterCheck = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
	
	public String loggedInUser; // user's username
	public int loggedInUserID; // user's ID
	public String loggedInUserBirthdate; // user's username
	public URL loggedInUserPictureURL; // user's username
	public String loggedInUserDesc; // user's username

	public Connection connect = null; //user connection
	public Statement st = null; //execute all needed SQL statement on LoginPage
	public boolean unknownScreenSize;
	
	//create initial JFrame and components of JFrame
	public JFrame frame = new JFrame();
	public JLabel usernameLabel = new JLabel("Username");
	public JLabel usernameLabel1 = new JLabel("Username");
	public JLabel passwordLabel = new JLabel("Password");
	public JLabel passwordLabel1 = new JLabel("Password");
	public JTextField loginUsernameField = new JTextField("");
	public JPasswordField loginPasswordField = new JPasswordField("");
	public JButton loginButton = new JButton("Login");
	public JButton signup = new JButton("Sign up");
	public JLabel loginError = new JLabel("Username or password is incorrect");
	public JLabel loginError1 = new JLabel("Username or password is incorrect");
	public Boolean usernamePasswordCombo = false;
	
	//objects used in Subclass EventHandler and used if signup button is clicked
	public JButton backButton = new JButton();
	public JLabel email = new JLabel("Email");
	public JTextField emailField = new JTextField();
	public JLabel usernameLoginLabel = new JLabel("Username");
	public JTextField signupUsernameField = new JTextField();
	public JLabel usernameSpecs = new JLabel("Must be between 4 and 16 characters");
	public JLabel passwordSpecs = new JLabel("Must be more than 5 characters");
	public JLabel emailSpecs = new JLabel();
	public JLabel termsAndPrivacySpecs = new JLabel("You must agree to our terms of service to create an account");
	public JLabel passwordLoginLabel = new JLabel("Password");
	public JPasswordField signupPasswordField = new JPasswordField();
	public JLabel birthday = new JLabel("Birthday");
	public JComboBox<String> birthMonth = new JComboBox<>(birthMonthOptions);
	public JComboBox<String> birthDate = new JComboBox<>(birthDateOptions);
	public JComboBox<String> birthYear = new JComboBox<>(birthYearOptions);
	public JCheckBox terms =  new JCheckBox();
	public JLabel termsText = new JLabel("I have read and agree to the ");
	public JLabel termsText1 = new JLabel("and");
	public JButton termsService = new JButton("<HTML><U>Terms of Service</U></HTML>");
	public JButton termsPrivacy = new JButton("<HTML><U>Privacy Policy</U></HTML>");
	public JButton createAccount = new JButton("<HTML><U>Create Account</U></HTML>");
	public Boolean usernameExists = false;
	public Boolean emailExists = false;
	
	//Items used in Subclass EventHandler and used if "Terms of Service" / "Privacy Policy" label is clicked
    public String textHolder = "";
    public JTextPane termsOfServiceTextPane = new JTextPane();
    public JTextPane privacyPolicyTextPane = new JTextPane();
	public JScrollPane termsOfServiceScrollPanel = new JScrollPane(termsOfServiceTextPane);
	public JScrollPane privacyPolicyScrollPanel = new JScrollPane(privacyPolicyTextPane);
	
	public JLabel animePlanet1;
	public JLabel animePlanet;
	public JLabel galaxyPicture;
	public JLabel animeGirl;
	public JLabel cosmosPicture;
	public ImageIcon arrowPicture;

	//Login page no argument constructor
	public LoginPage () {
		//parameters used to load an image 
		LoadImage load = new LoadImage();
		String errorMessage = "Failed to Load Image";
		String animePlanet1Path = "resources/images/ani-planet241x41.png";
		String animePlanetPath = "resources/images/ani-planet391x99.png";
		String galaxyPicturePath = "resources/images/galaxy1920x1080.jpg";
		String hanakoPicturePath = 	"resources/images/Hanako.png";
		String cosmosPicturePath = 	"resources/images/cosmos.png";
		String arrowPicturePath = 	"resources/images/arrow.png";

		//all home page images that need to be displayed
		animePlanet1 = load.loadImageIntoJLabel(animePlanet1Path, errorMessage );
		animePlanet = load.loadImageIntoJLabel(animePlanetPath, errorMessage );
		galaxyPicture = load.loadImageIntoJLabel(galaxyPicturePath,errorMessage );
		animeGirl = load.loadImageIntoJLabel(hanakoPicturePath, errorMessage );
		cosmosPicture = load.loadImageIntoJLabel(cosmosPicturePath, errorMessage );
		arrowPicture = load.loadImageIcon(arrowPicturePath,errorMessage );

		//set the size, position, and qualities of GUI items
		usernameLabel.setBounds(300, 200, 200, 100);
		usernameLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		usernameLabel.setForeground(Color.black);
		
		usernameLabel1.setBounds(302, 200, 200, 100);
		usernameLabel1.setFont(new Font("Verdana", Font.BOLD, 25));
		usernameLabel1.setForeground(Color.red);
		
		loginUsernameField.setBounds(445, 234, 200, 35);
		loginUsernameField.setFont(new Font("Verdana", Font.PLAIN, 15));
		loginUsernameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		loginUsernameField.setFont(new Font("Verdana", Font.BOLD, 14));
		
		passwordLabel.setBounds(300, 290, 200, 100);
		passwordLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		passwordLabel.setForeground(Color.black);
		
		passwordLabel1.setBounds(302, 290, 200, 100);
		passwordLabel1.setFont(new Font("Verdana", Font.BOLD, 25));
		passwordLabel1.setForeground(Color.red);
		
		loginPasswordField.setBounds(445, 324, 200, 35);
		loginPasswordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		loginButton.setBounds(300, 420, 346, 35);
		loginButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.red);
		loginButton.setFocusable(false);
		
		signup.setBounds(300, 460, 346, 35);
		signup.setFont(new Font("Verdana", Font.PLAIN, 15));
		signup.setBackground(Color.green);
		signup.setFocusable(false);
		
		loginError.setBounds(319, 325, 345, 120);
		loginError.setFont(new Font("Verdana", Font.BOLD, 17));
    	loginError.setForeground(Color.white);
    	
    	loginError1.setBounds(321, 325, 345, 120);
		loginError1.setFont(new Font("Verdana", Font.BOLD, 17));
    	loginError1.setForeground(Color.red);
		
		galaxyPicture.setBounds(0, 0, 952, 650);
		
		animePlanet.setBounds(300, 60, 391, 100);
		
		//add items to login page JFrame
		frame.add(loginError);
		frame.add(loginError1);
		loginError.setVisible(false);
		loginError1.setVisible(false);
		frame.add(animePlanet);
		frame.add(usernameLabel);
		frame.add(usernameLabel1);
		frame.add(loginUsernameField);
		frame.add(passwordLabel);
		frame.add(passwordLabel1);
		frame.add(loginPasswordField);
		frame.add(loginButton);
		frame.add(signup);
		frame.add(galaxyPicture);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	    int width = gd.getDisplayMode().getWidth();
	    int height = gd.getDisplayMode().getHeight();
		//change qualities of login JFrame
		if(width == 1920 && height == 1080 && java.awt.Toolkit.getDefaultToolkit().getScreenResolution() == 96) {
			frame.setLocation(1920/4, 120);
			unknownScreenSize = false;
	    }else if(width == 1920 && height == 1080 && java.awt.Toolkit.getDefaultToolkit().getScreenResolution() == 120){
	    	frame.setLocation(1920/6, 95);
	    	unknownScreenSize = true;
	    }else {
	    	unknownScreenSize = true;
	    	frame.setLocationRelativeTo(null);
	    }
		frame.setSize(950, 650);
		frame.setLayout(null);
		frame.setTitle("                                                                     "
				+ "                                                  Ani-Planet");
		frame.setVisible(true);
		frame.setResizable(false);
		Image frameImage = load.loadImage("resources/images/galaxy1920x1080.jpg", errorMessage);
		frame.setIconImage(frameImage);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","SYSTEM");
			st = connect.createStatement();	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to Connect to Database" + e.toString());
			return;
		}

		//enable GUI objects to interface with user when events occur using EventHandler class
		LoginPageEventHandler eventHandler = new LoginPageEventHandler(this);
		loginButton.addActionListener(eventHandler);
		signup.addActionListener(eventHandler);
		termsService.addActionListener(eventHandler);
		termsPrivacy.addActionListener(eventHandler);
		backButton.addActionListener(eventHandler);
		createAccount.addActionListener(eventHandler);
		loginPasswordField.addActionListener(eventHandler);
	}
}//LoginPage Class