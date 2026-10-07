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
        String titulo = FormTypeValidator.readString("Ingrese el Título: ");

        GeneroPelicula generoSeleccionado = SetPropertyHelper.setGeneroPelicula();
        String genero = generoSeleccionado.getDescripcion();

        int duracion = FormTypeValidator.readInt("Ingrese la Duración (minutos): ");
        double precio = FormTypeValidator.readDouble("Ingrese el Precio Base: ");
        int edadMinima = FormTypeValidator.readInt("Ingrese la Edad Mínima (0 para todo público, 18 para adultos): ");

        Pelicula creada = peliculaService.create(titulo, genero, duracion, precio, edadMinima);
        System.out.println("Película agregada a cartelera con éxito:");
        System.out.println(creada);
    }

    public void selectPeliculaById() {
        System.out.println("\n--- CONSULTAR PELÍCULA POR ID ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la película a buscar: ");
        selectPeliculaById(id);
    }

    public void selectPeliculaById(int id) {
        Pelicula pelicula = peliculaService.getById(id);
        if (pelicula != null) {
            System.out.println("Película encontrada:");
            System.out.println(pelicula);
        } else {
            System.out.println("No se encontró ninguna película con ID " + id);
        }
    }

    public void selectAllPeliculas() {
        System.out.println("\n--- CARTELERA DE PELÍCULAS ---");
        List<Pelicula> peliculas = peliculaService.getAll();
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas registradas en cartelera.");
        } else {
            for (Pelicula p : peliculas) {
                System.out.println(p);
            }
        }
    }

    public void updatePelicula() {
        System.out.println("\n--- ACTUALIZAR PELÍCULA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la película que desea actualizar: ");
        Pelicula existente = peliculaService.getById(id);
        if (existente == null) {
            System.out.println("No existe película con ID " + id);
            return;
        }

        String nuevoTitulo = FormTypeValidator.readStringOptional("Ingrese el nuevo Título", existente.getTitulo());
        
        System.out.println("¿Desea cambiar el Género? (Actual: " + existente.getGenero() + ")");
        boolean cambiarGenero = FormTypeValidator.readBoolean("¿Desea seleccionar un nuevo género?");
        String nuevoGenero = existente.getGenero();
        if (cambiarGenero) {
            GeneroPelicula generoSeleccionado = SetPropertyHelper.setGeneroPelicula();
            nuevoGenero = generoSeleccionado.getDescripcion();
        }

        int nuevaDuracion = FormTypeValidator.readIntOptional("Ingrese la nueva Duración (minutos)", existente.getDuracionMinutos());
        double nuevoPrecio = FormTypeValidator.readDoubleOptional("Ingrese el nuevo Precio Base", existente.getPrecioBase());
        int nuevaEdadMin = FormTypeValidator.readIntOptional("Ingrese la nueva Edad Mínima", existente.getEdadMinima());

        Pelicula peliculaActualizada = new Pelicula(id, nuevoTitulo, nuevoGenero, nuevaDuracion, nuevoPrecio, nuevaEdadMin);
        peliculaService.update(id, peliculaActualizada);
        System.out.println("Película actualizada con éxito:");
        System.out.println(peliculaActualizada);
    }

    public void deletePelicula() {
        System.out.println("\n--- ELIMINAR PELÍCULA ---");
        int id = FormTypeValidator.readInt("Ingrese el ID de la película que desea eliminar: ");
        deletePelicula(id);
    }

    public void deletePelicula(int id) {
        boolean eliminada = peliculaService.delete(id);
        if (eliminada) {
            System.out.println("Película con ID " + id + " eliminada de cartelera.");
        } else {
            System.out.println("No se pudo eliminar: ID " + id + " no encontrado.");
        }
    }
}
