package RayTracing;

public class Light {
	public Point3d position;
	public Color color;
	public double specularI;
	public double shadowsI;
	public double radius;
	
	public Light(){
		
	}
	
	public void setPosition(String x, String y, String z){
		position=new Point3d(x, y, z);
	}
	
	public void setColor(String r, String g, String b){
		color=new Color(r, g, b);
	}
	
	public void setSpecularIntensity(String spi){
		specularI=Double.parseDouble(spi);
	}
	
	public void setShadowIntensity(String shi){
		shadowsI=Double.parseDouble(shi);
	}
	
	public void setRadius(String r){
		radius=Double.parseDouble(r);
	}
}
