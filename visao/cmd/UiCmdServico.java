package visao.cmd;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ServicoController;
import model.Servico;


public class UiCmdServico {
    private Scanner teclado;
    private ServicoController controller;

   


    public UiCmdServico(ServicoController controller) {
        this.controller = controller;
    }

    public void iniciar(){
        int opcao = 0;
     do{
          System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║          GERENCIAR SERVICOS          ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1 - Adicionar Pet                   ║");
            System.out.println("║  2 - Remover Pet                     ║");
            System.out.println("║  3 - Listar Pets                     ║");
            System.out.println("║  4 - Buscar Pet                      ║");
            System.out.println("║  0 - Voltar ao Menu Principal        ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            switch (opcao) {
                case 1: adcionar(); break;
                
                case 2: apagar(); break;

                case 3: listar(); break;

                case 4: buscar(); break;
            
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
     
    }while(opcao !=0);
    
}

    private void adcionar(){
        System.out.println("Informe o ID do servico: ");
        int id = teclado.nextInt();
        System.out.println("Informe a descricao do servico: ");
        String descricao = teclado.next();
        System.out.println("Informe o valor do servico: ");
        double valor = teclado.nextDouble();
        
        controller.adicionar(id, descricao, valor);
    }

    private void apagar(){
        System.out.println("Informe o ID do servico que sera apagado: \n");
        int id = teclado.nextInt();

        boolean resultado = controller.remover(id);
        if (resultado == true) {
            System.out.println("Servico apagado com sucesso!");
        }else{
            System.out.println("Erro ao apagar servico");
        }

    }

    private void listar(){
        ArrayList<Servico> servicos = controller.listar();
        for (int index = 0; index < servicos.size(); index++) {
            System.out.println(servicos.get(index).toString());
        }
    }

    private void buscar(){
        System.out.println("Informe o ID do servico: ");
        int id = teclado.nextInt();

        Servico encontrado = controller.buscar(id);
        if(encontrado != null){
            System.out.println(encontrado.toString());
        }else{
            System.out.println("Servico nao encontrado");
        }
    }
}