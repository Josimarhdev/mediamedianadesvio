package br.com.postman.service;


import br.com.postman.entity.numero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.postman.repository.numeroRepository;

import java.util.Collections;
import java.util.List;

@Service
public class numeroService {

@Autowired
    private numeroRepository numeroRepository;


    public String calcularEstatisticas() {
        List<numero> numeros = numeroRepository.findAll();

        if (numeros.isEmpty()) {
            return "Não há números para calcular";
        }

        //media
        double soma = 0;
        for (numero numero : numeros) {
            soma += numero.getNumero();
        }
        double media = soma / numeros.size();

        //mediana
        Collections.sort(numeros, (n1, n2) -> Double.compare(n1.getNumero(), n2.getNumero()));
        double mediana;
        if (numeros.size() % 2 == 0) {
            int middle = numeros.size() / 2;
            mediana = (numeros.get(middle - 1).getNumero() + numeros.get(middle).getNumero()) / 2;
        } else {
            mediana = numeros.get(numeros.size() / 2).getNumero();
        }

        //desvio
        double somatorioDesvios = 0;
        for (numero numero : numeros) {
            double desvio = numero.getNumero() - media;
            somatorioDesvios += desvio * desvio;
        }
        double desvioPadrao = Math.sqrt(somatorioDesvios / numeros.size());

        return "Média: " + media + " | Mediana: " + mediana + " | Desvio padrão: " + desvioPadrao + " | Quantidade de numeros: " + numeros.size();
    }

}
