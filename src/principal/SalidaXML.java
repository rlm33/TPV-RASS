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
		float dctoAcu = 0.0f;
		DecimalFormat df = new DecimalFormat("0.00");
		String xmlCode = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>\n";
		if(venta != null){		
			xmlCode += "<ticket>\n";
			for(LinVenta v : venta.getLinventas()){
				float cant = 0, pUnit = 0, dctoLin = 0;
				String tab = "\t\t";
				xmlCode += "\t<linTicket>\n";
				xmlCode += tab + "<descr>" + v.getProducto().getDescripcion() + "</descr>\n";
				xmlCode += tab + "<cant>" + v.getCantidad() + "</cant>\n";
				cant = v.getCantidad();
				xmlCode += tab + "<pUnit>" + df.format(v.getProducto().getPvp()) + "</pUnit>\n";
				pUnit = v.getProducto().getPvp();
				xmlCode += tab + "<dctoLin>" + df.format(venta.getDescuentoLin(v.getProducto().getPvp())*v.getCantidad()) + "</dctoLin>\n";
				dctoLin = venta.getDescuentoLin(v.getProducto().getPvp())*v.getCantidad();
				xmlCode += tab + "<pLin>" + df.format(pUnit * cant - dctoLin) + "</pLin>\n";
				dctoAcu += venta.getDescuentoLin(v.getProducto().getPvp())*v.getCantidad();
				xmlCode += "\t</linTicket>\n";				
			}
			if(venta.getLinventas().size() > 0){
				xmlCode += "\t<totalAPagar cant=\"" + df.format(venta.subtotal()) + "\"/>\n";
				xmlCode += "\t<dctoAcumulado cant=\"" + df.format(dctoAcu) + "\"/>\n";
				xmlCode += "\t<impuestos cant=\"" + df.format(venta.getImpuestos()) + "\"/>\n";
			}
			xmlCode += "</ticket>";
		}
		
		
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
