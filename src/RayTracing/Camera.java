package RayTracing;

public class Camera {
	public Vector position;
	public Vector lookatpoint;
	public Vector up;
	public double scrDist;
	public double scrWidth;
	
	public Vector Vx;
	public Vector Vy;
	public Vector Vz;
	
	public Camera(){
		
	}
	
	public void setPosition(String x, String y, String z){
		position=new Vector(x,y,z);
	}
	
	public void setLookAtPoint(String x, String y, String z){
		lookatpoint=new Vector(x,y,z);
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
		//define Vx, Vy, Vz
		//based on Views & Projections presentation, pages 1-2
		Vector lookAtVector=Vector.substractVectors(lookatpoint, position);
		Vector w=Vector.getNormalized(lookAtVector);
		Vector u=Vector.getNormalized(Vector.crossProductVectors(up, w));
		Vector v=Vector.crossProductVectors(w, u);
		
		Vz=w;
		Vy=v;
		Vx=u;
	}
	
	public Screen getScreenFromCamera(int imageWidth, int imageHeight){
		//compute screen
		return null;
	}
}
