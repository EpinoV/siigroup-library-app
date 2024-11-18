package cl.epv.siigroup.libraryapp.service;

import cl.epv.siigroup.libraryapp.dto.BookDTO;

import java.util.List;

public interface BookService {

    /**
     * Crea un nuevo libro en BD
     * @param book
     * @return
     */
    BookDTO newBook(BookDTO book);

    /**
     * Obtiene un listado de todos los libros existentes
     * @return
     */
    List<BookDTO> getAllBooks();

    /**
     * Obtiene toda la información de un libro a través de su id
     * @param bookId
     * @return
     */
    BookDTO getBook(Long bookId);

    /**
     * Obtiene toda la información de un libro a través de el identificador ISBN
     * @param isbn
     * @return
     */
    BookDTO getBook(String isbn);


    /**
     * Actualiza la información de un libro existente a través de su id
     * @param book
     * @return
     */
    BookDTO updateBook(BookDTO book);


    /**
     * Elimina un libro a través de su id
     * @param id
     * @return
     */
    Boolean deleteBook(Long id);

}
