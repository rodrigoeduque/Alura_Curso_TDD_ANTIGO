package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

    private Double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private Double menorDeTodos = Double.POSITIVE_INFINITY;
    private Double somatoria = 0.0;
    private Double quantidadeLances = 0.0;
    private Double media = 0.0;
    private List<Lance> maiores;


    public void avalia(Leilao leilao) {

        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if (lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
            quantidadeLances++;
            somatoria += lance.getValor();
        }

        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {

            @Override
            public int compare(Lance o1, Lance o2) {
                if (o1.getValor() < o2.getValor()) {
                    return 1;
                }
                if (o1.getValor() > o2.getValor()) {
                    return -1;
                }
                return 0;
            }
        });
        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
    }

    public Double getMaiorLance() {
        return maiorDeTodos;
    }

    public Double getMenorLance() {

        return menorDeTodos;
    }

    public Double getMediaLance() {
        if (quantidadeLances <= 0) {
            media = 0.0;
            return media;
        } else
            media = somatoria / quantidadeLances;
        return media;
    }

    public List<Lance> getTresMaiores() {
        return maiores;
    }
}
