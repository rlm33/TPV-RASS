package utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Es la encargada de obtener las distintas propiedades del fichero "Propiedades TPV".
 * Para obtenerlos, lo haremos mediante la función de GET y pasándole la posición del atributo que queremos obtener
 * @author RASS-TPV
 *
 */
public class Propiedades {

	private static Propiedades prop=null;
	private final String path="TPV.properties";
	private Properties propiedades;
	
	private Propiedades() throws IOException
	{
		
		FileInputStream file = null;
		file = new FileInputStream(path);
		propiedades = new Properties();
		propiedades.load(file);
		file.close();
				
	}
	
	public static Propiedades getInstancia() throws IOException
	{
		if(prop==null)
		 prop= new Propiedades();	
		return prop;
	}

	public Properties getPropiedades()
        {
		return propiedades;
	}

    public static String getProperty(String p) throws IOException
    {
        return getInstancia().getPropiedades().getProperty(p);
    }
	
}
