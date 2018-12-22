/**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */
package es.inf.uva.poo.PackageLocker;
import es.inf.uva.poo.paquete.*;
import es.inf.uva.poo.paquete.Package;

import java.time.*;
import java.util.*;
import java.text.SimpleDateFormat;
import es.uva.inf.poo.maps.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PackageLockerTest {

	@Test
	public void testinicializadorCorrecto() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		assertEquals(p.getnumeroTaquillas(), 10);
		assertEquals(p.getEstado(), true);
		assertEquals(p.getIdentificador(), "123");
		assertSame(p.getCoordenadas(),coordenadas);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testinicializadorHoraAperturaMayorCierre() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(23, 40);
		LocalTime HoraCierre = LocalTime.of(11, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void coordenadasNull() {
		GPSCoordinate coordenadas = null;
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void horaAperturaNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = null;
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void horaCierreNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = null;
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void codigoNull() {
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		String codigo = null;
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, codigo, HoraApertura, HoraCierre);
	}
	
	@Test
	public void segundotestinicializadorCorrecto() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, "123", HoraApertura, HoraCierre);
		assertEquals(p.getnumeroTaquillas(), 10);
		assertEquals(p.getEstado(), false);
		assertEquals(p.getIdentificador(), "123");
		assertSame(p.getCoordenadas(),coordenadas);
	}
	@Test(expected=IllegalArgumentException.class)
	public void segundotestinicializadorHoraAperturaNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = null;
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, "123", HoraApertura, HoraCierre);
	}
	@Test(expected=IllegalArgumentException.class)
	public void segundotestinicializadorHoraCierreNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = null;
		PackageLocker p = new PackageLocker(10, coordenadas, "123", HoraApertura, HoraCierre);
	}
	@Test(expected=IllegalArgumentException.class)
	public void segundotestinicializadorHoraCierreMenorHoraApertura() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(6, 40);
		PackageLocker p = new PackageLocker(10, coordenadas, "123", HoraApertura, HoraCierre);
	}
	@Test(expected=IllegalArgumentException.class)
	public void segundotestinicializadorNumeroTaquillasNegativo() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(7, 41);
		PackageLocker p = new PackageLocker(-1, coordenadas, "123", HoraApertura, HoraCierre);
	}
	@Test(expected=IllegalArgumentException.class)
	public void segundotestinicializadorIdentificadorNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(7, 41);
		PackageLocker p = new PackageLocker(10, coordenadas, null, HoraApertura, HoraCierre);
	}
	@Test(expected=IllegalArgumentException.class)
	public void segundotestinicializadorLocalizadorNull() {
		GPSCoordinate coordenadas = null;
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(7, 41);
		PackageLocker p = new PackageLocker(10, coordenadas, "123", HoraApertura, HoraCierre);
	}
	@Test
	public void testGetEstado() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		Boolean comprobacion = p.getEstado();
		assertEquals(estado,comprobacion);
	}
	
	@Test
	public void testGetIdentificador() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		String comprobacion = p.getIdentificador();
		assertEquals(identificadorInicial,comprobacion);
	}
	
	@Test
	public void testGetNumeroTaquillas() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		int comprobacion = p.getnumeroTaquillas();
		assertEquals(taquillas,comprobacion);
	}
	
	@Test
	public void testGetCoordenadas() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		GPSCoordinate comprobacion = p.getCoordenadas();
		assertEquals(coordenadas,comprobacion);
	}
	
	@Test
	public void testGetTaquillasVacias() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		int comprobacion = p.getTaquillasVacias();
		assertEquals(taquillas,comprobacion);
	}
	
	@Test
	public void testGetTaquillasOcupadas() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		int comprobacion = p.getTaquillasOcupadas();
		assertEquals(0,comprobacion);
	}
	
	@Test
	public void testGetTaquillasOperativas() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		int comprobacion = p.getTaquillasOperativas();
		assertEquals(taquillas,comprobacion);
	}
	
	@Test
	public void testGetTaquillasInoperativas() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		int comprobacion = p.getTaquillasInoperativas();
		assertEquals(0,comprobacion);
	}
	
	@Test
	public void testGetHoraApertura() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		LocalTime comprobacion = p.getHoraApertura();
		assertEquals(HoraApertura,comprobacion);
	}
	
	@Test
	public void testGetHoraCierre() {
		boolean estado = true;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		LocalTime comprobacion = p.getHoraCierre();
		assertEquals(HoraCierre,comprobacion);
	}
	
	@Test
	public void testSetEstado() {
		boolean estado = false;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		p.setEstado(true);
		assertEquals(true,p.getEstado());
	}
	
	@Test
	public void testSetIdentificador() {
		boolean estado = false;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		p.setIdentificador("TU");
		assertEquals("TU",p.getIdentificador());
	}
	
	@Test
	public void testSetCoordenadas() {
		boolean estado = false;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		GPSCoordinate localizacionPrueba = new GPSCoordinate(5,6);
		p.setCoordenadas(localizacionPrueba);
		assertEquals(localizacionPrueba, p.getCoordenadas());
	}
	
	@Test
	public void testSetHoraApertura() {
		boolean estado = false;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		LocalTime Apertura = LocalTime.of(9, 40);
		p.setHoraApertura(Apertura);
		assertEquals(Apertura, p.getHoraApertura());
	}
	
	@Test
	public void testSetHoraCierre() {
		boolean estado = false;
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		LocalTime Cierre = LocalTime.of(9, 40);
		p.setHoraCierre(Cierre);
		assertEquals(Cierre, p.getHoraCierre());
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCodigoNullAlBuscarUnPackage() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(22, 40);
		String codigo = "123";
		PackageLocker p = new PackageLocker(10, coordenadas, true, codigo, HoraApertura, HoraCierre);
		String codigoAComprobar = null;
		p.buscarPaquete(codigoAComprobar);
	}
	
	@Test
	public void testAsignarTaquilla() {
		boolean estado = false;
		LocalDate fecha = LocalDate.of(2018, 11, 13);
		Package paqueteAIntroducir = new Package("1111111119", fecha );
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		p.asignarTaquilla(3, paqueteAIntroducir);
		assertSame(paqueteAIntroducir, p.mirarEnTaquilla(3));
	}
	
	@Test
	public void testBuscarTaquilla() {
		boolean estado = false;
		LocalDate fecha = LocalDate.of(2018, 11, 13);
		Package paqueteAIntroducir = new Package("1111111119", fecha );
		String identificadorInicial = "123";
		int taquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(taquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		p.asignarTaquilla(3, paqueteAIntroducir);
		assertSame(paqueteAIntroducir, p.buscarPaquete("1111111119"));
	}
	
	@Test
	public void testeQuitarPaqueteTaquilla() {
		boolean estado = false;
		LocalDate fecha = LocalDate.of(2018, 11, 13);
		Package paqueteAIntroducir = new Package("1111111119", fecha );
		String identificadorInicial = "123";
		int numtaquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(numtaquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		p.asignarTaquilla(1, paqueteAIntroducir);
		int taquillasVaciasIniciales = p.getTaquillasVacias();
		p.quitarPaqueteTaquilla("1111111119");
		int taquillasVaciasTrasBorrar = p.getTaquillasVacias();
		assertEquals(taquillasVaciasIniciales , taquillasVaciasTrasBorrar-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testeQuitarPaqueteTaquillaCodigoErroneo() {
		boolean estado = false;
		LocalDate fecha = LocalDate.of(2018, 11, 13);
		Package paqueteAIntroducir = new Package("1111111119", fecha );
		String identificadorInicial = "123";
		int numtaquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(numtaquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		p.asignarTaquilla(1, paqueteAIntroducir);
		p.quitarPaqueteTaquilla("0111111118");

	}
	@Test(expected=IllegalArgumentException.class)
	public void testeQuitarPaqueteTaquillaCodigoNull() {
		boolean estado = false;
		LocalDate fecha = LocalDate.of(2018, 11, 13);
		Package paqueteAIntroducir = new Package("1111111119", fecha );
		String identificadorInicial = "123";
		int numtaquillas = 10;
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(numtaquillas, coordenadas, estado, identificadorInicial, HoraApertura, HoraCierre);
		p.asignarTaquilla(1, paqueteAIntroducir);
		p.quitarPaqueteTaquilla(null);	
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetIdentificadorNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		p.setIdentificador(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetCoordenadasNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		p.setCoordenadas(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetHoraAperturaNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		p.setHoraApertura(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetHoraCierreNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		p.setHoraCierre(null);
	}
	
	@Test
	public void testGetPackage() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(1, paquete);
		assertSame(p.getPaquete(1),paquete);
	}
	@Test(expected=AssertionError.class)
	public void testGetPackageCeldaContenidoNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(10, paquete);
		Package v = p.getPaquete(3);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGetPackageNumeroCeldasNegativo() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(10, paquete);
		Package v = p.getPaquete(-10);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGetPackageNumeroMayorRango() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(10, paquete);
		Package v = p.getPaquete(11);
	}
	
	@Test
	public void testGetTaquillasOperativasQuitadas() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(10, paquete);
		p.celdaInoperativa(1);
		assertEquals(p.getTaquillasInoperativas(),1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCeldaInoperativaNegativo() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(10, paquete);
		p.celdaInoperativa(-10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCeldaInoperativaFueraRango() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(10, paquete);
		p.celdaInoperativa(11);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCeldaOperativaNegativo() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(10, paquete);
		p.celdaOperativa(-10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCeldaOperativaFueraRango() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(10, paquete);
		p.celdaOperativa(11);
	}
	
	@Test
	public void testGetTaqOperativasQuitadas() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(10, paquete);
		for(int i = 0; i<10;i++)
			p.celdaInoperativa(i);
		p.celdaOperativa(1);
		assertEquals(p.getTaquillasOperativas(),1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTaquillaCeldaNegativa() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(-10, paquete);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAsignarTaquillafueraRango() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(11, paquete);
	}
	@Test(expected=AssertionError.class)
	public void testAsignarTaquillaNull() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(9, paquete);
		p.asignarTaquilla(9, paquete);
	}
	
	@Test(expected=AssertionError.class)
	public void testAsignarTaquillaNoOperativa() {
		GPSCoordinate coordenadas = new GPSCoordinate (2,3);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker p = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package paquete = new Package("9999999991",fecha);
		p.asignarTaquilla(9, paquete);
		p.asignarTaquilla(9, paquete);
	}
}
	


	
	

