package main.frontend;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import main.utilities.LoadImage;
/**
 * 
 * @author Mohann Scarlett mohannscarlett3@gmail.com
 * @version 1/25/2023
 */
public class HomePage {
	
	private JFrame frame = new JFrame();
	//holds CSV file information
	private List<String[]> list;
	private List<String[]> listRanked;
	private List<String[]> listPopularity;
	private List<String[]> listEpisode;

	//background Panel
	private JPanel backgroundPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JScrollPane backgroundScrollPane = new JScrollPane();
	//Logo
	private JLabel animePlanet;
	//Navigation Panels and their objects
	private JPanel navigationPanel = new JPanel();
	private JPanel navigationPanel1 = new JPanel();
	private JTextField searchBar = new JTextField();
	private JButton searchButton;
	private JButton animeButton = new JButton("<HTML><U>Anime</U></HTML>");
	private JButton communityButton = new JButton("<HTML><U>Community</U></HTML>");
	private JButton helpButton = new JButton("<HTML><U>Help</U></HTML>");
	private JButton supportUsButton = new JButton("<HTML><U>Support Us</U></HTML>");
	private JLabel displayUsername;
	private String[] comboboxOptions = {"SELECT PROFILE OPTION","Profile", "Logout", "Delete Account"}; 
	private JComboBox<String> profileOptions = new JComboBox<String>(comboboxOptions);

	//popularPanel and its objects/variables
	private JPanel popularPanel = new JPanel();
	private int popularCount = 0; //holds the index of what image to load next in popularPanel
    private JPanel mostPopularTitlePanel = new JPanel();
    private JPanel mostPopularTitlePanel1 = new JPanel();
    private JLabel mostPopularSectionTitle = new JLabel("Most Popular");
    //images to be added to popular panels anime cards
	private BufferedImage img;
	private BufferedImage img1;
	private BufferedImage img2;
	private BufferedImage img3;
	private BufferedImage img4;
	private JButton popularRightSlider;
	private JPanel leftSliderHolder = new JPanel();
	private JPanel rightSliderHolder = new JPanel();
	private JButton popularLeftSlider;
	private JLabel leftSliderText = new JLabel("<HTML><U>Prior\nPage</U></HTML>");
	private JLabel rightSliderText = new JLabel("<HTML><U>Next\nPage</U></HTML>");

    //animeMoviesPanel and its objects/variables
    private JPanel animeMoviesPanel = new JPanel();
    private JPanel animeMoviesTitlePanel = new JPanel();
    private JLabel animeMoviesSectionTitle = new JLabel("Top Anime Movies");
    private JPanel animeMoviesHolder = new JPanel();
    private JPanel animeMoviesHolder1 = new JPanel();
    private JPanel animeMoviesHolder2 = new JPanel();
    private JPanel animeMoviesHolder3 = new JPanel();
    private JPanel animeMoviesHolder4 = new JPanel();
    private JButton animeMovies;
    private JButton animeMovies1;
    private JButton animeMovies2;
    private JButton animeMovies3;
    private JButton animeMovies4;
    private JButton animeMoviesAnimeName = new JButton("Kimi no Na wa");
    private JButton animeMoviesAnimeName1 = new JButton("Koe no Katachi");
    private JButton animeMoviesAnimeName2 = new JButton("Sen to Chihiro no Kamikakushi");
    private JButton animeMoviesAnimeName3 = new JButton("Mononoke-hime");
    private JButton animeMoviesAnimeName4 = new JButton("Howl no Ugoku Shiro");
	
    //top rated panel and its objects/variables
	private JPanel topRatedPanel = new JPanel();
	private JPanel topRatedTitlePanel = new JPanel();
	private JPanel topRatedTitlePanel1 = new JPanel();
	private int ratedCount = 0; //holds the index of what image to load next in topRatedPanel
	//private JButton topRatedSeeMore = new JButton("<HTML><U>View More</U></HTML>");
    private JLabel animeRatedSectionTitle = new JLabel("Top Rated");
	private BufferedImage image;
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage image3;
	private BufferedImage image4;
	private BufferedImage image5;
	private BufferedImage image6;
	private BufferedImage image7;
	private BufferedImage image8;
	private BufferedImage image9;
	private BufferedImage image10;
	private BufferedImage image11;
	private BufferedImage image12;
	private BufferedImage image13;
	private BufferedImage image14;
	private BufferedImage image15;
	JButton crown;
	private JButton ratedRightSlider;
	private JLabel rightSliderText1 = new JLabel("<HTML><U>Next\nPage</U></HTML>");
	private JButton ratedLeftSlider;
	private JLabel leftSliderText1 = new JLabel("<HTML><U>Prior\nPage</U></HTML>");
    private JPanel spaceHolder = new JPanel();
    
    //animeEpisodePanel and its objects/variables
	private JPanel animeEpisodePanel = new JPanel();
	private JPanel animeEpisodeTitlePanel = new JPanel();
	private JPanel animeEpisodeTitlePanel1 = new JPanel();
	private int episodeCount = 1; //holds the index of what image to load next in animeEpisodePanel
	//private JButton animeEpisodeSeeMore = new JButton("<HTML><U>View More</U></HTML>");
    private JLabel animeEpisodeSectionTitle = new JLabel("Most Episodes");
    private BufferedImage bImage;
    private BufferedImage bImage1;
    private BufferedImage bImage2;
    private BufferedImage bImage3;
    private BufferedImage bImage4;
    private BufferedImage bImage5;
	private JButton episodeLeftSlider;
	private JButton episodeRightSlider;
	private JPanel episodeleftSliderHolder = new JPanel();
	private JPanel episoderightSliderHolder = new JPanel();

