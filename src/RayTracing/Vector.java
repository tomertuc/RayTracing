package RayTracing;

public class Vector {
	public double x;
	public double y;
	public double z;
	
	public Vector(){
		
	}

	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;	
		this.z = z;	
	}
	
	public Vector(String x, String y, String z){
		double vx=Double.parseDouble(x);
		double vy=Double.parseDouble(y);
		double vz=Double.parseDouble(z);
		
		this.x=vx;
		this.y=vy;
		this.z=vz;
	}
	
	// Here are some static functions that do operations on vectors:
	
	public static Vector addVectors (Vector firstVector, Vector secondVector){
		Vector result = new Vector();
		
		result.x = firstVector.x + secondVector.x;
		result.y = firstVector.y + secondVector.y;
		result.z = firstVector.z + secondVector.z;
		
		return result;
	}
	
	public static Vector substractVectors (Vector firstVector, Vector secondVector){
		Vector result = new Vector();
		
		result.x = firstVector.x - secondVector.x;
		result.y = firstVector.y - secondVector.y;
		result.z = firstVector.z - secondVector.z;
		
		return result;
	}	
	
	public static double dotProductVectors (Vector firstVector, Vector secondVector){
		double result = 0;
		
		result += firstVector.x * secondVector.x;
		result += firstVector.y * secondVector.y;
		result += firstVector.z * secondVector.z;
		
		return result;
	}	
	
	public static Vector crossProductVectors (Vector firstVector, Vector secondVector){
		Vector result = new Vector();
		
		result.x = (firstVector.y * secondVector.z) - (firstVector.z * secondVector.y);
		result.y = (firstVector.x * secondVector.z) - (firstVector.z * secondVector.x);
		result.z = (firstVector.x * secondVector.y) - (firstVector.y * secondVector.x);
		
		return result;
	}	

	public static Vector multiplyVectorByScalar (Vector vector, double scalar){
		Vector result = new Vector();
		
		result.x = vector.x * scalar;
		result.y = vector.y * scalar;
		result.z = vector.z * scalar;
		
		return result;
	}

	public static double getSize (Vector vector) {
		double result = 0;
		
		result += Math.pow(vector.x, 2);
		result += Math.pow(vector.y, 2);
		result += Math.pow(vector.z, 2);
		
		return Math.sqrt(result);
	}
	
	public static Vector getNormalized (Vector vector) {
		Vector result = new Vector();
		
		double size = getSize(vector);
		result.x = result.x / size;
		result.y = result.y / size;
		result.z = result.z / size;
		
		return result;
	}	
	
}
