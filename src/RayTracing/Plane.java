package RayTracing;

public class Plane extends ObjectPrimitive{
	public Vector normal;
	public double offset;
	public Scene scene;
	//let N=normal and d=offset, then plane is P*N - d = 0
	
	public Plane(){
		
	}
	
	public String getType(){
		return "plane";
	}
	
	public void setNormal(String x, String y, String z){		
		normal=new Vector(x, y, z);
	}
	
	@Override
	public Vector getNormalForPoint(Vector point){
		return normal; 
	}
	
	public void setOffset(String o){
		offset=Double.parseDouble(o);
	}
	
	// based on Ray Casting presentation, page 9
	// returns the closest intersection t (-1 in case of no intersection)	
	public double getIntersection(Ray ray){
	
			double t = -(ray.origin.dot(normal) - offset) / (ray.direction.dot(normal));
			
			if (t < 0)
			{
				return -1;
			}
			else
			{
				return t;
			}
	}
}