	private JLabel episodeLeftSliderText = new JLabel("<HTML><U>Prior\nPage</U></HTML>");
	private JLabel episodeRightSliderText = new JLabel("<HTML><U>Next\nPage</U></HTML>");
    JPanel episodeFiller = new JPanel();

    
    //moviesPanel anime titles object list
	private ArrayList<JButton> moviesAnimeTitleList = new ArrayList<>();

	//popularPanel anime object list
	private ArrayList<JButton> popularTitleList = new ArrayList<>();
	private ArrayList<JButton> popularIconList = new ArrayList<>();
	private ArrayList<BufferedImage> popularImageList = new ArrayList<>();
	
	//ratedPanel anime object list
	private ArrayList<JButton> ratedTitleList = new ArrayList<>();
	private ArrayList<JButton> ratedIconList = new ArrayList<>();
	private ArrayList<JLabel> ratedLabelList = new ArrayList<>();
	private ArrayList<BufferedImage> ratedImageList = new ArrayList<>();
	
	//episode anime object list
	private ArrayList<JButton> episodeTitleList = new ArrayList<>();
	private ArrayList<JButton> episodeIconList = new ArrayList<>();
	private ArrayList<JLabel> episodeLabelList = new ArrayList<>();
	private ArrayList<BufferedImage> episodeImageList = new ArrayList<>();
		
    //animeInfoPanel and its objects/variables
	private JPanel animeInfoPanel = new JPanel();
	//infoPanel and its objects/variables
	private JPanel infoPanel = new JPanel();
    
	private String username; // user's username
	private int loggedInUserID;
	private String loggedInUserBirthdate; // user's username
	private URL loggedInUserPictureURL; // user's username
	private String loggedInUserDesc; // user's username
	
	Connection connect;//connection to oracle database
	Statement st; // oracle datbase statements
	
	boolean unknownScreenSize;

