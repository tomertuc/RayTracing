package RayTracing;

public class Vector {
	public double x;
	public double y;
	public double z;
	
	public Vector(){
		
	}
	
	public Vector(Vector other){
		this.x=other.x;
		this.y=other.y;
		this.z=other.z;
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
	
	public void setVector(Vector other){
		this.x=other.x;
		this.y=other.y;
		this.z=other.z;
	}
	
	public boolean isEqual(Vector other){
		return x==other.x && y==other.y && z==other.z;
	}
	
	// Here are some functions that do operations on vectors:
	
	public Vector add (Vector secondVector){
		Vector result = new Vector();
		
		result.x = this.x + secondVector.x;
		result.y = this.y + secondVector.y;
		result.z = this.z + secondVector.z;
		
		return result;
	}
	
	public Vector sub (Vector secondVector){
		Vector result = new Vector();
		
		result.x = this.x - secondVector.x;
		result.y = this.y - secondVector.y;
		result.z = this.z - secondVector.z;
		
		return result;
	}	
	
	public double dot (Vector secondVector){
		double result = 0;
		
		result += this.x * secondVector.x;
		result += this.y * secondVector.y;
		result += this.z * secondVector.z;
		
		return result;
	}	
	
	public Vector cross (Vector secondVector){
		Vector result = new Vector();
		
		result.x = (this.y * secondVector.z) - (this.z * secondVector.y);
		result.y = (this.z * secondVector.x) - (this.x * secondVector.z);
		result.z = (this.x * secondVector.y) - (this.y * secondVector.x);
		
		return result;
	}	

	public Vector mul (double scalar){
		Vector result = new Vector();
		
		result.x = this.x * scalar;
		result.y = this.y * scalar;
		result.z = this.z * scalar;
		
		return result;
	}
	
	//multiply index by index
	public Vector mul (Vector secondVector){
		Vector result=new Vector();
		
		result.x=this.x*secondVector.x;
		result.y=this.y*secondVector.y;
		result.z=this.z*secondVector.z;
		
		return result;
	}

	public double abs () {
		double result = 0;
		
		result += Math.pow(x, 2);
		result += Math.pow(y, 2);
		result += Math.pow(z, 2);
		
		return Math.sqrt(result);
	}
	
	public Vector normalize () {
		Vector result = new Vector();
		
		double size = abs();
		if(size==0)
			return this;
		result.x = this.x / size;
		result.y = this.y / size;
		result.z = this.z / size;
		
		return result;
	}	
	
}
