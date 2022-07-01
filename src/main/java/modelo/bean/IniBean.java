package modelo.bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class IniBean 
{
	private static BLFacade blInt = new BLFacadeImplementation();
	
	public static BLFacade getBusinessLogic()
	{
		return blInt;
	}
}
