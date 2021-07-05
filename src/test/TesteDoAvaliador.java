package test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class TesteDoAvaliador {

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        //1 Cenário
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 250.00));
        leilao.propoe(new Lance(jose, 300.00));
        leilao.propoe(new Lance(maria, 400.00));

        //2 Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);


        //3 Validação
        double maiorEsperado = 400.00;
        double menorEsperado = 250.00;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(),0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(),0.00001);

    }

    @Test
    public void deveCalcularAMediadosLances(){
        //1 Cenário
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 250.00));
        leilao.propoe(new Lance(jose, 300.00));
        leilao.propoe(new Lance(maria, 400.00));

        //2 Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double mediaLances = 316.6666;
        assertEquals(mediaLances, leiloeiro.getMediaLance(),0.01);
    }

    @Test
    public void testaMediadeZeroLances(){
        Usuario usuario1 = new Usuario("Teste");
        Leilao leilao = new Leilao("Carro Teste");
        leilao.propoe(new Lance(usuario1, 0.0));
        Avaliador leiloeiro = new Avaliador();
        double media = leiloeiro.getMediaLance();

        assertEquals(0.0, leiloeiro.getMediaLance(),0.001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance(){
        Usuario joao = new Usuario("Joao");
        Leilao leilao = new Leilao("Play5");
        leilao.propoe(new Lance(joao, 1000.00));
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(1000.00, leiloeiro.getMenorLance(), 0.00000001);
        assertEquals(1000.00, leiloeiro.getMaiorLance(), 0.00000001);

    }

    @Test
    public void deveEncontrarOsTresMaioresLances(){
        Usuario joao = new Usuario("João");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 5 Novo");

        leilao.propoe(new Lance(joao, 100.00));
        leilao.propoe(new Lance(jose, 200.00));
        leilao.propoe(new Lance(maria, 300.00));
        leilao.propoe(new Lance(maria, 400.00));

        Avaliador leiloeiro = new Avaliador();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(),0.0001);
        assertEquals(300.0, maiores.get(1).getValor(),0.0001);
        assertEquals(200.0, maiores.get(2).getValor(),0.0001);
    }
}
