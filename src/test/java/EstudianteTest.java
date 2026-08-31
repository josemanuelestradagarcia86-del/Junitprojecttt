import org.junit.Test;
import static org.junit.Assert.*;
public class EstudianteTest {

    @Test
    public void testEstudianteCreacionYToString() {
        int carnetEsperado = 20230001;
        String nombreEsperado = "Juan";
        String apellidosEsperado = "Pérez Gómez";

        Estudiante estudiante = new Estudiante(carnetEsperado, nombreEsperado, apellidosEsperado);

        assertEquals(carnetEsperado, estudiante.getCarnet());
        assertEquals(nombreEsperado, estudiante.getNombre());
        assertEquals(apellidosEsperado, estudiante.getApellidos());

        String resultadoToString = estudiante.toString();
        assertTrue(resultadoToString.contains("20230001"));
        assertTrue(resultadoToString.contains("Juan"));
        assertTrue(resultadoToString.contains("Pérez Gómez"));
    }
}