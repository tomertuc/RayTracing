package RayTracing;

public class Material {
	public Color diffuseColor;
	public Color specularColor;
	public Color reflectionColor;
	public double phongSpecularityCoefficient;
	public double transparencyValue;
	public double incidence;
	
	public Material(){
		
	}
	
	public void setDiffuseColor(String r, String g, String b){
		diffuseColor=new Color(r, g, b);
	}

	public void setSpecularColor(String r, String g, String b){
		specularColor=new Color(r, g, b);
	}
	
	public void setReflectionColor(String r, String g, String b){
		reflectionColor=new Color(r, g, b);
	}
	
	
	public void setPhongSpecularityCoefficient(String phongCoef){
		phongSpecularityCoefficient=Double.parseDouble(phongCoef);
	}
	
	public void setTransparencyValue(String transpVal){
		transparencyValue=Double.parseDouble(transpVal);
	}
	
	public void setIncidence(String incVal){
		incidence=Double.parseDouble(incVal);
	}
	
	public boolean isTransparent(){
		return transparencyValue!=0;
	}
	
	public boolean isReflective(){
		return !reflectionColor.isEqual(Color.zeroColor());
	}

}
