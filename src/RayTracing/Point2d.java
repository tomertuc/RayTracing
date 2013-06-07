package RayTracing;

public class Point2d {

	public int x;
	public int y;
	
	public Point2d(){
		
	}
	
	public Point2d(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public static Point2d outOfBounds(){
		final Point2d ret=new Point2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
		return ret;
	}
	
	public boolean isInBounds(){
		return isEqual(Point2d.outOfBounds());
	}
	
	public boolean isEqual(Point2d other){
		return x==other.x && y==other.y;
	}
}
