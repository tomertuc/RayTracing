package RayTracing;

public class Settings {
	public Color backgroundColor;
	public int rootNumberOfShadowRays;
	public int maximumNumberOfRecursions;
	
	public Settings(){
		
	}

	public void setBackgroundColor(String r, String g, String b){
		backgroundColor=new Color(r, g, b);
	}
	
	public void setRootNumberOfShadowRays(String numShadows){
		rootNumberOfShadowRays = Integer.parseInt(numShadows);
	}
	
	public void setMaximumNumberOfRecursions(String maxRec){
		maximumNumberOfRecursions = Integer.parseInt(maxRec);
	}	
}
