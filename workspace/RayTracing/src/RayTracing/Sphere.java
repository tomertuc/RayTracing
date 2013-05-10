package RayTracing;

public class Sphere {
	public Point3d center;
	public double radius;
	public int materialIndex;
	
	public Sphere(){
		
	}
	
	public void setCenter(String x, String y, String z){
		double cx=Double.parseDouble(x);
		double cy=Double.parseDouble(y);
		double cz=Double.parseDouble(z);
		
		center=new Point3d(cx, cy, cz);
	}
	
	public void setRadius(String rad){
		radius=Double.parseDouble(rad);
	}
	
	public void setMaterial(String matID){
		materialIndex=Integer.parseInt(matID);
	}
}
