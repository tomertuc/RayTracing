package RayTracing;

public class Screen {

	public Point3d origin;
	public int width;
	public int height;
	
	public Screen(){
		
	}
	
	public void setOrigin(Point3d origin){
		this.origin=origin;
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public void setHeight(int height){
		this.height=height;
	}
}
