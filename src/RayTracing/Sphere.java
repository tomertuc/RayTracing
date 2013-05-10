package RayTracing;

public class Sphere {
	public Vector center;
	public double radius;
	public int materialIndex;
	
	public Sphere(){
		
	}
	
	public void setCenter(String x, String y, String z){
		center=new Vector(x, y, z);
	}
	
	public void setRadius(String rad){
		radius=Double.parseDouble(rad);
	}
	
	public void setMaterial(String matID){
		materialIndex=Integer.parseInt(matID);
	}
	
	public double getIntersection(Ray ray){
		
		double a = 1;
		double b = Vector.dotProductVectors(Vector.multiplyVectorByScalar(ray.direction, 2), Vector.substractVectors(ray.origin, center));
		double c =  Math.pow(Vector.getSize(Vector.substractVectors(ray.origin, center)), 2) - Math.pow(radius, 2);
		
		
		
		//return t
		return 0.0;
	}
}