	public HomePage (LoginPage loginPage,ArrayList<String[]> list, ArrayList<String[]> listRanked,
			ArrayList<String[]> listPopularity, ArrayList<String[]> listEpisode,ConnectionFrame loadingFrame) throws MalformedURLException, IOException{
			
			this.username = loginPage.loggedInUser;
			this.loggedInUserID = loginPage.loggedInUserID;
			this.loggedInUserBirthdate = loginPage.loggedInUserBirthdate;
			this.loggedInUserPictureURL = loginPage.loggedInUserPictureURL;
			this.loggedInUserDesc = loginPage.loggedInUserDesc;
			this.connect = loginPage.connect;
			this.st = loginPage.st;
			this.unknownScreenSize =  loginPage.unknownScreenSize;
			
			//info to load all HomePage Anime TV show cards
			this.list = list;
			this.listRanked = listRanked;
			this.listPopularity = listPopularity;
			this.listEpisode =  listEpisode;

			
			if(unknownScreenSize){
				frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
		    }else {
		    	frame.setLocation(1920/10, 50);
				frame.setSize(new Dimension(1550,950));
		    }
			
			//parameters needed for image initialization
			String errorMessage = "Failed to Load Image";
			LoadImage load = new LoadImage();
			
			//set qualities and behavior of JFrame			
			//change qualities of homepage JFrame based on monitor
			Image frameImage = load.loadImage("resources/images/galaxy1920x1080.jpg", errorMessage);
			frame.setIconImage(frameImage);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			//initialize all images that need to be used in HomePage
		    animePlanet = new JLabel(load.loadImageIcon("resources/Images/ani-planet391x99.png", errorMessage));
		    searchButton =  new JButton(load.loadImageIcon("resources/Images/magnifyingGlass2.png",errorMessage));
		    popularRightSlider = new JButton(load.loadImageIcon("resources/Images/rightslider.png",errorMessage));
		    popularLeftSlider = new JButton(load.loadImageIcon("resources/Images/leftslider.png",errorMessage));
		    ratedLeftSlider = new JButton(load.loadImageIcon("resources/Images/leftslider.png",errorMessage));
		    ratedRightSlider = new JButton(load.loadImageIcon("resources/Images/rightslider.png",errorMessage));
			episodeLeftSlider = new JButton(load.loadImageIcon("resources/images/leftslider.png", errorMessage));
			episodeRightSlider = new JButton(load.loadImageIcon("resources/images/rightslider.png", errorMessage));
		    animeMovies = new JButton(load.loadImageIcon("resources/Images/KIMI.png",errorMessage));
		    animeMovies1 = new JButton(load.loadImageIcon("resources/Images/SPIRIT.png",errorMessage));
		    animeMovies2 = new JButton(load.loadImageIcon("resources/Images/KOE.png",errorMessage));
		    animeMovies3 = new JButton(load.loadImageIcon("resources/Images/MONONOKE.png",errorMessage));
		    animeMovies4 = new JButton(load.loadImageIcon("resources/Images/HOWL.png",errorMessage));
			crown = new JButton(load.loadImageIcon("resources/Images/crown.png",errorMessage));

			//add movies panel anime titles to list
			moviesAnimeTitleList.add(animeMoviesAnimeName); moviesAnimeTitleList.add(animeMoviesAnimeName1);
			moviesAnimeTitleList.add(animeMoviesAnimeName2); moviesAnimeTitleList.add(animeMoviesAnimeName3);
			moviesAnimeTitleList.add(animeMoviesAnimeName4);
			
			//add popular panel anime cards to list
		    for(int i = 0; i < 5; i++) {
		    	popularIconList.add(new JButton());
		    }
		    popularIconList.get(0).requestFocus(true);
		    
			//add popular panel anime titles to list
		    for(int i = 0; i < 5; i++) {
		    	popularTitleList.add(new JButton());
		    }
		    
			//add popular panel anime img to list
			popularImageList.add(img);
			popularImageList.add(img1); popularImageList.add(img2);
			popularImageList.add(img3); popularImageList.add(img4);
			
			//add rated panel anime cards to list
		    for(int i = 0; i < 16; i++) {
		    	ratedIconList.add(new JButton());
		    }
		    
			//add rated panel anime titles to list
		    for(int i = 0; i < 16; i++) {
		    	ratedTitleList.add(new JButton());
		    }
		
			//add rated panel anime imgs to list
			ratedImageList.add(image); ratedImageList.add(image1); ratedImageList.add(image2); ratedImageList.add(image3); 
			ratedImageList.add(image4); ratedImageList.add(image5); ratedImageList.add(image6); 
			ratedImageList.add(image7); ratedImageList.add(image8); ratedImageList.add(image9); 
			ratedImageList.add(image10); ratedImageList.add(image11); ratedImageList.add(image12); 
			ratedImageList.add(image13); ratedImageList.add(image14); ratedImageList.add(image15);	
			
			//add rated panel anime score labels(pink) to list
			for(int i = 0; i < 16; i++) {
				ratedLabelList.add(new JLabel());
		    }
			
			//add episode panel anime score titles to list
			for(int i = 0; i < 6; i++) {
				episodeTitleList.add(new JButton());
		    }

			//add episode panel anime cards to list
			for(int i = 0; i < 6; i++) {
				episodeIconList.add(new JButton());
		    }
			
			//add episode panel anime imgs to list
			episodeImageList.add(bImage); episodeImageList.add(bImage1); episodeImageList.add(bImage2);
			episodeImageList.add(bImage3); episodeImageList.add(bImage4); episodeImageList.add(bImage5);
			
			//add episode panel anime score labels(green) to list
			for(int i = 0; i < 6; i++) {
				episodeLabelList.add(new JLabel());
		    }
			
			
			//set position and qualities of backgroundScrollPane and backgroundPanel that backgroundScrollPane is attached to
			backgroundPanel.setPreferredSize(new Dimension(1500,2000));
			backgroundPanel.setBackground(Color.pink);
		    backgroundScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    backgroundScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    backgroundScrollPane.setViewportView(backgroundPanel);
		    backgroundScrollPane.getVerticalScrollBar().setUnitIncrement(14);
		    
		    //set position and qualities of navigationPanel1 and components in it
		    navigationPanel.setPreferredSize(new Dimension(554,100));
		    navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER,17,35));
		    navigationPanel.setBackground(Color.WHITE);
		    navigationPanel1.setPreferredSize(new Dimension(554,100));
		    navigationPanel1.setLayout(new FlowLayout(FlowLayout.RIGHT,2,2));
		    navigationPanel1.setBackground(Color.WHITE);
		    displayUsername = new JLabel("<HTML>Current User: <style='color:blue'><U>"+username+"<style='color:blue'><U><HTML>");
		    displayUsername.setPreferredSize(new Dimension(434,25));
		    profileOptions.setPreferredSize(new Dimension(434,25));
		    profileOptions.setBackground(Color.white);
		    profileOptions.setForeground(Color.blue);
		    profileOptions.setFocusable(false);
		    
		   //set position and qualities of navigationPanel and components in it
		    communityButton.setPreferredSize(new Dimension(125,25));
		    communityButton.setFont(new Font("Arial", Font.BOLD,20));
		    communityButton.setFocusable(false);
		    communityButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    communityButton.setBorderPainted(false);
		    communityButton.setOpaque(false);
		    communityButton.setContentAreaFilled(false);
			helpButton.setPreferredSize(new Dimension(64,25));
			helpButton.setFont(new Font("Arial", Font.BOLD,20));
			helpButton.setFocusable(false);
			helpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			helpButton.setBorderPainted(false);
			helpButton.setOpaque(false);
			helpButton.setContentAreaFilled(false);
			supportUsButton.setPreferredSize(new Dimension(142,25));
			supportUsButton.setFont(new Font("Arial", Font.BOLD,20));
			supportUsButton.setFocusable(false);
			supportUsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			supportUsButton.setBorderPainted(false);
			supportUsButton.setOpaque(false);
			supportUsButton.setContentAreaFilled(false);
		    searchBar.setPreferredSize(new Dimension(400,25));
			searchButton.setPreferredSize(new Dimension(30,25));
			
			//set position and qualities of popularPanel and components in it ( JPanel )
			popularPanel.setPreferredSize(new Dimension(1278,450));
		    popularPanel.setLayout(new FlowLayout(FlowLayout.CENTER,21,12));
		    popularPanel.setBackground(Color.WHITE);
		    animeButton.setPreferredSize(new Dimension(78,25));
		    animeButton.setFont(new Font("Arial", Font.BOLD,20));
		    animeButton.setFocusable(false);
		    animeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    animeButton.setBorderPainted(false);
		    animeButton.setOpaque(false);
		    animeButton.setContentAreaFilled(false);
		    
			for(JButton button : popularIconList) {
				button.setPreferredSize(new Dimension(204,300));
			}
			
			popularRightSlider.setPreferredSize(new Dimension(40,79));
		    popularRightSlider.setOpaque(false);
		    popularRightSlider.setContentAreaFilled(false);
		    popularRightSlider.setBorderPainted(false);
		    popularRightSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    rightSliderText.setFont(new Font("Arial", Font.BOLD, 15));
		    rightSliderText.setPreferredSize(new Dimension(45,55));
		    rightSliderText.setForeground(Color.MAGENTA);
		    rightSliderHolder.setLayout(new FlowLayout(FlowLayout.LEFT,0,10));
		    rightSliderHolder.setPreferredSize(new Dimension(41,240));
		    rightSliderHolder.setBackground(Color.WHITE);
		    popularLeftSlider.setPreferredSize(new Dimension(40,79));
		    popularLeftSlider.setOpaque(false);
		    popularLeftSlider.setContentAreaFilled(false);
		    popularLeftSlider.setBorderPainted(false);
		    popularLeftSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    leftSliderText.setFont(new Font("Arial", Font.BOLD, 15));
		    leftSliderText.setPreferredSize(new Dimension(45,55));
		    leftSliderText.setForeground(Color.MAGENTA);
		    leftSliderHolder.setLayout(new FlowLayout(FlowLayout.LEFT,0,10));
		    leftSliderHolder.setPreferredSize(new Dimension(41,240));
		    leftSliderHolder.setBackground(Color.WHITE);
		    
		    for(JButton button : popularTitleList) {
		    	button.setOpaque(false);
		    	button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    	button.setContentAreaFilled(false);
		    	button.setBorderPainted(false);
		    	button.setPreferredSize(new Dimension(204,40));
		    }
		    
		    mostPopularTitlePanel.setPreferredSize(new Dimension(1199,40));
		    mostPopularTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		    mostPopularTitlePanel.setBackground(Color.BLUE);
		    mostPopularTitlePanel1.setPreferredSize(new Dimension(699,40));
		    mostPopularTitlePanel1.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		    mostPopularTitlePanel1.setBackground(Color.BLUE);
		    mostPopularSectionTitle.setPreferredSize(new Dimension(500,40));
		    mostPopularSectionTitle.setFont(new Font("Arial", Font.BOLD,30));
		    mostPopularSectionTitle.setForeground(Color.MAGENTA);

		    animeMoviesPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,8));
		    animeMoviesPanel.setPreferredSize(new Dimension(225,450));
			animeMoviesPanel.setBackground(Color.WHITE);
			animeMoviesTitlePanel.setPreferredSize(new Dimension(200,45));
			animeMoviesTitlePanel.setBackground(Color.BLUE);
			animeMoviesTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			animeMoviesHolder.setPreferredSize(new Dimension(200,70));
			animeMoviesHolder1.setPreferredSize(new Dimension(200,70));
			animeMoviesHolder2.setPreferredSize(new Dimension(200,70));
			animeMoviesHolder3.setPreferredSize(new Dimension(200,70));
			animeMoviesHolder4.setPreferredSize(new Dimension(200,70));
			animeMoviesHolder.setLayout(new FlowLayout(FlowLayout.LEFT));
			animeMoviesHolder1.setLayout(new FlowLayout(FlowLayout.LEFT));
			animeMoviesHolder2.setLayout(new FlowLayout(FlowLayout.LEFT));
			animeMoviesHolder3.setLayout(new FlowLayout(FlowLayout.LEFT));
			animeMoviesHolder4.setLayout(new FlowLayout(FlowLayout.LEFT));
			animeMoviesSectionTitle.setPreferredSize(new Dimension(250,20));
			animeMoviesSectionTitle.setFont(new Font("Arial", Font.BOLD, 15));
			animeMoviesSectionTitle.setForeground(Color.MAGENTA);
			animeMovies.setPreferredSize(new Dimension(45,60));
			animeMovies1.setPreferredSize(new Dimension(45,60));
			animeMovies2.setPreferredSize(new Dimension(45,60));
			animeMovies3.setPreferredSize(new Dimension(45,60));
			animeMovies4.setPreferredSize(new Dimension(45,60));
			
			for(JButton button : moviesAnimeTitleList) {
				button.setPreferredSize(new Dimension(140,50));
				button.setContentAreaFilled(false);
				button.setBorderPainted(false);
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				button.setForeground(Color.BLUE);
		    }
			
	        animeMovies.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        animeMovies1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        animeMoviesAnimeName1.setForeground(Color.BLUE);
	        animeMovies2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        animeMoviesAnimeName2.setForeground(Color.BLUE);
	        animeMovies3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        animeMoviesAnimeName3.setForeground(Color.BLUE);
	        animeMovies4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        animeMoviesAnimeName4.setForeground(Color.BLUE);
	
		    //set position and qualities of topRatedPanel and components in it ( JPanel )
			topRatedPanel.setPreferredSize(new Dimension(1507,800));
			topRatedPanel.setBackground(Color.white);
			topRatedPanel.setLayout(new FlowLayout(FlowLayout.LEFT,16,5));
			topRatedTitlePanel.setPreferredSize(new Dimension(1479,40));
			topRatedTitlePanel.setBackground(Color.BLUE);
			topRatedTitlePanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
			topRatedTitlePanel1.setPreferredSize(new Dimension(643,40));
			topRatedTitlePanel1.setBackground(Color.BLUE);
			topRatedTitlePanel1.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
			animeRatedSectionTitle.setFont(new Font("Arial", Font.BOLD,30));
			animeRatedSectionTitle.setForeground(Color.MAGENTA);

		    for(JButton button : ratedIconList) {
		    	button.setPreferredSize(new Dimension(171,264));
		    }
		    for(JButton button : ratedTitleList) {
		    	button.setPreferredSize(new Dimension(171,40));
		    	button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    	button.setContentAreaFilled(false);
		    	button.setBorderPainted(false);
		    	button.setForeground(Color.BLUE);
		    }
		    for(JLabel label : ratedLabelList) {
		    	label.setPreferredSize(new Dimension(171,11));
		    	label.setForeground(Color.MAGENTA);
		    }
		    crown.setPreferredSize(new Dimension(50,34));
		    crown.setContentAreaFilled(false);
		    crown.setBorderPainted(false);
		    crown.setFocusable(false);
		    ratedRightSlider.setPreferredSize(new Dimension(40,79));
		    ratedRightSlider.setOpaque(false);
		    ratedRightSlider.setContentAreaFilled(false);
		    ratedRightSlider.setBorderPainted(false);
		    ratedRightSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    rightSliderText1.setFont(new Font("Arial", Font.BOLD, 15));
		    rightSliderText1.setPreferredSize(new Dimension(45,55));
		    rightSliderText1.setForeground(Color.MAGENTA);
		    ratedLeftSlider.setPreferredSize(new Dimension(40,79));
		    ratedLeftSlider.setOpaque(false);
		    ratedLeftSlider.setContentAreaFilled(false);
		    ratedLeftSlider.setBorderPainted(false);
		    ratedLeftSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    leftSliderText1.setFont(new Font("Arial", Font.BOLD, 15));
		    leftSliderText1.setPreferredSize(new Dimension(45,55));
		    leftSliderText1.setForeground(Color.MAGENTA);
		    spaceHolder.setPreferredSize(new Dimension(614,40));
		    spaceHolder.setBackground(Color.WHITE);
			
		    //set position and qualities of animeEpisodePanel and components in it ( JPanel )
		    animeEpisodePanel.setPreferredSize(new Dimension(1507,450));
		    animeEpisodePanel.setBackground(Color.WHITE);
		    animeEpisodeTitlePanel.setPreferredSize(new Dimension(1479,40));
		    animeEpisodeTitlePanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
			animeEpisodeTitlePanel.setBackground(Color.BLUE);
			animeEpisodeTitlePanel1.setPreferredSize(new Dimension(347,40));
			animeEpisodeTitlePanel1.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
			animeEpisodeTitlePanel1.setBackground(Color.BLUE);
			animeEpisodeSectionTitle.setPreferredSize(new Dimension(500,40));
			animeEpisodeSectionTitle.setFont(new Font("Arial", Font.BOLD,30));
			animeEpisodeSectionTitle.setForeground(Color.MAGENTA);

			for(JButton button : episodeIconList) {
				button.setPreferredSize(new Dimension(204,300));
			}
			
		    for(JButton button : episodeTitleList) {
		    	button.setOpaque(false);
		    	button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    	button.setContentAreaFilled(false);
		    	button.setBorderPainted(false);
		    	button.setPreferredSize(new Dimension(204,40));
		    }
		    for(JLabel label : episodeLabelList) {
		    	label.setPreferredSize(new Dimension(204,11));
		    	label.setForeground(Color.GREEN);
		    }
			
			episodeRightSlider.setPreferredSize(new Dimension(40,79));
			episodeRightSlider.setOpaque(false);
			episodeRightSlider.setContentAreaFilled(false);
			episodeRightSlider.setBorderPainted(false);
			episodeRightSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    episodeRightSliderText.setFont(new Font("Arial", Font.BOLD, 15));
		    episodeRightSliderText.setPreferredSize(new Dimension(45,75));
		    episodeRightSliderText.setForeground(Color.MAGENTA);
		    episodeLeftSlider.setPreferredSize(new Dimension(40,79));
		    episodeLeftSlider.setOpaque(false);
		    episodeLeftSlider.setContentAreaFilled(false);
		    episodeLeftSlider.setBorderPainted(false);
		    episodeLeftSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    episodeLeftSliderText.setFont(new Font("Arial", Font.BOLD, 15));
		    episodeLeftSliderText.setPreferredSize(new Dimension(45,75));
		    episodeLeftSliderText.setForeground(Color.MAGENTA);
			episodeleftSliderHolder.setLayout(new FlowLayout(FlowLayout.LEFT,0,10));
			episodeleftSliderHolder.setPreferredSize(new Dimension(75,240));
			episodeleftSliderHolder.setBackground(Color.WHITE);
			episoderightSliderHolder.setLayout(new FlowLayout(FlowLayout.RIGHT,0,10));
			episoderightSliderHolder.setPreferredSize(new Dimension(75,240));
			episoderightSliderHolder.setBackground(Color.WHITE);
		    episodeFiller.setPreferredSize(new Dimension(170,35));
		    episodeFiller.setOpaque(false);
			
		    //set position and qualities of animeInfoPanel and components in it ( JPanel )
			animeInfoPanel.setPreferredSize(new Dimension(1507,112));
			animeInfoPanel.setBackground(Color.white);
			
		    //set position and qualities of infoPanel and components in it ( JPanel )
			infoPanel.setPreferredSize(new Dimension(1507,162));
			infoPanel.setBackground(Color.BLUE);

		    //load initial anime images and their titles in popular panel
		    popularPanel.add(leftSliderHolder);
		    for(JButton button : popularIconList) {
		    	popularPanel.add(button);
		    }
		    

		    loadPopularAnime();
		    
		    loadRatedAnime();
	        
			for(int i = 0; i < 6; i++) {
			    BufferedImage tempImage = null;
				try {
					tempImage = ImageIO.read(new URL(listEpisode.get(episodeCount)[9]));
				} catch (MalformedURLException e) {
					JOptionPane.showMessageDialog(null, e);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				episodeImageList.set(i, tempImage);
				episodeIconList.get(i).setIcon(new javax.swing.ImageIcon(tempImage));
				episodeIconList.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				episodeTitleList.get(i).setText(listEpisode.get(episodeCount)[1]);
				episodeLabelList.get(i).setText(listEpisode.get(episodeCount)[4]);
				episodeCount++;
			}
			episodeCount--;

	        //add objects to navigationPanel
		    navigationPanel.add(animeButton);
		    navigationPanel.add(communityButton);
		    navigationPanel.add(helpButton);
		    navigationPanel.add(supportUsButton);
		    //add objects to navigationPanel1
		    navigationPanel1.add(searchBar);
		    navigationPanel1.add(searchButton);
		    navigationPanel1.add(displayUsername);
		    navigationPanel1.add(profileOptions);
		    
		    //add objects to popularPanel
		    mostPopularTitlePanel.add(mostPopularSectionTitle,0);
		    //mostPopularTitlePanel1.add(mostPopularSeeMore);
		    mostPopularTitlePanel.add(mostPopularTitlePanel1);
		    leftSliderHolder.add(leftSliderText);
		    leftSliderHolder.add(popularLeftSlider);
		    rightSliderHolder.add(rightSliderText);
		    rightSliderHolder.add(popularRightSlider);
		    popularPanel.add(rightSliderHolder);
		    popularPanel.add(mostPopularTitlePanel,0);
		    for(int i = 0; i <= 4; i++) {
		    	popularPanel.add(popularTitleList.get(i));
		    }
		    //add objects to animeMoviesPanel
		    animeMoviesPanel.add(animeMoviesTitlePanel);
		    animeMoviesPanel.add(animeMoviesHolder);
		    animeMoviesPanel.add(animeMoviesHolder1);
		    animeMoviesPanel.add(animeMoviesHolder2);
		    animeMoviesPanel.add(animeMoviesHolder3);
		    animeMoviesPanel.add(animeMoviesHolder4);
		    animeMoviesTitlePanel.add(animeMoviesSectionTitle);
		    animeMoviesHolder.add(animeMovies);
		    animeMoviesHolder1.add(animeMovies1);
		    animeMoviesHolder2.add(animeMovies2);
		    animeMoviesHolder3.add(animeMovies3);
		    animeMoviesHolder4.add(animeMovies4);
		    animeMoviesHolder.add(animeMoviesAnimeName);
		    animeMoviesHolder1.add(animeMoviesAnimeName1);
		    animeMoviesHolder2.add(animeMoviesAnimeName2);
		    animeMoviesHolder3.add(animeMoviesAnimeName3);
		    animeMoviesHolder4.add(animeMoviesAnimeName4);

		    //add objects to topRatedPanel
		    //topRatedTitlePanel1.add(topRatedSeeMore);
		    topRatedTitlePanel.add(crown);
		    topRatedTitlePanel.add(topRatedTitlePanel1);
		    topRatedTitlePanel.add(animeRatedSectionTitle,0);
		    topRatedPanel.add(topRatedTitlePanel);   
		    
		    for(int i = 0; i <= 7; i++) {
		    	topRatedPanel.add(ratedIconList.get(i));
		    }
		    for(int i = 0; i <= 7; i++) {
		    	topRatedPanel.add(ratedLabelList.get(i));
		    }
		    for(int i = 0; i <= 7; i++) {
		    	topRatedPanel.add(ratedTitleList.get(i));
		    }
		    for(int i = 8; i <= 15; i++) {
		    	topRatedPanel.add(ratedIconList.get(i));
		    }
		    for(int i = 8; i <= 15; i++) {
		    	topRatedPanel.add(ratedLabelList.get(i));
		    }
		    for(int i = 8; i <= 15; i++) {
		    	topRatedPanel.add(ratedTitleList.get(i));
		    }
		    topRatedPanel.add(spaceHolder);
		    topRatedPanel.add(leftSliderText1);
		    topRatedPanel.add(ratedLeftSlider);
		    topRatedPanel.add(ratedRightSlider);
		    topRatedPanel.add(rightSliderText1);
		    
		    //add objects to animeEpiosdePanel
		    animeEpisodePanel.add(animeEpisodeTitlePanel);
		    animeEpisodeTitlePanel.add(animeEpisodeSectionTitle);
		    animeEpisodeTitlePanel.add(animeEpisodeTitlePanel1);
		    //animeEpisodeTitlePanel1.add(animeEpisodeSeeMore);
		    animeEpisodePanel.add(episodeleftSliderHolder);
		    episodeleftSliderHolder.add(episodeLeftSliderText);
		    episodeleftSliderHolder.add(episodeLeftSlider);
		    for(JButton button : episodeIconList) {
		    	animeEpisodePanel.add(button);
		    	}
		    animeEpisodePanel.add(episoderightSliderHolder);
		    episoderightSliderHolder.add(episodeRightSliderText);
		    episoderightSliderHolder.add(episodeRightSlider);
		    animeEpisodePanel.add(episodeFiller);
		    for(JLabel label : episodeLabelList) {
		    	animeEpisodePanel.add(label);
		    	}
		    for(JButton button : episodeTitleList) {
		    	animeEpisodePanel.add(button);
		    	}
		    //add objects to animeInfoPanel
		    //add objects to InfoPanel
		   
			//add JPanels to background JPanel that hold all frame components
		    backgroundPanel.add(animePlanet);
			backgroundPanel.add(navigationPanel);
			backgroundPanel.add(navigationPanel1);
		    backgroundPanel.add(popularPanel);
		    backgroundPanel.add(animeMoviesPanel);
		    backgroundPanel.add(topRatedPanel);
		    backgroundPanel.add(animeEpisodePanel);
		    backgroundPanel.add(animeInfoPanel);
		    backgroundPanel.add(infoPanel);
		    
		    //add objects to JFrame directly
		    frame.getContentPane().add(backgroundScrollPane);
			
			//event handling object created to manage events
			EventHandler eventHandler = new EventHandler();
			popularLeftSlider.addActionListener(eventHandler);
			popularRightSlider.addActionListener(eventHandler);
			ratedLeftSlider.addActionListener(eventHandler);
			ratedRightSlider.addActionListener(eventHandler);
			episodeRightSlider.addActionListener(eventHandler);
			episodeLeftSlider.addActionListener(eventHandler);
			profileOptions.addActionListener(eventHandler);
			searchBar.addActionListener(eventHandler);
			searchButton.addActionListener(eventHandler);
			
			for(JButton button : popularIconList) {
				button.addActionListener(eventHandler);
			}
			for(JButton button : popularTitleList) {
				button.addActionListener(eventHandler);
			}
			for(JButton button : ratedIconList) {
				button.addActionListener(eventHandler);
			}
			for(JButton button : ratedTitleList) {
				button.addActionListener(eventHandler);
			}
			for(JButton button : episodeIconList) {
				button.addActionListener(eventHandler);
			}
			for(JButton button : episodeTitleList) {
				button.addActionListener(eventHandler);
			}
			
			loadingFrame.closeFrame();
			frame.setVisible(true);
	}
	private void loadPopularAnime() {
	    BufferedImage tempImage = null;
		for(int i = 0; i < 5; i++) {
			
			popularCount++;
			try {
				tempImage = ImageIO.read(new URL(listPopularity.get(popularCount)[9]));
			} catch (MalformedURLException e) {
				JOptionPane.showMessageDialog(null, e);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e);
			}
			popularImageList.set(i, tempImage);
			popularIconList.get(i).setIcon(new javax.swing.ImageIcon(tempImage));
			popularIconList.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			//popularPanel.add(popularIconList.get(i));
			popularTitleList.get(i).setText("#"+popularCount+" "+listPopularity.get(popularCount)[1]);
			
		}
	        popularPanel.add(rightSliderHolder);
	        popularPanel.add(popularTitleList.get(0));
	        popularPanel.add(popularTitleList.get(1));
	        popularPanel.add(popularTitleList.get(2));
	        popularPanel.add(popularTitleList.get(3));
	        popularPanel.add(popularTitleList.get(4));
	}
	private void loadRatedAnime() {
	    BufferedImage tempImage = null;
		for(int i = 0; i < 16; i++) {
			ratedCount++;
			try {
				tempImage = ImageIO.read(new URL(listRanked.get(ratedCount)[9]));
			} catch (MalformedURLException e) {
				JOptionPane.showMessageDialog(null, e);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e);
			}

			ratedImageList.set(i, tempImage);
			ratedIconList.get(i).setIcon(new javax.swing.ImageIcon(tempImage));
			ratedIconList.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ratedTitleList.get(i).setText(listRanked.get(ratedCount)[1]);
			ratedLabelList.get(i).setText(listRanked.get(ratedCount)[8]);
		}
	}

	private class EventHandler implements ActionListener  {
		BufferedImage tempImage;
		@Override
		public void actionPerformed(ActionEvent event) {
			//if user clicks next page button on popular panel
			if(event.getSource() == popularRightSlider) {
				loadPopularAnime();
				
			}
			//if user clicks prior button on popular panel
			if(event.getSource() == popularLeftSlider) {
				if(popularCount != 5) {
					
					
					popularCount = popularCount - 5;
					int tempCount = popularCount;
					for(int i = 4; i >= 0; i--) {
						
						try {
							tempImage = ImageIO.read(new URL(listPopularity.get(tempCount)[9]));
						} catch (MalformedURLException e) {
							JOptionPane.showMessageDialog(null, e);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, e);
						}
						popularImageList.set(i, tempImage);
						popularIconList.get(i).setIcon(new javax.swing.ImageIcon(tempImage));
						popularIconList.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						//popularPanel.add(popularIconList.get(i));
						popularTitleList.get(i).setText("#"+tempCount+" "+listPopularity.get(tempCount)[1]);
						tempCount--;
					}
				        popularPanel.add(rightSliderHolder);
				        popularPanel.add(popularTitleList.get(0));
				        popularPanel.add(popularTitleList.get(1));
				        popularPanel.add(popularTitleList.get(2));
				        popularPanel.add(popularTitleList.get(3));
				        popularPanel.add(popularTitleList.get(4));
				}else {
					return;
				}
		    }

			if(event.getSource() == ratedRightSlider){
				
				loadRatedAnime();
			}
			if(event.getSource() == ratedLeftSlider) {
				if(ratedCount != 16) {
					ratedCount = ratedCount - 16;
					int tempCount = ratedCount;
					for(int i = 15; i >= 0; i--) {
						
						try {
							tempImage = ImageIO.read(new URL(listRanked.get(tempCount)[9]));
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						ratedImageList.set(i, tempImage);
						ratedIconList.get(i).setIcon(new javax.swing.ImageIcon(tempImage));
						ratedIconList.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						ratedTitleList.get(i).setText(listRanked.get(tempCount)[1]);
						ratedLabelList.get(i).setText(listRanked.get(tempCount)[8]);
						tempCount--;
						}
					}
				}	
			if(event.getSource() == episodeRightSlider) {
				for(int i = 0; i < 6; i++) {
					episodeCount++;
					try {
						tempImage = ImageIO.read(new URL(listEpisode.get(episodeCount)[9]));
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					episodeImageList.set(i, tempImage);
					episodeIconList.get(i).setIcon(new javax.swing.ImageIcon(tempImage));
					episodeIconList.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					episodeTitleList.get(i).setText(listEpisode.get(episodeCount)[1]);
					episodeLabelList.get(i).setText(listEpisode.get(episodeCount)[4]);
				}
					for(JButton button : episodeIconList) {
				    	animeEpisodePanel.add(button);
				    	}
				    animeEpisodePanel.add(episoderightSliderHolder);
				    animeEpisodePanel.add(episodeFiller);
				    for(JLabel label : episodeLabelList) {
				    	animeEpisodePanel.add(label);
				    	}
				    for(JButton button : episodeTitleList) {
				    	animeEpisodePanel.add(button);
				    	}
				}
			if(event.getSource() == episodeLeftSlider) {
				if(episodeCount != 6) {
	
					episodeCount = episodeCount - 6;
					int tempCount = episodeCount;
					for(int i = 5; i >= 0; i--) {
						
						try {
							tempImage = ImageIO.read(new URL(listEpisode.get(tempCount)[9]));
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						episodeImageList.set(i, tempImage);
						episodeIconList.get(i).setIcon(new javax.swing.ImageIcon(tempImage));
						episodeIconList.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						episodeTitleList.get(i).setText(listEpisode.get(tempCount)[1]);
						episodeLabelList.get(i).setText(listEpisode.get(tempCount)[4]);
						tempCount--;
					}
					for(JButton button : episodeIconList) {
				    	animeEpisodePanel.add(button);
				    	}
				    animeEpisodePanel.add(episoderightSliderHolder);
				    animeEpisodePanel.add(episodeFiller);
				    for(JLabel label : episodeLabelList) {
				    	animeEpisodePanel.add(label);
				    	}
				    for(JButton button : episodeTitleList) {
				    	animeEpisodePanel.add(button);
				    	}
				}else {
					return;
				}
				
				}
			//event execution if popular anime icon cards are clicked
			int popularTempCount = 4;
			for(JButton button : popularIconList) {
				if(event.getSource() == button) {
					try{
						if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
						    Desktop.getDesktop().browse(new URI(listPopularity.get(popularCount-popularTempCount)[10]));
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Could not open website in browser");
					}
					
				}
				popularTempCount--;
			}
			//event execution if popular anime titles are clicked
			popularTempCount = 4;
			for(JButton button : popularTitleList) {
				if(event.getSource() == button) {
					try{
						if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
						    Desktop.getDesktop().browse(new URI(listPopularity.get(popularCount-popularTempCount)[10]));
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Could not open website in browser");
					}
					
				}
				popularTempCount--;
			}
			//event execution if rated anime cards are clicked
			int ratedTempCount = 15;
			for(JButton button : ratedIconList) {
				if(event.getSource() == button) {
					try{
						if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
						    Desktop.getDesktop().browse(new URI(listRanked.get(ratedCount-ratedTempCount)[10]));
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Could not open website in browser");
					}
					
				}
				ratedTempCount--;
			}
			//event execution if rated anime titles are clicked
			ratedTempCount = 15;
			for(JButton button : ratedTitleList) {
				if(event.getSource() == button) {
					try{
						if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
						    Desktop.getDesktop().browse(new URI(listRanked.get(ratedCount-ratedTempCount)[10]));
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Could not open website in browser");
					}
					
				}
				ratedTempCount--;
			}
			//event execution if episode anime cards are clicked
			int episodeTempCount = 5;
			for(JButton button : episodeIconList) {
				if(event.getSource() == button) {
					try{
						if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
						    Desktop.getDesktop().browse(new URI(listEpisode.get(episodeCount-episodeTempCount)[10]));
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Could not open website in browser");
					}
					
				}
				episodeTempCount--;
			}
			//event execution if episode anime titles are clicked
			episodeTempCount = 5;
			for(JButton button : episodeTitleList) {
				if(event.getSource() == button) {
					try{
						if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
						    Desktop.getDesktop().browse(new URI(listEpisode.get(episodeCount-episodeTempCount)[10]));
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Could not open website in browser");
					}
					
				}
				episodeTempCount--;
			}//"Profile", "Logout", "Delete Account"}; 
			if(event.getSource() == profileOptions) {
				if(profileOptions.getSelectedItem().toString().equals("SELECT PROFILE OPTION")) {
					
					return;
				}else if(profileOptions.getSelectedItem().toString().equals("Profile")) {
					try {
					//get picture URL  of the user with the input username
					String getUserPictureURL = "SELECT picture_link FROM user_information WHERE userID ='"+loggedInUserID+"'";
					ResultSet PictureRS = st.executeQuery(getUserPictureURL);
					PictureRS.next();
					loggedInUserPictureURL = new URL(PictureRS.getString("picture_link"));
					
					//get  desc of the user with the input username
					String getUserDesc = "SELECT user_desc FROM user_information WHERE userID ='"+loggedInUserID+"'";
					ResultSet DescRS = st.executeQuery(getUserDesc);
					DescRS.next();
					loggedInUserDesc = DescRS.getString("user_desc");
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Issue Loading Your Profile");
					}
					
					new UserProfile(username, loggedInUserID,loggedInUserBirthdate, loggedInUserPictureURL, loggedInUserDesc, connect, st);
					return;
					
				}else if(profileOptions.getSelectedItem().toString().equals("Logout")){
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					new LoginPage();
					return;
				}else if(profileOptions.getSelectedItem().toString().replaceAll("\\s","").equals("DeleteAccount")) {
					try {
					//delete user account from all database areas
					String deleteIdentification = "DELETE FROM user_identification WHERE userID ='"+loggedInUserID+"'";
					st.executeUpdate(deleteIdentification);
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					new LoginPage();
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, "Failed Account Deletion");
					}
					return;
				}else {
					return;
				}
			}
			if(event.getSource() == searchButton || event.getSource() == searchBar ) {
				
				try {
				String usernameSearch = searchBar.getText().replaceAll("\\s", "");

				//get the userID of the user with the input username
				String getUserIDSQL = "SELECT get_userID('"+usernameSearch+"') FROM dual";
				ResultSet rs = st.executeQuery(getUserIDSQL);
				rs.next();
				int userID = Integer.parseInt(rs.getString("get_userID('"+usernameSearch+"')"));
				//if user is found, load their profile
				//getbirthday  of the user with the input username
				String getUserbirthdate = "SELECT birthdate FROM user_information WHERE userID ='"+userID+"'";
				ResultSet birthdateRS = st.executeQuery(getUserbirthdate);
				birthdateRS.next();
				String searchedUserBirthdate = birthdateRS.getString("birthdate");
				
				//get picture URL  of the user with the input username
				String getUserPictureURL = "SELECT picture_link FROM user_information WHERE userID ='"+userID+"'";
				ResultSet PictureRS = st.executeQuery(getUserPictureURL);
				PictureRS.next();
				URL searchedUserPictureURL = new URL(PictureRS.getString("picture_link"));
				
				//get  desc of the user with the input username
				String getUserDesc = "SELECT user_desc FROM user_information WHERE userID ='"+userID+"'";
				ResultSet DescRS = st.executeQuery(getUserDesc);
				DescRS.next();
				String searchedUserDesc = DescRS.getString("user_desc");
				
				new UserProfile(usernameSearch, userID,searchedUserBirthdate, searchedUserPictureURL, searchedUserDesc);
				
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR finding this user");
					return;
					
				}
			}
		}//Action performed
	}//eventhandler

}//superclass

