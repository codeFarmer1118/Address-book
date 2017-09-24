package org.lanqiao.core;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Picture {
	public static Image getImage(String path) {
		URL u = Picture.class.getClassLoader().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}
}
