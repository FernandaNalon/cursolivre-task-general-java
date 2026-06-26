package projeto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Classe responsável pela tela principal do sistema
// Estamos herdando o JFrame, ou seja, teremos uma janela gráfica
public class TelaTarefa extends JFrame{
	// Lista interna que armazena todas as tarefas cadastradas pelo usuário
	private ArrayList<Tarefa> tarefas = new ArrayList<>();
	
	// Campo onde o usuário digitará o título da tarefa
	private JTextField campoTitulo;
	
	// Campo onde o usuário digitará a descrição da tarefa
	private JTextArea campoDescricao;
	
	// Modelo responsável por armazenar os dados exibidos na lista visual
	private DefaultListModel<Tarefa> modeloLista;
	
	// Lista visual que exibe as tarefas cadastradas na tela
	private JList<Tarefa> listaTarefas;
	
	// Construtor da nossa interface
	public TelaTarefa() {
		
		// Define o titulo da janela
		setTitle("Gerenciador de Tarefas");
		
		// Define o tamanho da janela (largura x altura)
		setSize(850, 550);
		
		// Encerra o programa ao clicar no botão de fechar da interface
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Centraliza a janela na tela
		setLocationRelativeTo(null);
		
		// Impede que o usuário redimensionar a janela
		setResizable(false);
		
		// Chama o método responsável por montar a interface gráfica
		criarInterface();
		
		// Deixará nossa janela visivel
		setVisible(true);
	}
	
