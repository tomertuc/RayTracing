package RayTracing;

public class Ray {
	public Vector origin;
	public Vector direction;
	//let P0=origin and V=direction, then ray is P=P0+tV
	
	public Ray(){
		
	}
	
	public Ray(Vector origin, Vector direction){
		this.origin=origin;
		this.direction=direction;
	}
	
	public void setOrigin(Vector origin){
		this.origin=origin;
	}
	
	public void setDirection(Vector direction){
		this.direction=direction;
	}
	
	public Vector P0(){
		return origin;
	}
	
	public Vector V(){
		return direction;
	}
}
