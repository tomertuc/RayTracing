package RayTracing;

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
		Ray ray=getRayToPixel(w,h);
		
		ObjectPrimitive intersected=findClosestIntersection(ray);
		
		//...
		
		return null;
	}
	
	public Ray getRayToPixel(int w, int h){
		Ray ray=new Ray();
		Vector direction=scr.getPixelPosition(w, h).sub(cam.position);
		ray.setDirection(direction);
		ray.setOrigin(cam.position);
		return ray;
	}
	
	public ObjectPrimitive findClosestIntersection(Ray ray){
		double t, t_min=Double.MAX_VALUE;
		ObjectPrimitive obj_min_intr=null;
		for(ObjectPrimitive obj: this){
			t=obj.getIntersection(ray);
			if(t<t_min){
				t_min=t;
				obj_min_intr=obj;
			}
		}
		return obj_min_intr;
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
		sph.setMaterial(materials.get(sph.materialIndex-1));
		spheres.add(sph);
	}
	
	public void addPlane(Plane pln){
		pln.setMaterial(materials.get(pln.materialIndex-1));
		planes.add(pln);
	}
	
	public void addLight(Light lgt){
		lights.add(lgt);
	}


	@Override
	public Iterator<ObjectPrimitive> iterator() {
		Iterator<ObjectPrimitive> it=new Iterator<ObjectPrimitive>(){
			//we first iterate over spheres and then over planes
			
			private int currentIndex=0;
			
			public boolean isCurrentInSpheres(){
				return currentIndex<spheres.size();
			}
			
			//if not in spheres - current index in planes
			//if in spheres - returns negative
			public int currentIndexInPlanes(){
				return currentIndex-spheres.size();
			}
			
			@Override
			public boolean hasNext() {
				return currentIndexInPlanes()>=planes.size();
			}

			@Override
			public ObjectPrimitive next() {
				if(isCurrentInSpheres())
					return spheres.get(currentIndex++);
				else{
					Plane ret=planes.get(currentIndexInPlanes());
					currentIndex++;
					return ret;
				}
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		};
		return it;
	}
}
