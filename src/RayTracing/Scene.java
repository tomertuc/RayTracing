package RayTracing;

import java.io.Console;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Scene implements Iterable<ObjectPrimitive>{
	
	public Camera cam;
	public Screen scr;
	public Settings setts;
	public List<Material> materials;
	public List<Sphere> spheres;
	public List<Plane> planes;
	public List<Light> lights;
	
	public Scene(){
		materials=new LinkedList<Material>();
		spheres=new LinkedList<Sphere>();
		planes=new LinkedList<Plane>();
		lights=new LinkedList<Light>();
	}
	
	
	public Color getColorOfPixel(int w, int h){
		//construct ray
		
		//find closest intersection
		
		return null;
	}
	
	public Ray getRayToPixel(int w, int h){
		return null;
	}
	
	public ObjectPrimitive findClosestIntersection(Ray ray){
		return null;
	}
	
	public void addCamera(Camera cam){
		this.cam=cam;
	}
	
	public void addScreen(Screen scr){
		this.scr=scr;
	}
	
	public void addSettings(Settings setts){
		this.setts=setts;
	}
	
	public void addMaterial(Material mtl){
		materials.add(mtl);
	}
	
	public void addSphere(Sphere sph){
		spheres.add(sph);
	}
	
	public void addPlane(Plane pln){
		planes.add(pln);
	}
	
	public void addLight(Light lgt){
		lights.add(lgt);
	}


	@Override
	public Iterator<ObjectPrimitive> iterator() {
		// TODO implement iterator over objects
		return null;
	}
}
