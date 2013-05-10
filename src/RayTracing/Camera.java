package RayTracing;

public class Camera {
	public Vector position;
	public Vector lookatpoint;
	public Vector up;
	public double scrDist;
	public double scrWidth;
	
	public Vector Vx;
	public Vector Vy;
	public Vector Vz;
	
	public Camera(){
		
	}
	
	public void setPosition(String x, String y, String z){
		position=new Vector(x,y,z);
	}
	
	public void setLookAtPoint(String x, String y, String z){
		lookatpoint=new Vector(x,y,z);
	}
	
	public void setUpVector(String x, String y, String z){
		up=new Vector(x,y,z);
	}
	
	public void setScreenDistance(String d){
		scrDist=Double.parseDouble(d);
	}
	
	public void setScreenWidth(String w){
		scrWidth=Double.parseDouble(w);
	}
	
	public void computeCoordinateSystem(){
		//define Vx, Vy, Vz
		//based on Views & Projections presentation, pages 1-2
		Vector lookAtVector=lookatpoint.sub(position);
		Vector w=lookAtVector.normalize();
		Vector u=up.cross(w).normalize();
		Vector v=w.cross(u);
		
		Vz=w;
		Vy=v;
		Vx=u;
	}
	
	public Screen getScreenFromCamera(int imageWidth, int imageHeight){
		//compute screen
		//based on Ray Casting presentation, page 16
		Screen screen=new Screen(Vx, Vy, Vz);
		
		Vector E=position;
		double f=scrDist;
		double w=((double)imageWidth)/2;
		double h=((double)imageHeight)/2;
		
		Vector P=E.add(Vz.mul(f));
		Vector P0=P.sub(Vx.mul(w)).sub(Vy.mul(h));
		
		screen.setOrigin(P0);
		screen.setWidth(imageWidth);
		screen.setHeight(imageHeight);
		
		return screen;
	}
}
