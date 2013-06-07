package RayTracing;

import java.io.File;

public class PointCloud {
	
	public File file;
	public double size;
	public Color color;
	
	public PointCloud(){	
	}
	
	public void setFile(File file){
		this.file=file;
	}
	
	public void setSize(String size){
		this.size = Double.parseDouble(size);	
	}	
	
	public void setColor(String r, String g, String b){
		this.color = new Color(r,g,b);
	}
}
