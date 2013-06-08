package RayTracing;

import java.util.List;
import java.util.LinkedList;

public class Pixel {

	public int x;
	public int y;
	public int imageWidth;
	public int imageHeight;
	
	public Pixel(int image_width, int image_height){
		imageWidth=image_width;
		imageHeight=image_height;
	}
	
	public Pixel(int image_width, int image_height, int x, int y){
		imageWidth=image_width;
		imageHeight=image_height;
		this.x=x;
		this.y=y;
	}
	
	public static Pixel outOfBounds(){
		final Pixel ret=new Pixel(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
		return ret;
	}
	
	public boolean isInBounds(){
		return (x>=0 && x<imageWidth) && (y>=0 && y<imageHeight);
	}
	
	public boolean isEqual(Pixel other){
		return x==other.x && y==other.y;
	}
	
	//returns all pixels that intersect on the screen, with the circle centered in this pixel's center,
	//and with the specified radius
	public List<Pixel> getAllPixelsAroundThisPixel(double radius, Screen screen){
		List<Pixel> intersectedPixels=new LinkedList<Pixel>();
		Vector pixelCenter=screen.getPixelCenter(x, y);
		
		Pixel leftMost=screen.pointToPixel(pixelCenter.sub(screen.Vx.mul(radius)));
		Pixel rightMost=screen.pointToPixel(pixelCenter.add(screen.Vx.mul(radius))); 
		Pixel upMost=screen.pointToPixel(pixelCenter.add(screen.Vy.mul(radius)));
		Pixel downMost=screen.pointToPixel(pixelCenter.sub(screen.Vy.mul(radius)));
		
		for(int i=leftMost.x; i<=rightMost.x; i++){
			for(int j=downMost.y; j<=upMost.y; j++){
				Pixel p=new Pixel(imageWidth, imageHeight, i, j);
				intersectedPixels.add(p);
				//if(screen.minimalDistanceOfPixelFromPoint(p, pixelCenter)<=radius){
					
				//}
			}
		}
		
		return intersectedPixels;
	}
}
