package RayTracing;

public abstract class ObjectPrimitive {
	
	abstract double getIntersection(Ray ray);
	abstract void setMaterial(Material material);
	abstract Material getMaterial();
	abstract void setMaterialIndex(String matID);
	abstract int getMaterialIndex();
	
	public Color getColorWrtIntersectedRay(Ray ray){
		return null;
	}
}
