package RayTracing;

public class ColorComputation {
	
	public Scene scene;
	
	public ColorComputation(Scene scene){
		this.scene=scene;
	}
	
	public Color getColorOfPixel(int w, int h){
		Ray ray=getRayFromCamToPixel(w,h);
		
		ObjectPrimitive intersected=findClosestIntersection(ray);
		
		Color c=getColorByIntersectedRay(intersected, ray);
		
		return c;
	}
	
	private Ray getRayFromCamToPixel(int w, int h){
		Vector startPoint=scene.cam.position;
		Vector toPoint=scene.scr.getPixelPosition(w, h);
		return Ray.getRay(startPoint, toPoint);
	}
	
	private ObjectPrimitive findClosestIntersection(Ray ray){
		return findClosestIntersection(ray, null);
	}
	
	private ObjectPrimitive findClosestIntersection(Ray ray, ObjectPrimitive ignored){
		double t, t_min=Double.MAX_VALUE;
		ObjectPrimitive obj_min_intr=null;
		for(ObjectPrimitive obj: scene){
			if(obj==ignored)
				continue;
			t=obj.getIntersection(ray);
			if(t<t_min){
				t_min=t;
				obj_min_intr=obj;
			}
		}
		return obj_min_intr;
	}
	
	private Color getColorByIntersectedRay(ObjectPrimitive obj, Ray ray){
		Color outputColor, diffuseColor, specularColor, illuminatedColor, reflectionColor, backgroundColor;
		Material material=obj.getMaterial();
		double transparency=0;
		diffuseColor=getDiffuseColorOfObject(obj);
		specularColor=getSpecularColorByIntersectedRay(obj, ray);
		reflectionColor=getReflectedColorByIntersectedRay(obj, ray);
		backgroundColor=getTransColorByIntersectedRay(obj, ray);
		illuminatedColor=Color.color(diffuseColor.add(specularColor));
		outputColor=Color.color(backgroundColor.mul(transparency).add(illuminatedColor.mul(1-transparency)).add(reflectionColor));
		return outputColor;
	}

	private Color getDiffuseColorOfObject(ObjectPrimitive obj){
		return null;
	}
	
	private Color getSpecularColorByIntersectedRay(ObjectPrimitive obj, Ray ray){
		return null;
	}
	
	private Color getTransColorByIntersectedRay(ObjectPrimitive obj, Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	private Color getReflectedColorByIntersectedRay(ObjectPrimitive obj, Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
