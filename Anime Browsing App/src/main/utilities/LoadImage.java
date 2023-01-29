package main.utilities;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * 
 * @author Mohann Scarlett
 * @version 1/25/2023
 */
public class LoadImage {
	//loads image from specified path into a JLabel that can be inserted into a JFrame
	public JLabel loadImageIntoJLabel(String path,String errorMessage) {
		JLabel imageLabel = null;
		ImageIcon imageAsIcon = null;
		Image image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
			imageAsIcon = new ImageIcon(image);
			imageLabel = new JLabel(imageAsIcon); 
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, errorMessage);
			return null;
		}
		return imageLabel;
	}
	//loads image from specified path into an Image that can be used
	public Image loadImage(String path, String errorMessage) {
		Image image;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, errorMessage);
			return null;
		}
		return image;
	}
	//loads image from specified path into an ImageIcon that can be used
	public ImageIcon loadImageIcon(String path, String errorMessage) {
		Image image;
		ImageIcon icon;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
			icon = new ImageIcon(image);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, errorMessage);
			return null;
		}
		return icon;
	}
	//render GIF to imageIcon
	public ImageIcon fetchImageFromGIF(String path,String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			JOptionPane.showMessageDialog(null, "Failed to Display Loading Bar");
			return null;
		}
	}
}
