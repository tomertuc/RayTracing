package RayTracing;

public abstract class ObjectPrimitive {
	
	public int materialIndex;
	public Material material;
	public Color diffuseColor=null;
	
	abstract double getIntersection(Ray ray);
	abstract Vector getNormalForPoint(Vector point);
	
	public void setMaterialIndex(String matID){
		materialIndex=Integer.parseInt(matID);
	}
	
	public int getMaterialIndex() {
		return materialIndex;
	}
	
	public void setMaterial(Material material) {
		this.material=material;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public Vector getIntersectionPoint(Ray ray){
		double t=getIntersection(ray);
		return ray.get_t_on_ray(t);
	}
}
