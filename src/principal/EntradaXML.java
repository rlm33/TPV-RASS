package principal;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
 
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class EntradaXML extends Entrada {


	private DiaDescuento diaDescuento = new DiaDescuento();
	private ArrayList<LinVenta> linVentas = new ArrayList<LinVenta>();
	private Tarjeta tarjeta = new Tarjeta();
	private boolean empleado = false;

	public EntradaXML(String fic){
		  try{ 
			  
			     SAXParserFactory factory = SAXParserFactory.newInstance();
			     SAXParser saxParser = factory.newSAXParser();
			 
			     DefaultHandler handler = new DefaultHandler() {
			 
			     boolean bfname = false;
			     boolean blname = false;
			     boolean bnname = false;
			     boolean bsalary = false;
			 
			     public void startElement(String uri, String localName,
			        String qName, Attributes attributes)
			        throws SAXException {
			 
			        System.out.println("Start Element :" + qName);
			 
			        if (qName.equalsIgnoreCase("FIRSTNAME")) {
			           bfname = true;
			        }
			 
			        if (qName.equalsIgnoreCase("LASTNAME")) {
			           blname = true;
			        }
			 
			        if (qName.equalsIgnoreCase("NICKNAME")) {
			           bnname = true;
			        }
			 
			        if (qName.equalsIgnoreCase("SALARY")) {
			           bsalary = true;
			        }
			 
			     }
			 
			     public void endElement(String uri, String localName,
			          String qName)
			          throws SAXException {
			 
			          System.out.println("End Element :" + qName);
			 
			     }
			 
			     public void characters(char ch[], int start, int length)
			         throws SAXException {
			 
			         if (bfname) {
			            System.out.println("First Name : "
			                + new String(ch, start, length));
			            bfname = false;
			          }
			 
			          if (blname) {
			              System.out.println("Last Name : "
			                  + new String(ch, start, length));
			              blname = false;
			           }
			 
			          if (bnname) {
			              System.out.println("Nick Name : "
			                  + new String(ch, start, length));
			              bnname = false;
			           }
			 
			          if (bsalary) {
			              System.out.println("Salary : "
			                  + new String(ch, start, length));
			              bsalary = false;
			           }
			 
			        }
			 
			      };
			 
			      saxParser.parse("c:\\file.xml", handler);
			 
			    } catch (Exception e) {
			      e.printStackTrace();
			    }
			  }

	@Override
	public void buildDiaDescuento() {
		// TODO Auto-generated method stub
		pedido.setDiaDescuento(diaDescuento);
	}

	@Override
	public void buildAddLinVentas() {
		// TODO Auto-generated method stub
		pedido.setLinVentas(linVentas);
	}

	@Override
	public void buildTarjeta() {
		// TODO Auto-generated method stub
		pedido.setTarjeta(tarjeta);
	}

	@Override
	public void buildEmpleado() {
		// TODO Auto-generated method stub
		pedido.setEmpleado(empleado);
	}

}
