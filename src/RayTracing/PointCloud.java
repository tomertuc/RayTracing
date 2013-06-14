package RayTracing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import Jama.*; 
import org.jblas.*;

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
		System.out.println("Started parsing ply file " + file);
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
		System.out.println("Finished parsing ply file " + file);
		r.close();
	}
	
	public void computeBoundingBox() {
		// Perform PCA using SVD.

		int numberOfPoints=1000;
		
		Vector[] samplePointArray;
		if (pointArray.length < numberOfPoints){
			samplePointArray = pointArray;
		}
		else{
			samplePointArray = new Vector[numberOfPoints];
			float offsetToJump = (float)Math.floor(((float)pointArray.length - 1)/(float)numberOfPoints);
			for (int i=0; i<numberOfPoints; ++i){
				samplePointArray[i] = pointArray[Math.round(i*offsetToJump)];
			}
		}

		Vector sumOfSamplePoints = new Vector(0,0,0);	
		for (int i=0; i<samplePointArray.length; ++i){
			sumOfSamplePoints=sumOfSamplePoints.add(samplePointArray[i]);
		}

		Vector averageSamplePoint = sumOfSamplePoints.mul(1/(float)samplePointArray.length);

		Vector[] zeroCenteredPointArray =  new Vector[samplePointArray.length];;
		for (int i=0; i<samplePointArray.length; ++i){
			zeroCenteredPointArray[i] = samplePointArray[i].sub(averageSamplePoint);
		}


		double[][] zeroCenteredDoubleArray = new double[zeroCenteredPointArray.length][3];
		for (int i=0; i<zeroCenteredPointArray.length; ++i){
			zeroCenteredDoubleArray[i] = zeroCenteredPointArray[i].getArrayOfCoordinates();
		}

		// Perform SVD using the JAMA library.
		Matrix zeroCenteredMatrix = new Matrix(zeroCenteredDoubleArray);
		SingularValueDecomposition svd=zeroCenteredMatrix.svd();
		Matrix U=svd.getV();
		//U = zeroCenteredMatrix.times(zeroCenteredMatrix.transpose()).eig().getV();
		double[][] PCAResultDoubleArray = U.transpose().getArrayCopy();		 
		
		/*DoubleMatrix zeroCenteredMatrix=new DoubleMatrix(zeroCenteredDoubleArray).transpose(); 
        DoubleMatrix[] singularValueDecomposition = Singular.fullSVD(zeroCenteredMatrix);
        DoubleMatrix U = singularValueDecomposition[0];
        double[][] PCAResultDoubleArray = U.transpose().toArray2();*/
        
        // Find the final 3 PCA vectors.
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
        											averageSamplePoint)));
        			System.out.println(boundingPointArray[4*i+2*j+k].toString());
        		}
        	}
        }	
        System.out.println();
	}
}
