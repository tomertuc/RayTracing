package RayTracing;

public class Point3d {

	public double x;
	public double y;
	public double z;
	
	public Point3d(double x, double y, double z){
		this.x = x;
		this.y = y;	
		this.z = z;	
	}
	
	public Point3d(String x, String y, String z){
		double px=Double.parseDouble(x);
		double py=Double.parseDouble(y);
		double pz=Double.parseDouble(z);
		
		this.x=px;
		this.y=py;
		this.z=pz;
	}
	
	public static Point3d addPoints (Point3d point1, Point3d point2){
		return Vector.addVectors(new Vector(point1), new Vector(point2)).coordinates;
	}
	
	public static Point3d substractPoints (Point3d point1, Point3d point2){
		return Vector.substractVectors(new Vector(point1), new Vector(point2)).coordinates;
	}
	
}
