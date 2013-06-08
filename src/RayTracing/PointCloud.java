package RayTracing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import Jama.*; 

public class PointCloud {
	
	public File file;
	public double size;
	public Color color;
	public Vector[] pointArray;
	public Vector[] boundingPointArray;
	
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
	
	public static Color getBoundingBoxColor(){
		Color color = new Color("0", "0", "1");
		return color;
	}

	public static double getBoundingBoxDotSize(){
		return 7.0;
	}
	
	public void readPoints() throws IOException{
		FileReader fr = new FileReader(file);

		BufferedReader r = new BufferedReader(fr);
		String line = null;
		System.out.println("Started parsing scene file " + file);
		int numberOfPoints=0;

		while ((line = r.readLine()) != null)
		{
			line = line.trim();

			if (line.isEmpty() || (line.startsWith("comment")))
			{  // This line in the scene file is a comment
				continue;
			}
			else if(line.startsWith("element vertex ")){
				numberOfPoints=Integer.parseInt(line.substring(15));
			}
			else if(line.startsWith("end_header")){
				break;
			}
		}
		pointArray=new Vector[numberOfPoints];
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
				
				String[] parts = line.trim().toLowerCase().split("\\s+");
				double x=Double.parseDouble(parts[0]);
				double y=Double.parseDouble(parts[1]);
				double z=Double.parseDouble(parts[2]);
				
				pointArray[i]=new Vector(x,y,z);
				i++;
			}
		} 
		r.close();
	}
	
	public void computeBoundingBox() {
		// Perform PCA using SVD.
		
		Vector sumOfPoints = new Vector(0,0,0);	
		for (int i=0; i<pointArray.length; ++i){
			sumOfPoints.add(pointArray[i]);
		}
		
		Vector averagePoint = sumOfPoints.mul(1/pointArray.length);

		Vector[] zeroCenteredPointArray;
		if (pointArray.length < 1000){
			zeroCenteredPointArray = new Vector[pointArray.length];
			for (int i=0; i<pointArray.length; ++i){
				zeroCenteredPointArray[i] = pointArray[i].sub(averagePoint);
			}
		}
		else{
			zeroCenteredPointArray = new Vector[1000];
			float offsetToJump = (pointArray.length - 1)/1000;
			for (int i=0; i<1000; ++i){
				zeroCenteredPointArray[i] = pointArray[Math.round(i*offsetToJump)].sub(averagePoint);
			}
		}
		
		double[][] zeroCenteredDoubleArray = new double[zeroCenteredPointArray.length][3];
		for (int i=0; i<zeroCenteredPointArray.length; ++i){
			zeroCenteredDoubleArray[i] = zeroCenteredPointArray[i].getArrayOfCoordinates();
		}
		
		// Perform SVD using the JAMA library.
		Matrix zeroCenteredMatrix = new Matrix(zeroCenteredDoubleArray); 
        SingularValueDecomposition singularValueDecomposition = zeroCenteredMatrix.svd();
        Matrix U = singularValueDecomposition.getU();
        
        // Find the final 3 PCA vectors.
        double[][] PCAResultDoubleArray = U.getArrayCopy();
        Vector[] PCAVectorsArray = new Vector[3];
		for (int i=0; i<3; ++i){
			Vector PCAVector = new Vector(PCAResultDoubleArray[i]);
			PCAVectorsArray[i] = PCAVector.normalize();
		}
        
		// Compute the 8 corner points of the bounding box.
		double[][] sizesOfBoundingBox = new double[3][2];
        for (int i=0; i<3; ++i){
        	for (int j=0; j<zeroCenteredPointArray.length; ++j){
        		double pointDotPCAVector = zeroCenteredPointArray[j].dot(PCAVectorsArray[i]);
        		if (pointDotPCAVector > sizesOfBoundingBox[i][0]){
        			sizesOfBoundingBox[i][0] = pointDotPCAVector;
        		}
        		else if (pointDotPCAVector < sizesOfBoundingBox[i][1]){
        			sizesOfBoundingBox[i][1] = pointDotPCAVector;
        		}
        	}
        }
        
        boundingPointArray = new Vector[8];        
        for (int i=0; i<2; ++i){
        	for (int j=0; j<2; ++j){
        		for (int k=0; k<2; ++k){
        			boundingPointArray[4*i+2*j+k] = PCAVectorsArray[0].mul(sizesOfBoundingBox[0][i]).add(
        											PCAVectorsArray[1].mul(sizesOfBoundingBox[1][j]).add(
        											PCAVectorsArray[2].mul(sizesOfBoundingBox[2][k]).add(
        											averagePoint)));
        		}
        	}
        }	
	}
}
