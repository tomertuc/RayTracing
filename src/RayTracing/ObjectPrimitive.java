package RayTracing;

public interface ObjectPrimitive {
	
	public double getIntersection(Ray ray);
	public void setMaterial(Material material);
	public Material getMaterial();
	public void setMaterialIndex(String matID);
	public int getMaterialIndex();
}