	// Método responsável por criar e organizar todos os componentes visuais
	private void criarInterface() {
		
		// Painel principal da nossa tela
		JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
		
		// Define o espaçamento interno do painel principal
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		
		// Define a cor de fundo da aplicação
		painelPrincipal.setBackground(new Color(236, 240, 243));
		
		// Titulo principal da aplicação
		JLabel titulo = new JLabel("Gerenciador de Tarefas");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
		titulo.setForeground(new Color(44,62,80));
		
		// Subtítulo principal da aplicação
		JLabel subtitulo = new JLabel("Organize suas tarefas de forma simples e "
				+ "prática");
		subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		subtitulo.setForeground(new Color(100,116,139));
		
		// Painel superior para agrupar título e subtítulo
		JPanel painelTopo = new JPanel(new GridLayout(2,1));
		painelTopo.setBackground(new Color(236, 240, 243));
		painelTopo.add(titulo);
		painelTopo.add(subtitulo);
		
		// Adiciona o painel superior no topo da tela
		painelPrincipal.add(painelTopo, BorderLayout.NORTH);
		
		// Cria o card/formulário para o cadastro de tarefas
		JPanel cardFormulario = criarCard();
		cardFormulario.setLayout(new BorderLayout(10,10));
		
		// Titulo do formulário
		JLabel tituloFormulario = new JLabel("Nova tarefa");
		tituloFormulario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tituloFormulario.setForeground(new Color(44, 62, 80));
		
		// Painel que agrupa os campos do formulário
		JPanel campos = new JPanel(new GridLayout(4, 1, 8, 8));
		campos.setBackground(Color.WHITE);
		
		// Label do campo de titulo
		JLabel labelTitulo = new JLabel("Titulo");
		labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
	
		// Campo para digitar o titulo
		campoTitulo = new JTextField();
		campoTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		// Borda personalizada do campo de titulo
		campoTitulo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(203,213,225)),
				BorderFactory.createEmptyBorder(8,10,8,10)
		));
		
		// Label do campo de descrição
		JLabel labelDescricao = new JLabel("Descrição");
		labelDescricao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		// Campo de texto para digitar a descrição da tarefa
		campoDescricao = new JTextArea();
		campoDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		campoDescricao.setLineWrap(true);
		campoDescricao.setWrapStyleWord(true);
		campoDescricao.setBorder(BorderFactory.createEmptyBorder(8,10,8,10));
		
		// Scroll para o campo descrição
		JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
		scrollDescricao.setBorder(BorderFactory.createLineBorder(new Color(203,213,225)));
		
		// Adiciona os labels 
		campos.add(labelTitulo);
		campos.add(campoTitulo);
		campos.add(labelDescricao);
		campos.add(scrollDescricao);
		
		// Botão para adicionar uma nova tarefa
		JButton botaoAdicionar = criarBotao("Adicionar tarefa", new Color(37,99,235));
		
		// Adiciona titulo, campos e botão ao card de formulário
		cardFormulario.add(tituloFormulario, BorderLayout.NORTH);
		cardFormulario.add(campos, BorderLayout.CENTER);
		cardFormulario.add(botaoAdicionar, BorderLayout.SOUTH);
		
		// Adiciona o card do formulário à esquerda da tela
		painelPrincipal.add(cardFormulario, BorderLayout.WEST);
		
		// Cria o card na lista de tarefas
		JPanel cardLista = criarCard();
		cardLista.setLayout(new BorderLayout(10,10));
		
		// Título da área de tarefas cadastradas
		JLabel tituloLista = new JLabel("Tarefas cadastradas");
		tituloLista.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tituloLista.setForeground(new Color(44,62,80));
		
		// Modelo que guarda os dados que será exibidos na JList
		modeloLista = new DefaultListModel<>();
		
		// Lista visual que exibirá as tarefas
		listaTarefas = new JList<>(modeloLista);
		
		// Define um renderer personalizado para exibir cada tarefa como um card
		listaTarefas.setCellRenderer(new TarefaCardRenderer());

		// Define uma altura fixa para cada tarefa exibida
		listaTarefas.setFixedCellHeight(75);
		
		// Permite selecionar apenas uma tarefa por vez
		listaTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Define a cor de fundo da lista
		listaTarefas.setBackground(Color.WHITE);
		
		// Scroll para a lista de tarefas cadastradas
		JScrollPane scrollLista = new JScrollPane(listaTarefas);
		scrollLista.setBorder(BorderFactory.createLineBorder(new Color(226,232,240)));
		
		// Painel inferior, onde ficam os botões de ação na lista de tarefas
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		painelBotoes.setBackground(Color.WHITE);
		
		// Botões para concluir e remover uma tarefa cadastrada
		JButton botaoConcluir = criarBotao("Concluir", new Color(22,163,74));
		JButton botaoRemover = criarBotao("Remover", new Color(220,38,38));
		
		// Adiciona os botões ao painel inferior
		painelBotoes.add(botaoConcluir);
		painelBotoes.add(botaoRemover);
		
		//Adiciona titulo, lista e botões ao card da lista
		cardLista.add(tituloLista, BorderLayout.NORTH);
		cardLista.add(scrollLista, BorderLayout.CENTER);
		cardLista.add(painelBotoes, BorderLayout.SOUTH);
		
		// Adiciona o card da lista ao centro da tela
		painelPrincipal.add(cardLista, BorderLayout.CENTER);
		
		// Adiciona o painel principal à nossa janela
		add(painelPrincipal);
		
		// Eventos para os botões de adicionar, concluir e remover uma tarefa
		botaoAdicionar.addActionListener(e -> adicionarTarefa());
		botaoConcluir.addActionListener(e -> concluirTarefa());
		botaoRemover.addActionListener(e -> removerTarefa());
	}
	
	// Método responsável por criar um painel com aparência de card
	private JPanel criarCard() {
		JPanel painel = new JPanel();
		painel.setBackground(Color.WHITE);
		painel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(226,232,240)),
			BorderFactory.createEmptyBorder(20,20,20,20)
		));
		painel.setPreferredSize(new Dimension(330,400));
		return painel;
	}
		
	// Método responsável por criar os botões padronizados
	private JButton criarBotao(String texto, Color cor) {
		// Cria o botão com base no texto recebido na criação da nossa interface
		JButton botao = new JButton(texto);
		botao.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fonte do botão
		botao.setBackground(cor); // Cor de fundo
		botao.setForeground(Color.WHITE); // Cor do texto
		botao.setFocusPainted(false); // Remove a borda de foco
		// Altera o cursor ao passar o mouse sobre o botão
		botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
		// Define  espaçamento interno do botão (também chamamos de padding)
		botao.setBorder(BorderFactory.createEmptyBorder(10,18,10,18));
		return botao;
	}
	
	//Método responsável por adicional uma nova tarefa
	private void adicionarTarefa() {
		// Captura o título e descrição digitado pelo usuário
		String titulo = campoTitulo.getText().trim();
		String descricao = campoDescricao.getText().trim();
		// Verifica se os campos estão vazios
		if (titulo.isEmpty() || descricao.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
			return;
		}
		// Cria um novo objeto Tarefa
		Tarefa tarefa = new Tarefa(titulo, descricao);
		tarefas.add(tarefa); // adiciona a tarefa à lista interna
		modeloLista.addElement(tarefa); // adiciona a tarefa 
		// Limpa os campos de titulo e descrição
		campoTitulo.setText("");
		campoDescricao.setText("");
	}
	
	private void concluirTarefa() {
		// Pega a tarefa selecionada na lista visual
		Tarefa tarefaSelecionada = listaTarefas.getSelectedValue();
		// Verifica se o usuário selecionou uma tarefa
		if(tarefaSelecionada == null) {
			JOptionPane.showMessageDialog(this, "Selecione uma tarefa");
			return;
		}
		tarefaSelecionada.concluir(); // Altera o status da tarefa
		listaTarefas.repaint(); // Atualiza visualmente a lista
	}
	
	// Método responsável por remover uma tarefa
	private void removerTarefa() {
		// Pega a tarefa selecionada na lista
		Tarefa tarefaSelecionada = listaTarefas.getSelectedValue();
		// Verifica se o usuário selecionou uma tarefa
		if (tarefaSelecionada == null) {
			JOptionPane.showMessageDialog(this, "Selecione uma tarefa");
			return;
		}
		tarefas.remove(tarefaSelecionada); // Remove da lista interna
		modeloLista.removeElement(tarefaSelecionada); // Remove da lista visual
	}
	// Classe interna para fazer a renderização do card de tarefa
	private class TarefaCardRenderer extends JPanel implements ListCellRenderer<Tarefa>{
		private JLabel labelTitulo; // exibe o titulo
		private JLabel labelDescricao; // exibe a descrição
		private JLabel labelStatus; // exibe o status
		// Construtor do renderer
		public TarefaCardRenderer() {
			setLayout(new BorderLayout(8,5)); // Define o layout do card
			setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0,0,8,0,Color.WHITE),
				BorderFactory.createEmptyBorder(10,12,10,12)
			)); // Define a borda e o espaçamento
			
			// Cria e estiliza a label do titulo
			labelTitulo = new JLabel();
			labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
			labelTitulo.setForeground(new Color(30,41,59));
			
			// Cria e estiliza a label de descrição
			labelDescricao = new JLabel();
			labelDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			labelDescricao.setForeground(new Color(100,116,139));
			
			labelStatus = new JLabel(); // Estiliza o status
			labelStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
			
			// Organiza o titulo e a descrição em 2 linhas e 1 coluna
			JPanel painelTexto = new JPanel(new GridLayout(2,1));
			
			painelTexto.setOpaque(false); // deixa o painel transparente
			painelTexto.add(labelTitulo); // adiciona titulo
			painelTexto.add(labelDescricao); // adiciona descricao
			// Adiciona o painel no centro do card
			add(painelTexto, BorderLayout.CENTER); 
			add(labelStatus, BorderLayout.EAST); // coloca o status a direita do card
		}
			// Método para recriar a listagem

	        @Override
	        public Component getListCellRendererComponent(
	                JList<? extends Tarefa> list,
	                Tarefa tarefa,
	                int index,
	                boolean isSelected,
	                boolean cellHasFocus
	        ) {

	            // Define o título que será exibido no card
	            labelTitulo.setText(tarefa.getTitulo());

	            // Define a descrição que será exibida no card
	            labelDescricao.setText(tarefa.getDescricao());

	            // Define o status que será exibido no card
	            labelStatus.setText(tarefa.getStatus());

	            // Se a tarefa estiver concluída, o status fica verde
	            if (tarefa.getStatus().equals("Concluída")) {
	                labelStatus.setForeground(new Color(22, 163, 74));
	            } else {
	                // Caso contrário, o status fica laranja
	                labelStatus.setForeground(new Color(234, 88, 12));
	            }

	            // Se o card estiver selecionado, muda a cor do fundo
	            if (isSelected) {
	                setBackground(new Color(219, 234, 254));
	            } else {
	                // Caso contrário, usa um fundo claro
	                setBackground(new Color(248, 250, 252));
	            }

	            // Retorna o próprio painel personalizado para ser exibido na lista
	            return this;
	        }
	    }
	}







