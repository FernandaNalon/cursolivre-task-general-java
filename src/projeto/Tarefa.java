package projeto;

public class Tarefa {
	// Atributos da nossa tarefa
	private String titulo;
	private String descricao;
	private String status;
	
	// Método construtor da nossa classe
	public Tarefa(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = "Pendente";
	}
	
	// Retorna o título da tarefa
	public String getTitulo() {
		return titulo;
	}
	
	// Retorna a descrição da tarefa
	public String getDescricao() {
		return descricao;
	}
	
	// Retorna o status da tarefa
	public String getStatus() {
		return status;
	}
	
	// Altera o status da tarefa para concluído
	public void concluir() {
		this.status = "Concluída";
	}
	
	// Define como a tarefa será exibida na lista da tela
	@Override
	public String toString() {
		return titulo + " - " + status;
	}
}
