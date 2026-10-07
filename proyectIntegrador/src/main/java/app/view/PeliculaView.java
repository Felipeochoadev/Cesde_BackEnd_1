package app.view;

import app.domain.Pelicula;
import app.domain.enums.GeneroPelicula;
import app.service.helpers.SetPropertyHelper;
import app.service.inputports.PeliculaServiceInterface;
import app.service.validations.FormTypeValidator;
import java.util.List;

public class PeliculaView {
    private final PeliculaServiceInterface peliculaService;

    public PeliculaView(PeliculaServiceInterface peliculaService) {
        this.peliculaService = peliculaService;
    }

    public void createPelicula() {
        System.out.println("\n--- REGISTRAR PELÍCULA EN CARTELERA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la película: ");
        String titulo = FormTypeValidator.readString("Ingrese el Título: ");

        // 🌟 Uso de SetPropertyHelper: Menú interactivo con Enum
        GeneroPelicula generoSeleccionado = SetPropertyHelper.setGeneroPelicula();
        String genero = generoSeleccionado.getDescripcion();

        int duracion = FormTypeValidator.readInt("Ingrese la Duración (minutos): ");
        double precio = FormTypeValidator.readDouble("Ingrese el Precio Base: ");
        int edadMinima = FormTypeValidator.readInt("Ingrese la Edad Mínima (0 para todo público, 18 para adultos): ");

        // Paso 14 y 15: Pasa los parámetros capturados al create del servicio
        Pelicula creada = peliculaService.create(id, titulo, genero, duracion, precio, edadMinima);
        System.out.println("✅ Película agregada a cartelera con éxito: " + creada);
    }

    public void selectPeliculaById() {
        System.out.println("\n--- CONSULTAR PELÍCULA POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la película a buscar: ");
        selectPeliculaById(id);
    }

    public void selectPeliculaById(int id) {
        Pelicula pelicula = peliculaService.getById(id);
        if (pelicula != null) {
            System.out.println("Encontrada: " + pelicula + " (Clasificación: +" + pelicula.getEdadMinima() + ")");
        } else {
            System.out.println("❌ No se encontró ninguna película con ID " + id);
        }
    }

    public void selectAllPeliculas() {
        System.out.println("\n--- CARTELERA DE PELÍCULAS ---");
        List<Pelicula> peliculas = peliculaService.getAll();
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas registradas en cartelera.");
        } else {
            for (Pelicula p : peliculas) {
                System.out.println("• " + p);
            }
        }
    }

    public void updatePelicula() {
        System.out.println("\n--- ACTUALIZAR PELÍCULA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la película que desea actualizar: ");
        Pelicula existente = peliculaService.getById(id);
        if (existente == null) {
            System.out.println("❌ No existe película con ID " + id);
            return;
        }

        String nuevoTitulo = FormTypeValidator.readString("Ingrese el nuevo Título: ");
        
        // 🌟 Uso de SetPropertyHelper en actualización
        GeneroPelicula generoSeleccionado = SetPropertyHelper.setGeneroPelicula();
        String nuevoGenero = generoSeleccionado.getDescripcion();

        int nuevaDuracion = FormTypeValidator.readInt("Ingrese la nueva Duración (minutos): ");
        double nuevoPrecio = FormTypeValidator.readDouble("Ingrese el nuevo Precio Base: ");
        int nuevaEdadMin = FormTypeValidator.readInt("Ingrese la nueva Edad Mínima: ");

        Pelicula peliculaActualizada = new Pelicula(id, nuevoTitulo, nuevoGenero, nuevaDuracion, nuevoPrecio, nuevaEdadMin);
        peliculaService.update(id, peliculaActualizada);
        System.out.println("✅ Película actualizada con éxito.");
    }

    public void deletePelicula() {
        System.out.println("\n--- ELIMINAR PELÍCULA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la película que desea eliminar: ");
        deletePelicula(id);
    }

    public void deletePelicula(int id) {
        boolean eliminada = peliculaService.delete(id);
        if (eliminada) {
            System.out.println("✅ Película con ID " + id + " eliminada de cartelera.");
        } else {
            System.out.println("❌ No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
