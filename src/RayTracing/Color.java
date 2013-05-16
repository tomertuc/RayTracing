package RayTracing;

public class Color extends Vector {
	
	//x->r, y->g, z->b
	
	public Color(){
		x=0; y=0; z=0;
	}
	
	public Color(String r, String g, String b){
		set_r(Double.parseDouble(r));
		set_g(Double.parseDouble(g));
		set_b(Double.parseDouble(b));
	}
	
	public static Color zeroColor(){
		return new Color();
	}
	
	public static Color color(Vector v){
		Color c=new Color();
		c.set_r(v.x);
		c.set_g(v.y);
		c.set_b(v.z);
		return c;
	}
	
	public double r(){
		return x;
	}
	
	public double g(){
		return y;
	}
	
	public double b(){
		return z;
	}
	
	private void set_r(double r){
		x=r;
	}
	
	private void set_g(double g){
		y=g;
	}
	
	private void set_b(double b){
		z=b;
	}

}
