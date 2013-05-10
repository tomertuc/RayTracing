package RayTracing;

public class Plane implements ObjectPrimitive{
	public Vector normal;
	public double offset;
	public int materialIndex;
	
	public Plane(){
		
	}
	
	public void setNormal(String x, String y, String z){		
		normal=new Vector(x, y, z);
	}
	
	public void setOffset(String o){
		offset=Double.parseDouble(o);
	}
	
	public void setMaterialIndex(String m){
		materialIndex=Integer.parseInt(m);
	}
	
	public double getIntersection(Ray ray){
		//return t
		return 0.0;
	}
}
