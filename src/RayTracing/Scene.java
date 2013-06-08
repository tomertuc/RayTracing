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
				this.doSingleRGBupdate(rgbData, x, y, imageWidth, imageHeight, back);
			}
		}
		
		for(PointCloud pc: pointClouds){
			for(int i=0; i<pc.boundingPointArray.length; i++){
				Pixel center=getXandYofPointOnImage(pc.boundingPointArray[i], imageWidth, imageHeight);
				if(center.isInBounds()){
					double distanceFromCamera=pc.boundingPointArray[i].distance(cam.position);
					double radius=pc.size/distanceFromCamera;
					List<Pixel> intersectedPixels=center.getAllPixelsAroundThisPixel(radius, scr);
					for(Pixel ip: intersectedPixels){
						this.doSingleRGBupdate(rgbData, ip.x, ip.y, imageWidth, imageHeight, PointCloud.getBoundingBoxColor());
					}
				}
			}
			for(int i=0; i<pc.pointArray.length; i++){
				Pixel center=getXandYofPointOnImage(pc.pointArray[i], imageWidth, imageHeight);
				if(center.isInBounds()){
					double distanceFromCamera=pc.pointArray[i].distance(cam.position);
					double radius=pc.size/distanceFromCamera;
					List<Pixel> intersectedPixels=center.getAllPixelsAroundThisPixel(radius, scr);
					for(Pixel ip: intersectedPixels){
						this.doSingleRGBupdate(rgbData, ip.x, ip.y, imageWidth, imageHeight, pc.color);
					}
				}
			}
		}
		
	}
	
	private void doSingleRGBupdate(byte[] rgbData, int x, int y, int imageWidth, int imageHeight, Color c){
		rgbData[(y*imageWidth+x)*3]=Color.colorComponentToByte(c.r());
		rgbData[(y*imageWidth+x)*3+1]=Color.colorComponentToByte(c.g());
		rgbData[(y*imageWidth+x)*3+2]=Color.colorComponentToByte(c.b());
	}
	
	private Pixel getXandYofPointOnImage(Vector point, int imageWidth, int imageHeight){
		Pixel xAndy=new Pixel(imageWidth, imageHeight);
		Ray rayToPoint=Ray.getRayNotNormalized(cam.position, point);
		double ratio=rayToPoint.direction.dot(cam.Vz)/cam.scrDist;
		Vector pointOnScreen=cam.position.add(rayToPoint.direction.mul(1/ratio));
		
		Vector fromCenterToPoint=pointOnScreen.sub(scr.origin);
		
		double distX=fromCenterToPoint.dot(cam.Vx);
		double xRatio=distX/scr.screen_width;
		int x;
		if(xRatio>1 || xRatio<-1){
			double relativePlacement=(xRatio+1)/2;
			double doublePixel=relativePlacement*imageWidth;
			x=(int)Math.round(doublePixel);
			if(x==imageWidth)
				return Pixel.outOfBounds();
			//else x in [0, imageWidth-1]
		}
		else
			return Pixel.outOfBounds();
		
		double distY=fromCenterToPoint.dot(cam.Vy);
		double yRatio=distY/scr.screen_height;
		int y;
		if(yRatio>1 || yRatio<-1){
			double relativePlacement=(yRatio+1)/2;
			double doublePixel=relativePlacement*imageWidth;
			y=(int)Math.round(doublePixel);
			if(y==imageHeight)
				return Pixel.outOfBounds();
			//else y in [0, imageHeight-1]
		}
		else
			return Pixel.outOfBounds();
		
		xAndy.x=x;
		xAndy.y=y;
		
		return xAndy;
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
