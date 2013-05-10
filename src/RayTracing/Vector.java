package RayTracing;

public class Vector {
	public Point3d coordinates;
	
	public Vector(){
		
	}
	
	public Vector(String x, String y, String z){
		coordinates=new Point3d(x,y,z);
	}
	
	// Here are some static functions that do operations on vectors:
	
	public static Vector addVectors (Vector firstVector, Vector secondVector){
		Vector result = new Vector();
		
		result.coordinates.x = firstVector.coordinates.x + secondVector.coordinates.x;
		result.coordinates.y = firstVector.coordinates.y + secondVector.coordinates.y;
		result.coordinates.z = firstVector.coordinates.z + secondVector.coordinates.z;
		
		return result;
	}
	
	public static Vector subtractVectors (Vector firstVector, Vector secondVector){
		Vector result = new Vector();
		
		result.coordinates.x = firstVector.coordinates.x - secondVector.coordinates.x;
		result.coordinates.y = firstVector.coordinates.y - secondVector.coordinates.y;
		result.coordinates.z = firstVector.coordinates.z - secondVector.coordinates.z;
		
		return result;
	}	
	
	public static double dotProductVectors (Vector firstVector, Vector secondVector){
		double result = 0;
		
		result += firstVector.coordinates.x * secondVector.coordinates.x;
		result += firstVector.coordinates.y * secondVector.coordinates.y;
		result += firstVector.coordinates.z * secondVector.coordinates.z;
		
		return result;
	}	
	
	public static Vector crossProductVectors (Vector firstVector, Vector secondVector){
		Vector result = new Vector();
		
		result.coordinates.x = (firstVector.coordinates.y * secondVector.coordinates.z) - (firstVector.coordinates.z * secondVector.coordinates.y);
		result.coordinates.y = (firstVector.coordinates.x * secondVector.coordinates.z) - (firstVector.coordinates.z * secondVector.coordinates.x);
		result.coordinates.z = (firstVector.coordinates.x * secondVector.coordinates.y) - (firstVector.coordinates.y * secondVector.coordinates.x);
		
		return result;
	}	

	public static Vector multiplyVectorByScalar (Vector vector, double scalar){
		Vector result = new Vector();
		
		result.coordinates.x = vector.coordinates.x * scalar;
		result.coordinates.y = vector.coordinates.y * scalar;
		result.coordinates.z = vector.coordinates.z * scalar;
		
		return result;
	}
	
}
