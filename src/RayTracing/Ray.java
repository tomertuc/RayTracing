package RayTracing;

public class Ray {
	public Point3d origin;
	public Vector direction;
	//let P0=origin and V=direction, then ray is P=P0+tV
	
	public Ray(){
		
	}
	
	public Ray(Point3d origin, Vector direction){
		this.origin=origin;
		this.direction=direction;
	}
	
	public void setOrigin(Point3d origin){
		this.origin=origin;
	}
	
	public void setDirection(Vector direction){
		this.direction=direction;
	}
	
	public Point3d P0(){
		return origin;
	}
	
	public Vector V(){
		return direction;
	}
}
