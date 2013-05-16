package RayTracing;

public abstract class ObjectPrimitive {
	
	public int materialIndex;
	public Material material;
	public Scene scene;
	
	abstract double getIntersection(Ray ray);
	
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
	
	public void setScene(Scene scene){
		this.scene=scene;
	}
	
	public Scene getScene(){
		return scene;
	}
	
	public Color getColorWrtIntersectedRay(Ray ray){
		return null;
	}
}
