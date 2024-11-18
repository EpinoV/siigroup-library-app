package cl.epv.siigroup.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    /**
     * Identificador de Libro
     */
    private Long id;

    /**
     * Título del libro
     */
    private String title;

    /**
     * Autor del libro
     */
    private String author;

    /**
     * Código ISBN único
     */
    private String isbn;

    /**
     * Fecha de publicación
     */
    private LocalDate publishedDate;

    /**
     * Categoría o género
     */
    private String category;

    /**
     * Disponibilidad
     */
    private Boolean available;
}