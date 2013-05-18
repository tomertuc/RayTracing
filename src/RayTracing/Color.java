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
	
	/*public Color add(Color c2){
		return Color.color(((Vector)this).add(c2));
	}
	
	public Color mul(double coeff){
		return Color.color(((Vector)this).mul(coeff));
	}
	
	public Color mul(Color c2){
		return Color.color(((Vector)this).mul(c2));
	}
	
	public Color sub(Color c2){
		return Color.color(((Vector)this).sub(c2));
	}*/
	
	public static byte colorComponentToByte(double c){
		if(c>1)
			c=1;
		return (byte)Math.round(c*255);
	}

}
