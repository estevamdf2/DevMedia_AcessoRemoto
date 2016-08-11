package devmedia.main;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import wildfly.ejb.CompraDolarServices;
import wildfly.ejb.CompraDolarServicesRemote;
import wildfly.ejb.CotacaoServices;
import wildfly.ejb.CotacaoServicesRemote;


public class ConsoleViewCotacao {
	public static void main(String[] args) throws Exception{

				
		String appName = "";
		String moduleName = "WildflyEJB/";
		String distinctName = "";
		String beanCotacaoServices = CotacaoServices.class.getSimpleName();
		String viewCotacaoServicesName = CotacaoServicesRemote.class.getName();
		String beanCompraDolaresServices = CompraDolarServices.class.getSimpleName();
		String viewCompraDolaresName = CompraDolarServicesRemote.class.getName();
		
		
		Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
				
		Context context = new InitialContext(properties);

		//Lookup no serviço de cotação remota
		CotacaoServicesRemote cotacaoService = (CotacaoServicesRemote) context.lookup("ejb:/"+appName + moduleName + beanCotacaoServices + "!"+viewCotacaoServicesName);

		//Invocando a cotação remota
		System.out.println("Cotação: 20 dolares são " + cotacaoService.calcularCotacaoReal(20) + " reais");

		//Lookup no serviço de compra de dolar remota
		CompraDolarServicesRemote dolarService =  (CompraDolarServicesRemote) context.lookup("ejb:/"+appName + moduleName + beanCompraDolaresServices +"!"+ viewCompraDolaresName +"?stateful");

		//Adiciona 40 dolares
		dolarService.adicionarDolar(40);
		
		//Calcula o valor total a pagar em reais
		System.out.println("Total a pagar: " + dolarService.calcularValorTotal() + " reais");

		//Remove 20 dolares
		dolarService.removerDolar(20);

		//Calcula o valor total a pagar em reais
		System.out.println("Total a pagar: " + dolarService.calcularValorTotal() + " reais");
	}
}
