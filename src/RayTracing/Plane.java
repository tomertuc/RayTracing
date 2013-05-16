package RayTracing;

public class Plane extends ObjectPrimitive{
	public Vector normal;
	public double offset;
	public int materialIndex;
	public Material material;
	//let N=normal and d=offset, then plane is P*N + d = 0
	
	public Plane(){
		
	}
	
	public void setNormal(String x, String y, String z){		
		normal=new Vector(x, y, z);
	}
	
	public void setOffset(String o){
		offset=Double.parseDouble(o);
	}
	
	@Override
	public void setMaterialIndex(String matID){
		materialIndex=Integer.parseInt(matID);
	}
	
	@Override
	public int getMaterialIndex() {
		return materialIndex;
	}
	
	@Override
	public void setMaterial(Material material) {
		this.material=material;
	}

	@Override
	public Material getMaterial() {
		return material;
	}

	
	// based on Ray Casting presentation, page 9
	// returns the closest intersection t (-1 in case of no intersection)	
	public double getIntersection(Ray ray){
	
			double t = -(ray.origin.dot(normal) + offset) / (ray.direction.dot(normal));
			
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
