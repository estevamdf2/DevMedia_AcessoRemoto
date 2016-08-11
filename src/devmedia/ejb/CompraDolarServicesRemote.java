package devmedia.ejb;

import javax.ejb.Remote;

@Remote
public interface CompraDolarServicesRemote {

	public void adicionarDolar(double valor);
	
	public void removerDolar(double valor);
	
	public double calcularValorTotal();
	
}
