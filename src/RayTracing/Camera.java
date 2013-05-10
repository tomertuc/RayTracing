package RayTracing;

public class Camera {
	public Point3d position;
	public Point3d lookat;
	public Vector up;
	public double scrDist;
	public double scrWidth;
	
	public Vector Ux;
	public Vector Uy;
	public Vector Uz;
	
	public Camera(){
		
	}
	
	public void setPosition(String x, String y, String z){
		position=new Point3d(x,y,z);
	}
	
	public void setLookAtPoint(String x, String y, String z){
		lookat=new Point3d(x,y,z);
	}
	
	public void setUpVector(String x, String y, String z){
		up=new Vector(x,y,z);
	}
	
	public void setScreenDistance(String d){
		scrDist=Double.parseDouble(d);
	}
	
	public void setScreenWidth(String w){
		scrWidth=Double.parseDouble(w);
	}
	
	public void computeCoordinateSystem(){
		//define Ux, Uy, Uz
	}
	
	public Screen getScreenFromCamera(){
		//compute screen
		return null;
	}
}
