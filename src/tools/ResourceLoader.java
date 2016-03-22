/* Name: ResourceLoader
 * Author: Devon McGrath
 * Date: 01/31/2016
 * Description: This class loads resources for the program.
 */

package tools;

import java.awt.Image;
import javax.imageio.ImageIO;

public class ResourceLoader {

	//Method that loads the images for the program
	public static Image loadImage(String imageName){
		
		//Image
		Image img = null;
		
		//Special case
		if (imageName == "") {
			return null;
		}
		
		//Get the image
		try{
			img = ImageIO.read(ResourceLoader
					.class.getResource("/images/"+imageName+".png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//Return the image
		return img;
	}
}
