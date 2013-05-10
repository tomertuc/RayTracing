package RayTracing;

public class Screen {

	public Vector origin;
	public int width;
	public int height;
	
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
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public void setHeight(int height){
		this.height=height;
	}
	
	public Vector getPixelPosition(double w, double h){
		return origin.add(Vx.mul(w)).add(Vy.mul(h));
	}
}
