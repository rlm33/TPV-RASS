package principal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class SalidaXML implements Salida{
	
	private File xmlFile;
	private BufferedWriter writer;
	
	public SalidaXML(String fichero) throws IOException{
		xmlFile = new File(fichero);
		writer = new BufferedWriter(new FileWriter(xmlFile));		
	}

	@Override
	public void setVenta(Venta venta) {
		DecimalFormat df = new DecimalFormat("0.00");
		String xmlCode = "<ticket>\n";
		if(venta != null){			
			for(LinVenta v : venta.getLinventas()){
				String tab = "\t\t";
				xmlCode += "\t<linTicket>\n";
				xmlCode += tab + "<descr>" + v.getProducto().getDescripcion() + "</descr>\n";
				xmlCode += tab + "<cant>" + v.getCantidad() + "</cant>\n";
				xmlCode += tab + "<pUnit>" + df.format(v.getProducto().getPvp()) + "</pUnit>\n";
				xmlCode += tab + "<dctoLin>" + df.format(venta.getDescuentoLin(v.getProducto().getPvp())*v.getCantidad()) + "</dctoLin>\n";
				xmlCode += "\t</linTicket>\n";				
			}
			xmlCode += "\t<totalAPagar cant=\"" + df.format(venta.subtotal()) + "\"/>\n";
			xmlCode += "\t<dctoAcumulado cant=\"" + df.format(venta.getDescuentoAcumulado()*venta.subtotal()/100) + "\"/>\n";
			xmlCode += "\t<impuestos>cant=\"" + df.format(venta.getImpuestos()) + "\"/>\n";
		}
		
		xmlCode += "</ticket>";
		try {
			writer.write(xmlCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
