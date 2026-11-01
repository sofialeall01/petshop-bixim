package repository;

import model.Cliente;
import model.Pet;
import model.Servico;
import model.ServicoPrestado;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicoPrestadoRepository
{
    private ArrayList<ServicoPrestado> servicoPrestados;

    public ServicoPrestadoRepository(ArrayList<ServicoPrestado> servicoPrestados) {
        this.servicoPrestados = servicoPrestados;
    }

    public void adicionar(ServicoPrestado servicoPrestado){
        this.servicoPrestados.add(servicoPrestado);
    }

    public ArrayList<ServicoPrestado> listar(){
        return servicoPrestados;
    }

    public ServicoPrestado buscarPorId(int id){
        if(id != 0){
            for (int i = 0; i <= servicoPrestados.size();i++){
                if (servicoPrestados.get(i).getId() == id){
                    return servicoPrestados.get(i);
                } else{
                    System.out.println("Serviço prestado não existe.");
                }
            }
        }else{
            System.out.println("O id não pode ser zero");
        }
        return null;
    }

    public boolean remover(int id){
        if(id != 0){
            for (int i = 0; i <= servicoPrestados.size();i++){
                if (servicoPrestados.get(i).getId() == id){
                    ServicoPrestado p = servicoPrestados.get(i);
                    servicoPrestados.remove(p);
                    return true;
                }else{
                    System.out.println("Serviço prestado não existe.");
                }
            }
        }else{
            System.out.println("O id não pode ser zero");
        }
        return false;
    }

    public void carregarDados(String caminhoArquivo, PetRepository petRepository,
                              ServicoRepository servicoRepository) {

        File arquivo = new File(caminhoArquivo);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

        try (Scanner scanner = new Scanner(arquivo, "UTF-8")) {

            while (scanner.hasNextLine()) {

                String linha = scanner.nextLine();
                String[] dados = linha.split(",");

                int id = Integer.parseInt(dados[0].trim());

                LocalDate data = LocalDate.parse(dados[1].trim(), formato);

                double valorCobrado = Double.parseDouble(dados[2].trim());

                int idServico = Integer.parseInt(dados[3].trim());

                int idPet = Integer.parseInt(dados[4].trim());

                Pet pet = petRepository.buscarPorId(idPet);

                Servico servico = servicoRepository.buscarPorId(idServico);

                if (pet != null && servico != null) {

                    ServicoPrestado servicoPrestado =
                            new ServicoPrestado(id, data, valorCobrado, pet, servico);

                    adicionar(servicoPrestado);

                } else {
                    System.out.println("Pet ou Serviço não encontrado! ID: " + id);
                }
            }

            System.out.println("Dados carregados com sucesso!");

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());

        } catch (DateTimeParseException e) {
            System.out.println("Erro no formato da data: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Erro na conversão de números: " + e.getMessage());
        }
    }

    public ArrayList<ServicoPrestado> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {

        ArrayList<ServicoPrestado> resultado = new ArrayList<>();

        for (int i = 0; i < servicoPrestados.size(); i++) {
            LocalDate data = servicoPrestados.get(i).getData();

            if (!data.isBefore(dataInicio) && !data.isAfter(dataFim)) {
                resultado.add(servicoPrestados.get(i));
            }
        }

        return resultado;
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nomeDono) {
        ArrayList<Pet> resultado = new ArrayList<>();

        for (int i = 0; i < servicoPrestados.size(); i++) {
            Pet pet = servicoPrestados.get(i).getPet();

            if (pet.getCliente().getNome().equalsIgnoreCase(nomeDono)) {
                if (!resultado.contains(pet)) { //evita duplicar o pet
                    resultado.add(pet);
                }
            }
        }

        return resultado;
    }

    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente){
        ArrayList<ServicoPrestado> resultado = new ArrayList<>();

        for (int i = 0; i < servicoPrestados.size(); i++) {
            if(servicoPrestados.get(i).getNomeDonoPet().equalsIgnoreCase(cliente.getNome())){ //reaproveitei o metodo usado em buscarBichosPorNomeDono
               resultado.add(servicoPrestados.get(i));
            }
        }
        return resultado;
    }

    public double maiorValorPorCliente(Cliente cliente) {
        double maior = 0;

        for (int i = 0; i < servicoPrestados.size(); i++) {
            ServicoPrestado servico = servicoPrestados.get(i);

            if (servico.getNomeDonoPet().equalsIgnoreCase(cliente.getNome())) {
                if (servico.getValorCobrado() > maior) {
                    maior = servico.getValorCobrado();
                }
            }
        }

        return maior;
    }
}
