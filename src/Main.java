import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        ///1.
        List<Producto> productos = ListaProductos.cargarProductos();

        List<String> resultado1 = productos.stream()
                .filter(p -> p.getCategoria().equals("Electrónica"))
                .filter(p -> p.getPrecio() > 1000)
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .map(producto -> producto.getNombre() + "$ " + producto.getPrecio())
                .toList();

        System.out.println(resultado1);

        ///2.
        OptionalDouble resultado2 = productos.stream()
                .filter(producto -> producto.getCategoria().equals("Hogar"))
                .filter(producto -> producto.getStock() > 0)
                .mapToDouble(Producto::getPrecio)
                .average();

        System.out.println("Promedio: $ "+ resultado2.getAsDouble());

        ///3.
        Map<String, Optional<Producto>> resultado3 = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.maxBy(Comparator.comparing(Producto::getPrecio))
                ));
        //Agustin
        /// EJERCICIO 4
        System.out.println("\nEjercicio 4");
        Optional<String> nombreProducto = productos.stream()
                .filter(p -> p.getCategoria().equals("Deportes"))
                .filter(p -> p.getStock() > 10)
                .findFirst()
                .map(p -> p.getNombre().toLowerCase());

        System.out.println(nombreProducto.orElse("Producto Inexistente"));










    }
}