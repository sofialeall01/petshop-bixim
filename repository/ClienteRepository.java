package repository;

import model.Cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteRepository
{
    private ArrayList<Cliente> clientes;

    public ClienteRepository(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void adicionar(Cliente cliente){
        this.clientes.add(cliente);
    }

    public ArrayList<Cliente> listar(){
       return clientes;
    }

    public Cliente buscarPorId(int id){
       if(id != 0){
           for (int i = 0; i <= clientes.size();i++){
               if (clientes.get(i).getId() == id){
                   return clientes.get(i);
               } else{
                   System.out.println("Cliente não existe.");
               }
           }
       }else{
           System.out.println("O id não pode ser zero");
       }
       return null;
    }

    public boolean remover(int id){
        if(id != 0){
            for (int i = 0; i <= clientes.size();i++){
                if (clientes.get(i).getId() == id){
                    Cliente c = clientes.get(i);
                    clientes.remove(c);
                    return true;
                }else{
                    System.out.println("Cliente não existe.");
                }
            }
        }else{
            System.out.println("O id não pode ser zero");
        }
        return false;
    }

    public void carregarDados(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);
        try (Scanner scanner = new Scanner(arquivo, "UTF-8")) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(",");
                int id = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                String telefone = dados[2].trim();


                Cliente cliente = new Cliente(id, nome, telefone);
                adicionar(cliente);
            }

            System.out.println("Dados carregados com sucesso!");

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }
}
