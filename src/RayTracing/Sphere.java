package RayTracing;

public class Sphere {
	public Point3d center;
	public double radius;
	public int materialIndex;
	
	public Sphere(){
		
	}
	
	public void setCenter(String x, String y, String z){
		center=new Point3d(x, y, z);
	}
	
	public void setRadius(String rad){
		radius=Double.parseDouble(rad);
	}
	
	public void setMaterial(String matID){
		materialIndex=Integer.parseInt(matID);
	}
	
	public double getIntersection(Ray ray){
		
		double a = 1;
		double b = dotProductVectors(Vector.multiplyVectorByScalar(ray.direction, 2), Vector.substractVectors(ray.origin, center));
				
		
		
		
		//return t
		return 0.0;
	}
}
