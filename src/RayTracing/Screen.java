package RayTracing;

public class Screen {

	public Vector origin;
	public Vector center;
	public int pixels_width;
	public int pixels_height;
	public double screen_width;
	public double screen_height;
	
	public Vector Vx;
	public Vector Vy;
	public Vector Vz;
	
	public Screen(){
		
	}
	
	public Screen(Vector Vx, Vector Vy, Vector Vz){
		this.Vx=Vx;
		this.Vy=Vy;
		this.Vz=Vz;
	}
	
	public void setOrigin(Vector origin){
		this.origin=origin;
	}
	
	public void setCenter(Vector center){
		this.center=center;
	}
	
	public void setPixelsWidth(int width){
		this.pixels_width=width;
	}
	
	public void setPixelsHeight(int height){
		this.pixels_height=height;
	}
	
	public void setScreenWidth(double width){
		this.screen_width=width;
	}
	
	public void setScreenHeight(){
		double width_ratio=(double)screen_width/(double)pixels_width;
		screen_height=width_ratio*pixels_height;
	}
	
	private double convertPixelWToScreenW(int w){
		return w*screen_width/pixels_width;
	}
	
	private double convertPixelHToScreenH(int h){
		return h*screen_height/pixels_height;
	}
	
	//returns the pixel's left bottom corner on screen
	public Vector getPixelPosition(int w, int h){
		double w_on_scr=convertPixelWToScreenW(w);
		double h_on_scr=convertPixelHToScreenH(h);
		return origin.add(Vx.mul(w_on_scr)).add(Vy.mul(h_on_scr));
	}
	
	//returns the pixel's center on screen
	public Vector getPixelCenter(int w, int h){
		final double pixelWidth=screen_width/pixels_width;
		final double pixelHeight=screen_height/pixels_height;
		return getPixelPosition(w, h).add(Vx.mul(0.5*pixelWidth)).add(Vy.mul(0.5*pixelHeight));
	}
	
	public Pixel pointToPixel(Vector point){
		Vector originToPoint=point.sub(origin);
		double distX=originToPoint.dot(Vx);
		double distY=originToPoint.dot(Vy);
		final double pixelWidth=screen_width/pixels_width;
		final double pixelHeight=screen_height/pixels_height;
		int numPixelsSeperatingInX=(int)Math.ceil(distX/pixelWidth);
		int numPixelsSeperatingInY=(int)Math.ceil(distY/pixelHeight);
		int x, y;
		if(numPixelsSeperatingInX>=pixels_width)
			x=pixels_width-1;
		else
			x=numPixelsSeperatingInX;
		if(numPixelsSeperatingInY>=pixels_height)
			y=pixels_height-1;
		else
			y=numPixelsSeperatingInY;
		return new Pixel(pixels_width, pixels_height, x, y);
	}
	
	public double minimalDistanceOfPixelFromPoint(Pixel pix, Vector point){
		//this may be not accurate
		final double pixelWidth=screen_width/pixels_width;
		final double pixelHeight=screen_height/pixels_height;
		Vector bottomLeft=getPixelPosition(pix.x, pix.y);
		Vector bottomRight=bottomLeft.add(Vx.mul(pixelWidth));
		Vector upperLeft=bottomLeft.add(Vy.mul(pixelHeight));
		Vector upperRight=upperLeft.add(Vx.mul(pixelWidth));
		Vector center=bottomLeft.add(Vx.mul(0.5*pixelWidth)).add(Vy.mul(0.5*pixelHeight));
		double[] distances={bottomLeft.distance(point), bottomRight.distance(point), upperLeft.distance(point),
				upperRight.distance(point), center.distance(point)};
		double minDist=Double.MAX_VALUE;
		for(int i=0; i<5; i++){
			if(minDist<distances[i])
				minDist=distances[i];
		}
		return minDist;
	}
}