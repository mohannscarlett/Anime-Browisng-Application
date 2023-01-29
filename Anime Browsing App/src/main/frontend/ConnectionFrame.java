package main.frontend;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import main.utilities.FileLoader;
import main.utilities.LoadImage;
/**
 * 
 * @author Mohann Scarlett mohannscarlett3@gmail.com
 * @version 1/25/2023
 */
public class ConnectionFrame extends SwingWorker<Void,Void> {
		//holds CSV file information
		private ArrayList<String[]> list = new ArrayList<>();
		private ArrayList<String[]> listRanked = new ArrayList<>();
		private ArrayList<String[]> listPopularity = new ArrayList<>();
		private ArrayList<String[]> listEpisode = new ArrayList<>();
		
		JFrame frame = new JFrame();
		boolean unknownScreenSize;
		LoginPage loginPage;
		
		public ConnectionFrame(LoginPage loginPage, boolean unknownScreenSize){
			this.unknownScreenSize = unknownScreenSize;
			this.loginPage = loginPage;
		}

		@Override
		protected Void doInBackground() throws Exception {
			animeList();
			 
			LoadImage load = new LoadImage();
			 
			String applicationIconPath = "resources/images/galaxy1920x1080.jpg";
			Image applicationIconImage;

			applicationIconImage = load.loadImage(applicationIconPath, "Failed to Load Application Image");
						
			ImageIcon icon = load.fetchImageFromGIF("resources/images/connecting-loading.gif",null);
			JLabel lLogo = new JLabel(icon);

			if(unknownScreenSize){
				frame.setLocationRelativeTo(null);
				frame.setSize(new Dimension(276,120));
		    }else {
		    	frame.setLocation(1645/2,940/2);;
		    	frame.setSize(new Dimension(276,120));
		    }
			
			frame.getContentPane().add(lLogo);
			frame.setLayout(new FlowLayout());
			frame.setTitle("Scarlett's Chat");
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setIconImage(applicationIconImage);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
    		HomePage home = new HomePage(loginPage,list,listRanked,listPopularity,listEpisode,this);
			
			return null;
		}
		public void closeFrame() {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); //remove loading bar

		}
		//method to scan through CSV file and split it into a list of string arrays ( each row is a string array )
		//each file object is responsible for giving CSV information to a different list
		private void  animeList() {
				FileLoader csv = new FileLoader();
				list = csv.csvToArrayList("resources/csvfiles/animes.csv");
				listRanked = csv.csvToArrayList("resources/csvfiles/animesRanked.csv");
				listPopularity = csv.csvToArrayList("resources/csvfiles/animesPopularity.csv");
				listEpisode = csv.csvToArrayList("resources/csvfiles/animesEpisodes.csv");
		}
		
}
