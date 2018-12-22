/**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */
package es.inf.uva.poo.pickingpointssystem;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;

import es.inf.uva.poo.PackageLocker.PackageLocker;
import es.inf.uva.poo.paquete.Package;
import es.uva.inf.poo.maps.GPSCoordinate;

public class PickingPointsSystemTest {	
	@Test
	public void testGetNumeroPackageLocker(){
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker packagelocker = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		PickingPointsSystem p = new PickingPointsSystem();
		listaPackageLockers.add(packagelocker);
		PackageLocker packagelocker2 = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		listaPackageLockers.add(packagelocker2);
		assertSame(listaPackageLockers.size(),p.getNumeroPackageLocker(listaPackageLockers));
	}
	
	@Test
	public void testAddPackageLocker() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker packagelocker = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		PickingPointsSystem p = new PickingPointsSystem();
		listaPackageLockers.add(packagelocker);
		p.addPackageLocker(packagelocker, listaPackageLockers);
		assertSame(listaPackageLockers.get(0),listaPackageLockers.get(1));
	}
	
	@Test
	public void testExistePackageLocker() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker packagelocker = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		PickingPointsSystem p = new PickingPointsSystem();
		p.addPackageLocker(packagelocker, listaPackageLockers);
		assertTrue(p.existePackageLocker("123", listaPackageLockers));
		assertFalse(p.existePackageLocker("232", listaPackageLockers));
	}
	
	@Test
	public void testRemovePackageLocker() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker packagelocker = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		PickingPointsSystem p = new PickingPointsSystem();
		listaPackageLockers.add(packagelocker);
		int prueba = listaPackageLockers.size();
		p.removePackageLocker("123", listaPackageLockers);
		assertNotSame(listaPackageLockers.size(), prueba);
		boolean comprobacionExistePackageLocker = p.existePackageLocker("123", listaPackageLockers);
		assertFalse(comprobacionExistePackageLocker);
	}
	
	@Test
	public void testPackageLockerOperativos() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker packagelocker = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		PickingPointsSystem p = new PickingPointsSystem();
		p.addPackageLocker(packagelocker, listaPackageLockers);
		PackageLocker[] operativos = p.packageLockerOperativos(listaPackageLockers);
		assertSame(operativos[0], packagelocker);
		PackageLocker packagelocker2 = new PackageLocker(10, coordenadas, false, "123", HoraApertura, HoraCierre);
		p.addPackageLocker(packagelocker2, listaPackageLockers);
		operativos = p.packageLockerOperativos(listaPackageLockers);
		for(int i=0; i < operativos.length; i++) {
			assertNotSame(operativos[i],packagelocker2);
		}
	}
	
	@Test
	public void testPackageLockerFueraDeServicio() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker packagelocker = new PackageLocker(10, coordenadas, false, "123", HoraApertura, HoraCierre);
		PickingPointsSystem p = new PickingPointsSystem();
		PackageLocker packagelocker2 = new PackageLocker(10, coordenadas, true, "123", HoraApertura, HoraCierre);
		p.addPackageLocker(packagelocker, listaPackageLockers);
		PackageLocker[] fueraDeServicio = p.packageLockerFueraDeServicio(listaPackageLockers);
		p.addPackageLocker(packagelocker2, listaPackageLockers);
		assertSame(fueraDeServicio[0], packagelocker);
		fueraDeServicio = p.packageLockerFueraDeServicio(listaPackageLockers);
		for(int i=0; i < fueraDeServicio.length; i++) {
			assertNotSame(fueraDeServicio[i],packagelocker2);
		}
	}
	
	@Test
	public void testPackageLockerEnUnRadio() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadasprueba = new GPSCoordinate(20,20);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		GPSCoordinate coordenadas1 = new GPSCoordinate(2,2);
		PackageLocker packagelocker1 = new PackageLocker(10, coordenadas1, true, "321", HoraApertura, HoraCierre);
		GPSCoordinate coordenadas2 = new GPSCoordinate(3,3);
		PackageLocker packagelocker2 = new PackageLocker(10, coordenadas2, true, "321", HoraApertura, HoraCierre);
		PickingPointsSystem p = new PickingPointsSystem();
		double distancia2 = packagelocker2.getCoordenadas().getDistanceTo(coordenadasprueba);
		p.addPackageLocker(packagelocker1, listaPackageLockers);
		p.addPackageLocker(packagelocker2, listaPackageLockers);
		PackageLocker[] enUnRadio = p.packageLockerEnUnRadio(distancia2, listaPackageLockers, coordenadasprueba);
		assertSame(enUnRadio[0],packagelocker2);
		for(int i=0; i < enUnRadio.length; i++) {
			assertNotSame(enUnRadio[i],packagelocker1);
		}
	}
	
	@Test
	public void testPackageLockerConTaquillasVacias() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalDate fecha = LocalDate.of(2000,1,1);
		String identificadorInicial = "1111111119";
		Package paqueteAIntroducir = new Package(identificadorInicial ,fecha);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker packagelocker = new PackageLocker(1, coordenadas, false, "123", HoraApertura, HoraCierre);
		PackageLocker packagelocker2 = new PackageLocker(1, coordenadas, false, "123", HoraApertura, HoraCierre);
		PickingPointsSystem p = new PickingPointsSystem();
		packagelocker.asignarTaquilla(1, paqueteAIntroducir);
		p.addPackageLocker(packagelocker, listaPackageLockers);
		p.addPackageLocker(packagelocker2, listaPackageLockers);
		PackageLocker[] conTaquillasVacias = p.packageLockerConTaquillasVacias(listaPackageLockers);
		assertSame(packagelocker2, conTaquillasVacias[0]);
		for(int i=0; i < conTaquillasVacias.length; i++) {
			assertNotSame(conTaquillasVacias[i],packagelocker);
		}
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testCodigoRemovePackageLockerNull() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		PickingPointsSystem p = new PickingPointsSystem();
		p.removePackageLocker(null, listaPackageLockers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCodigoRemovePackageLockerCodigoErroneo() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		LocalTime HoraApertura = LocalTime.of(7, 40);
		LocalTime HoraCierre = LocalTime.of(23, 00);
		PackageLocker packagelocker = new PackageLocker(1, coordenadas, false, "123", HoraApertura, HoraCierre);
		listaPackageLockers.add(packagelocker);
		PickingPointsSystem p = new PickingPointsSystem();
		p.removePackageLocker("321", listaPackageLockers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddPackageLockerNull() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		PackageLocker packagelocker = null;
		PickingPointsSystem p = new PickingPointsSystem();
		p.addPackageLocker(packagelocker, listaPackageLockers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExistePackageLockerCodigoNull() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		PickingPointsSystem p = new PickingPointsSystem();
		p.existePackageLocker(null, listaPackageLockers);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPackageLockerEnUnRadioCoordenadasNull() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = null;
		PickingPointsSystem p = new PickingPointsSystem();
		p.packageLockerEnUnRadio(100, listaPackageLockers, coordenadas);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPackageLockerEnUnRadioDistanciaNegativa() {
		ArrayList<PackageLocker> listaPackageLockers = new ArrayList<PackageLocker>();
		GPSCoordinate coordenadas = new GPSCoordinate(3,4);
		PickingPointsSystem p = new PickingPointsSystem();
		p.packageLockerEnUnRadio(-1, listaPackageLockers, coordenadas);
	}
	
	
	
	
	
}
