package RayTracing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PointCloud {
	
	public File file;
	public double size;
	public Color color;
	public Vector[] pointArray;
	
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
	
	public void readPoints() throws IOException{
		FileReader fr = new FileReader(file);

		BufferedReader r = new BufferedReader(fr);
		String line = null;
		System.out.println("Started parsing scene file " + file);
		int size=0;

		while ((line = r.readLine()) != null)
		{
			line = line.trim();

			if (line.isEmpty() || (line.startsWith("comment")))
			{  // This line in the scene file is a comment
				continue;
			}
			else if(line.startsWith("element vertex ")){
				size=Integer.parseInt(line.substring(15));
			}
			else if(line.startsWith("end_header")){
				break;
			}
		}
		size=size/1000;
		pointArray=new Vector[size];
		int i=0;
		while ((line = r.readLine()) != null)
		{
			line = line.trim();

			if (line.isEmpty() || (line.startsWith("comment")))
			{  // This line in the scene file is a comment
				continue;
			}
			else
			{
				if(i%1000!=0){
					i++;
					continue;
				}
				
				String[] parts = line.trim().toLowerCase().split("\\s+");
				double x=Double.parseDouble(parts[0]);
				double y=Double.parseDouble(parts[1]);
				double z=Double.parseDouble(parts[2]);
				
				pointArray[i]=new Vector(x,y,z);
				i++;
			}
		} 
	}
	
}
