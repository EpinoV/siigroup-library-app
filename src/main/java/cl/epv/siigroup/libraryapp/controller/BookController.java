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

    @GetMapping(value = "/allBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> allBooks(){
        log.info("Todos los libros");

        List<BookDTO> result = service.getAllBooks();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/bookByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> bookByEmail(
            @RequestParam(value = "email") String email
    ){
        log.info("Todos los libros");

        BookDTO result = service.getBook(email);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/updateBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> updateBook(
            @Valid @RequestBody BookDTO book
    ){
        log.info("actualiza libro");

        BookDTO result = service.updateBook(book);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> deleteBook(
            @RequestParam(value = "email") String email
    ){
        log.info("Todos los libros");

        BookDTO result = service.getBook(email);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
