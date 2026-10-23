package model;

public class Servico {
    private int id;
    private String descricao;
    private double valor;

    public Servico() {
		
	}

	public Servico(String descricao, int id, double valor) {
		this.descricao = descricao;
		this.id = id;
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public int getId() {
		return id;
	}
	public double getValor() {
		return valor;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
    //hashCode é um metodo que retorna um numero inteiro que representa o objeto, esse numero é usado para comparar objetos e para armazenar objetos em colecoes como HashMap e HashSet
	public int hashCode() {
		final int prime = 31;//numero primo que ajuda a misturar os bits dos atributos
        //para que o resultado seja unico
		int result = 1;// hahcod comeca com 1, pois 0 nao ajuda a misturar os bits
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode()); // se descricao for null, adiciona 0 ao hashcode, caso contrario adiciona o hashcode da descricao
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(valor);// converte o valor double em um long para que possa ser usado no hashcode
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
    //equals é um metodo que compara dois objetos do tipo Servico e retorna true se eles forem iguais, false caso contrario
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;//transforma o objeto obj em um objeto do tipo Servico para poder comparar os atributos
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Servico id:" + id + ", descricao:" + descricao + ", valor:" + valor + "";
	}

    
    
    
    
}
