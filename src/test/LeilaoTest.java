package test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {


    @Test
    public void deveReceberUmLance(){
        Leilao leilao = new Leilao("Macbook");
        assertEquals(0,leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000.00));
        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.00, leilao.getLances().get(0).getValor(),0.00001);
    }

    @Test
    public void deveReceberVariosLances(){
        Leilao leilao = new Leilao("Macbook");
        leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000.00));
        leilao.propoe(new Lance(new Usuario("Steve 2"), 3000.00));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(),0.0001);
        assertEquals(3000, leilao.getLances().get(1).getValor(),0.0001);

    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
        Leilao leilao = new Leilao("Macbook");
        Usuario usuario = new Usuario("Steve Jobs");
        leilao.propoe(new Lance(usuario, 2000));
        leilao.propoe(new Lance(usuario, 3000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(),0.0001);


    }

    @Test
    public void naoDeveAceitarMaisqueCincoLancesDoMesmoUsuario(){
        Leilao leilao = new Leilao("Macbook");
        Usuario rodrigo = new Usuario("Rodrigo");
        Usuario henrique = new Usuario("Henrique");
        Usuario ana = new Usuario("Ana");

        leilao.propoe(new Lance(rodrigo, 1500.00));
        leilao.propoe(new Lance(ana, 1600.00));
        leilao.propoe(new Lance(rodrigo, 1700.00));
        leilao.propoe(new Lance(henrique, 1800.00));
        leilao.propoe(new Lance(ana, 1900.00));
        leilao.propoe(new Lance(rodrigo, 2000.00));
        leilao.propoe(new Lance(henrique, 2100.00));
        leilao.propoe(new Lance(rodrigo, 2200.00));
        leilao.propoe(new Lance(ana, 2500.00));
        leilao.propoe(new Lance(rodrigo, 2600.00));
        leilao.propoe(new Lance(henrique, 2700.00));
        leilao.propoe(new Lance(ana, 2850.00));
        leilao.propoe(new Lance(rodrigo, 3000.00));


        assertEquals(12, leilao.getLances().size());
    }
}
