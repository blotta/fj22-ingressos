package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SessaoTest {
	
	private Filme filme;
	private Sala sala;
	private Sessao sessao;
	
	@Before
	public void before() {
		this.filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		this.sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), this.filme, this.sala);

	}

	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
//		Sala sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
//		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		
		BigDecimal somaDosPrecosDaSalaEFilme = this.sala.getPreco().add(this.filme.getPreco());
		
//		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		
		Assert.assertEquals(somaDosPrecosDaSalaEFilme, this.sessao.getPreco());

	}
	
	@Test
	public void garanteQueOLugarA1EstaOcupadoEOsLugaresA2EA3Disponiveis() {
		Lugar a1 = new Lugar("A", 1);
		Lugar a2 = new Lugar("A", 2);
		Lugar a3 = new Lugar("A", 3);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);
		
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		
		this.sessao.setIngressos(ingressos);
		
		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));
		
	}

}
