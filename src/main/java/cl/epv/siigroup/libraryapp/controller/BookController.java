package cl.epv.siigroup.libraryapp.controller;

import cl.epv.siigroup.libraryapp.dto.BookDTO;
import cl.epv.siigroup.libraryapp.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller principal de manejo de operaciones CRUD de Libros
 */
@RestController
@RequestMapping("/library-app/api/v1/books")
@AllArgsConstructor
@Slf4j
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> createBook(
            @Valid @RequestBody BookDTO book
    ){
        log.info("nuevo libro");

        BookDTO result = service.newBook(book);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        log.info("Todos los libros");

        List<BookDTO> result = service.getAllBooks();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> getBookById(
            @PathVariable(value = "id") Long id
    ){
        log.info("Obtener un libro por id");

        BookDTO result = service.getBook(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> updateBook(
            @Valid @RequestBody BookDTO book, @PathVariable Long id
    ){
        log.info("actualiza libro");

        BookDTO result = service.updateBook(book, id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteBook(
            @RequestParam(value = "id") Long id
    ){
        log.info("eliminar libro por id");

        Boolean result = service.deleteBook(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
