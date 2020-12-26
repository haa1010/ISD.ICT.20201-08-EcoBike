package views.screen;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * This class is the fxml screen view handler
 * @author Do Minh Thong
 * @version 1.0
 */

public class FXMLScreenHandler {

	protected FXMLLoader loader;
	protected AnchorPane content;
	
	/**
	 * constructor
	 * @param screenPath
	 * @throws IOException
	 */
	public FXMLScreenHandler(String screenPath) throws IOException {
		this.loader = new FXMLLoader(getClass().getResource(screenPath));
		// Set this class as the controller
		this.loader.setController(this);
		this.content = (AnchorPane) loader.load();
	}
	
	/**
	 * get content of the anchor pane
	 * @return
	 */
	public AnchorPane getContent() {
		return this.content;
	}
	
	public FXMLLoader getLoader() {
		return this.loader;
	}
	
	/**
	 * set image with the path to the file
	 * @param imv
	 * @param path
	 */
	public void setImage(ImageView imv, String path){
		File file = new File(path);
		Image img = new Image(file.toURI().toString());
		imv.setImage(img);
	}
}
