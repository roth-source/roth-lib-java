package roth.lib.api.digitalocean.test;


public class ImageTest extends Test
{
	
	public static void main(String[] args) throws Exception
	{
		//getImages();
		//getImage();
		//updateImage();
		//deleteImage();
	}
	
	public static void getImages()
	{
		clientFactory.getImageClient().getImages(1);
	}
	
	public static void getImage()
	{
		clientFactory.getImageClient().getImage(1601);
	}
	
	public static void updateImage()
	{
		clientFactory.getImageClient().updateImage(4707226, "test");
	}
	
	public static void deleteImage()
	{
		clientFactory.getImageClient().deleteImage(4707226);
	}
	
}
