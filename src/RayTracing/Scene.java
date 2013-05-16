package RayTracing;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class Scene {
	
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
	
	public void testCamera(){
		System.out.println(cam.Vx.x);
	}
}
