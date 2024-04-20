package br.com.joaocarloslima;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvException;

public class Dataset {

    private List<Avistamento> avistamentos = new ArrayList<>();

    private int totalFiltrado;

    public Dataset(String caminhoArquivo) throws IOException, CsvException {
        Csv.carregarDadosCSV(caminhoArquivo, avistamentos);
    }

    public List<Avistamento> getAvistamentos() {

        return this.avistamentos;

    }

    public List<Avistamento> filtrarAvistamentosPorAnimal(String animal) {

        List<Avistamento> filtroAnimal = this.avistamentos.stream().filter(a -> a.getAnimal().equals(animal) ).toList();
        return filtroAnimal;

    }

    public List<Avistamento> filtrarAvistamentosPorPesquisador(String pesquisador) {
        
        List<Avistamento> filtroPesquisador = this.avistamentos.stream().filter(p -> p.getPesquisador().equals(pesquisador) ).toList();
        return filtroPesquisador;
    }

    public List<Avistamento> filtrarAvistamentosPorMes(int mes) {
        
        List<Avistamento> avistamentoMes = this.avistamentos.stream().filter(m -> m.getMes() == mes ).toList();
        return avistamentoMes;
    }

    public int getQtdePorAnimal(String animal, List<Avistamento> avistamentos) {
        
        int total = avistamentos.stream()
        .filter(a -> a.getAnimal().equals(animal))
        .mapToInt(Avistamento::getQuantidade)
        .sum();


        return total;

        
    }

    public int getQtdePorPesquisador(String pesquisador, List<Avistamento> avistamentos) {
        
        int total = avistamentos.stream()
        .filter(p -> p.getPesquisador().equals(pesquisador))
        .mapToInt(Avistamento::getQuantidade)
        .sum();

        return total;

    }

    public int responderQ1() {
        
        int t = avistamentos.stream()
        .filter(p -> p.getPesquisador().equals("Bob"))
        .filter(p -> p.getAnimal().equals("Elefante"))
        .filter(m -> m.getMes() == 2 ) 
        .mapToInt(Avistamento::getQuantidade)
        .sum();


        return t;
    }
         
    
    public int responderQ2() {
        
        int qtdTotal = avistamentos.stream()
        .filter(p -> p.getPesquisador().equals("Ana"))
        .filter(m -> m.getMes() >= 1 && m.getMes() <= 3)
        .mapToInt(Avistamento::getQuantidade)
        .sum();

        
        return qtdTotal;
        
        
    }

    public int responderQ3() {

        int qtdTotal = avistamentos.stream()
        .filter(p -> p.getPesquisador().equals("Ana"))
        .filter(a -> a.getAnimal().equals("Macaco") || a.getAnimal().equals("Elefante"))
        .filter(m -> m.getMes() == 7)
        .mapToInt(Avistamento::getQuantidade)
        .sum();


        return qtdTotal;
        
    }

    public int getTotalFiltrado() {
        return totalFiltrado;
    }

}
