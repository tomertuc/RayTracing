package RayTracing;

public class Sphere extends ObjectPrimitive{
	public Vector center;
	public double radius;
	//let O=center and r=radius, then sphere is |P-O|^2-r^2=0
	
	public Sphere(){
		
	}
	
	public void setCenter(String x, String y, String z){
		center=new Vector(x, y, z);
	}
	
	public void setRadius(String rad){
		radius=Double.parseDouble(rad);
	}
	
	// based on Ray Casting presentation, page 6
	// returns the closest intersection t (-1 in case of no intersection)
	public double getIntersection(Ray ray){
		
		double a = 1;
		double b = ray.direction.mul(2).dot(ray.origin.sub(center));
		double c = Math.pow(ray.origin.sub(center).abs(), 2) - Math.pow(radius, 2);
		double delta = (Math.pow(b, 2) - 4*a*c);
		double first_result, second_result;
		
		if (delta < 0)//no solution -> no intersection
			return -1;
		else if (delta == 0)
		{
			first_result = -b/(2*a);
			if (first_result < 0)//if only result is negative, there's no intersection
				return -1;
			return first_result;//if only result is non-negative, it is returned
		}
		else
		{
			first_result = (-b + Math.sqrt(delta))/(2*a);
			second_result = (-b - Math.sqrt(delta))/(2*a);	
			if ((first_result < 0) && (second_result < 0))//if both negative, there's no intersection
				return -1;
			else
			{
				//we are here only if at least one of the results is non-negative
				if((first_result<0) || (second_result<0))//if one result negative, return the non-negative intersection
					return Math.max(first_result, second_result);
				return Math.min(first_result, second_result);//if both are non-negative, return the closest intersection
			}
		}
	}

	@Override
	Vector getNormalForPoint(Vector point) {
		return point.sub(center).normalize();
	}
}
