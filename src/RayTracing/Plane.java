package RayTracing;

public class Plane {
	public Vector normal;
	public double offset;
	public int materialIndex;
	
	public Plane(){
		
	}
	
	public void addNormal(String x, String y, String z){		
		normal=new Vector(x, y, z);
	}
	
	public void addOffset(String o){
		offset=Double.parseDouble(o);
	}
	
	public void addMaterial(String m){
		materialIndex=Integer.parseInt(m);
	}
}
