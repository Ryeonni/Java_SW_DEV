package DataList;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageView {
	private String blobFile;
	private File file;
	private BufferedImage img;
	private ImageIcon imgIcon;
	private int imgGetW, imgGetH;
	private final int maxSizeH = 295;	// 화면에 보여주는 예문이미지 최대 높이값
	
	public ImageView(String fileName) throws IOException {

		blobFile ="src//IMG//"+fileName;
		file = new File(blobFile);

		img = ImageIO.read(file);
		imgIcon = new ImageIcon(img);
		imgGetW = imgIcon.getIconWidth();
		imgGetH = imgIcon.getIconHeight();
		
		if(imgGetH > maxSizeH) {
			double ratio;	// 사이즈 비율
			ratio = (maxSizeH/(double)imgGetH);
			imgGetW = (int)(imgGetW * ratio);
			imgGetH = maxSizeH ;
			Image changeImg = img.getScaledInstance(imgGetW, imgGetH, Image.SCALE_SMOOTH);
			
			imgIcon = new ImageIcon(changeImg);
		}
	}
	
	//새창에 원본 이미지 보여주기	
	public ImageIcon img()	{	return new ImageIcon(img);	}
	public ImageIcon run()	{	return imgIcon;	}
	public int imgSizeW()	{	return imgGetW;	}
	public int imgSizeH()	{	return imgGetH;	}

}
