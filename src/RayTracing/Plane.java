package RayTracing;

public class Plane {
	public Vector normal;
	public double offset;
	public int materialIndex;
	
	public Plane(){
		
	}
	
	public void addNormal(String x, String y, String z){
		double nx=Double.parseDouble(x);
		double ny=Double.parseDouble(y);
		double nz=Double.parseDouble(z);
		
		normal=new Vector(nx, ny, nz);
	}
	
	public void addOffset(String o){
		offset=Double.parseDouble(o);
	}
	
	public void addMaterial(String m){
		materialIndex=Integer.parseInt(m);
	}
}
