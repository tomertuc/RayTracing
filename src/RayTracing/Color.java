package RayTracing;

public class Color {
	
	public double r;
	public double g;
	public double b;
	
	public Color(String r, String g, String b){
		this.r = Double.parseDouble(r);
		this.g = Double.parseDouble(g);
		this.b = Double.parseDouble(b);
	}

}
