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
	public List<PointCloud> pointClouds;
	
	public Scene(){
		materials=new LinkedList<Material>();
		spheres=new LinkedList<Sphere>();
		planes=new LinkedList<Plane>();
		lights=new LinkedList<Light>();
		pointClouds=new LinkedList<PointCloud>();
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
	
	public void addPointCloud(PointCloud pc) {
		pointClouds.add(pc);
	}
	
	public void setMaterials(){
		for(ObjectPrimitive obj: this){
			obj.setMaterial(materials.get(obj.materialIndex-1));
		}
	}

	
	public void updateRGBdata(byte[] rgbData, int imageWidth, int imageHeight){
		for(int x=0; x<imageWidth; x++){
			for(int y=0; y<imageHeight; y++){
				Color back=setts.backgroundColor;
				rgbData[(y*imageWidth+x)*3]=Color.colorComponentToByte(back.r());
				rgbData[(y*imageWidth+x)*3+1]=Color.colorComponentToByte(back.g());
				rgbData[(y*imageWidth+x)*3+2]=Color.colorComponentToByte(back.b());
			}
		}
		
		for(PointCloud pc: pointClouds){
			
		}
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
				return currentIndexInPlanes()<planes.size();
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
