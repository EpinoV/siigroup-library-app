package cl.epv.siigroup.libraryapp.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 */
@Entity
@Table(name = "BOOK")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    /**
     * Identificador de Libro
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private Long id;

    /**
     * Título del libro
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * Autor del libro
     */
    @Column(name = "AUTHOR")
    private String author;

    /**
     * Código ISBN único
     */
    @Column(name = "ISBN", unique = true)
    private String isbn;

    /**
     * Categoría o género
     */
    @Column(name = "CATEGORY")
    private String category;

    /**
     * Disponibilidad
     */
    @Column(name = "AVAILABLE")
    private Boolean available;

}
