package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Servico;

public class ServicoRepository {
    private ArrayList<Servico> servicos;

    public ServicoRepository() {
        this.servicos = new ArrayList<>();
    }
    
    public Servico buscarPorId(int id){
        for (int i = 0; i < servicos.size(); i++) {
            Servico s = servicos.get(i);
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Servico> listar(){
        return servicos;
    }

    public void adicionar(Servico servico){
       // ddiciona o objeto diretamente no final do ArrayList
       this.servicos.add(servico);
    }

    public boolean remover(int id) {
    for (int i = 0; i < servicos.size(); i++) {
        Servico s = servicos.get(i);
        
        if (s.getId() == id) {
            //funçao nativa do arraylist para remover
            servicos.remove(i); 
            return true; // Achou, removeu e encerra a função retornando true
        }
    }
    // Se o loop terminar e não achar o ID, retorna false
    return false;
}

   public void carregarDados(String caminhoArquivo) {
    // Criamos um objeto File com o caminho recebido
    File arquivo = new File(caminhoArquivo);
    
    //uso o 'try' por segurança,o scanner sera fechado logo apos o final da leitura
    try (Scanner scanner = new Scanner(arquivo, "UTF-8")) {
        //formato "UTF-8" para que acentos e caracteres especiais sejam lidos corretamente
        
        // enquanto houver uma proxima linha no arquivo...
        while (scanner.hasNextLine()) {
            //pega o conteudo da linha atual, guarda na variavel e vai para proxima linha
            String linha = scanner.nextLine();
            
            // divide a linha usando a virgula como separador 
            String[] dados = linha.split(",");
            
            //uso trim() para remover espaços em branco no inicio ou fim do texto
            int id = Integer.parseInt(dados[0].trim()); //converte a string em int
            String descricao = dados[1].trim();
            double valor = Double.parseDouble(dados[2].trim()); // converte a string em double
            
            // Cria o objeto novo com os dados coletados
            Servico servico = new Servico(descricao, id, valor);
            adicionar(servico);
        }
        
        System.out.println("Dados carregados com sucesso!"); // (ou System.out.println)
        
    } catch (FileNotFoundException e) {
        //captura o erro e exibe uma mensagem para o usuario
        System.out.println("Arquivo não encontrado: " + e.getMessage());
    }
}

}
