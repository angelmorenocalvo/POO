/**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */
package es.inf.uva.poo.paquete;

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.*;


public class PackageTest {

	@Test
	public void testInicializadorCorrecto() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991",fecha);
		assertEquals(p.getIdentificador(),"9999999991");
		assertSame(p.getFecha(),fecha);
	}

	@Test(expected=IllegalArgumentException.class)
	public void codigoNull() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package(null,fecha);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void decimoTerminoIncorrecto() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("1111111118",fecha);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void numeroMenorDeDiez() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("123456",fecha);
	}
	@Test(expected=IllegalArgumentException.class)
	public void numeroMayorDeDiez() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("1234567891",fecha);
	}
	@Test(expected=IllegalArgumentException.class)
	public void fechaNull() {
		LocalDate fecha = null;
		Package p = new Package("123456",fecha);
	}
	@Test(expected=IllegalArgumentException.class)
	public void IdentificadorConCaracteresMayores() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("a111111118",fecha);
	}
	@Test(expected=IllegalArgumentException.class)
	public void IdentificadorConCaracteresMenores() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("!111111118",fecha);
	}
	
	@Test
	public void getIdentificadorPrueba() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		String identificadorInicial = "1111111119";
		Package p = new Package(identificadorInicial ,fecha);
		String a = p.getIdentificador();
		assertEquals(identificadorInicial,a);
	}

	@Test
	public void testGetFecha() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		String identificadorInicial = "1111111119";
		Package p = new Package(identificadorInicial ,fecha);
		LocalDate  a = p.getFecha();
		assertEquals(fecha,a);
	}
	
	@Test
	public void testGetRecogido() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		String identificadorInicial = "1111111119";
		Package p = new Package(identificadorInicial ,fecha);
		boolean  a = p.getRecogido();
		assertFalse(a);
	}
	
	@Test
	public void testGetDevuelto() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		String identificadorInicial = "1111111119";
		Package p = new Package(identificadorInicial ,fecha);
		assertFalse(p.getDevuelto());
	}
	
	@Test
	public void testSetRecogido() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		String identificadorInicial = "1111111119";
		Package p = new Package(identificadorInicial ,fecha);
		p.setRecogido(true);
		assertTrue(p.getRecogido());
	}

	@Test
	public void testSetDevuelto() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		String identificadorInicial = "1111111119";
		Package p = new Package(identificadorInicial ,fecha);
		p.setDevuelto(true);
		assertTrue(p.getDevuelto());
	}
	
	
	
	@Test
	public void setIdentificadorResultadoCorrecto() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		String identificadorInicial = "1111111119";
		Package p = new Package(identificadorInicial ,fecha);
		p.setIdentificador("9999999991");
		assertSame("9999999991",p.getIdentificador());
	}
	@Test(expected=IllegalArgumentException.class)	
	public void setIdentificadorCodigoNull() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991" ,fecha);
		p.setIdentificador(null);
		
	}
	@Test(expected=IllegalArgumentException.class)	
	public void setIdentificadorMenor10() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991" ,fecha);
		p.setIdentificador("123456789");
	}
	@Test(expected=IllegalArgumentException.class)	
	public void setIdentificadorMayor10() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991" ,fecha);
		p.setIdentificador("1234567811");
	}
	@Test(expected=IllegalArgumentException.class)	
	public void setIdentificadorLetraPorArriba() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991" ,fecha);
		p.setIdentificador("a111111119");
	}
	@Test(expected=IllegalArgumentException.class)	
	public void setIdentificadorLetraPorDebajo() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991" ,fecha);
		p.setIdentificador("!111111119");
	}
	@Test
	public void testExpiradoSalidaFalse() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991",fecha);
		LocalDate hoy = LocalDate.of(2000,1,2);
		assertFalse(p.expirado(hoy));
	}
	@Test
	public void testExpiradoSalidaTrue() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991",fecha);
		LocalDate hoy = LocalDate.of(1999,12,31);
		assertTrue(p.expirado(hoy));
	}
	@Test(expected=IllegalArgumentException.class)
	public void testExpiradoEntradaNull() {
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991",fecha);
		assertFalse(p.expirado(null));
	}
	
}
