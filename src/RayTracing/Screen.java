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
	
	public Vector getPixelPosition(int w, int h){
		double w_on_scr=convertPixelWToScreenW(w);
		double h_on_scr=convertPixelHToScreenH(h);
		return origin.add(Vx.mul(w_on_scr)).add(Vy.mul(h_on_scr));
	}
}
