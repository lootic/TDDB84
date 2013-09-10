package lab;

public class S_Prototype<Type extends MapSite> 
{
	private Type prototype;
	
	public S_Prototype(Type prototypeIn)
	{
		prototype = prototypeIn;
	}
	
	public Type clone()
	{
		return (Type) prototype.clone();
	}
}
