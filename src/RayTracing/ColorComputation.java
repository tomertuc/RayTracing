package RayTracing;

import java.util.HashMap;
import java.util.Map;

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
		Color outputColor, diffuseColor, specularColor, illuminatedColor, reflectionColor, transColor;
		Material material=obj.getMaterial();
		double transparency=material.transparencyValue;
		
		Vector point=obj.getIntersectionPoint(ray);
		Vector normal=obj.getNormalForPoint(point);
		Map<Light, Color> diffuseColorsFromLights=new HashMap<Light, Color>();
		Map<Light, Color> specularColorsFromLights=new HashMap<Light, Color>();
		
		getDiffuseColorByIntersectedRay(obj, ray, normal, point, diffuseColorsFromLights);
		specularColor=getSpecularColorByIntersectedRay(obj, ray);
		reflectionColor=getReflectedColorByIntersectedRay(obj, ray);
		transColor=getTransColorByIntersectedRay(obj, ray);
		
		illuminatedColor=Color.color(diffuseColor.add(specularColor));
		outputColor=Color.color(transColor.mul(transparency).add(illuminatedColor.mul(1-transparency)).add(reflectionColor));
		return outputColor;
	}

	private void getDiffuseColorByIntersectedRay(ObjectPrimitive obj, Ray ray, Vector N, Vector point, Map<Light, Color> diffuseForLights){
		Material material=obj.getMaterial();
		
		Color surfaceDiffuse=material.diffuseColor;
		Color lightDiffuse;
		Color colorFromLight;
		for(Light light: scene.lights){
			lightDiffuse=light.color;
			Ray L=Ray.getRay(point, light.position);
			double cos=N.dot(L.direction);
			colorFromLight=Color.color(surfaceDiffuse.mul(lightDiffuse).mul(cos));
			diffuseForLights.put(light, colorFromLight);
		}
	}
	
	private Color getSpecularColorByIntersectedRay(ObjectPrimitive obj, Ray ray){
		Color specularColor = Color.zeroColor();
		Color materialSpecularColor = obj.material.specularColor;
		
		for(Light light: scene.lights){
				
			}
		
	
		
		
		
		return specularColor;
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
