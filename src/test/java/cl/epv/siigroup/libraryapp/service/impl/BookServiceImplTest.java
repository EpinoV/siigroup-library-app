package cl.epv.siigroup.libraryapp.service.impl;

import cl.epv.siigroup.libraryapp.dto.BookDTO;
import cl.epv.siigroup.libraryapp.exceptions.BookException;
import cl.epv.siigroup.libraryapp.mappers.BookMapper;
import cl.epv.siigroup.libraryapp.model.Book;
import cl.epv.siigroup.libraryapp.repository.BookRepository;
import cl.epv.siigroup.libraryapp.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository repository;

    private final BookMapper mapper = BookMapper.INSTANCE;

    private BookService service;

    Book mockModel;

    BookDTO mockDTO;


    @BeforeEach
    void setUp() {
       // MockitoAnnotations.openMocks(this);
        service = new BookServiceImpl(repository);

        mockModel = new Book(1L, "Titulo libro", "Autor Libro", "12345678", "Novela", Boolean.TRUE);
        mockDTO = new BookDTO(1L, "Titulo libro", "Autor Libro", "12345678", "Novela", Boolean.TRUE);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldNewBookWhenISBNIsUnique() {
        BookDTO dto = new BookDTO(1L, "Titulo libro", "Autor Libro", "12345678", "Novela", Boolean.TRUE);
        Book book = BookMapper.INSTANCE.dtoToEntity(mockDTO);
        Book savedBook = new Book(1L, "Titulo libro", "Autor Libro", "12345678", "Novela", Boolean.TRUE);

        when(repository.findByIsbn(any(String.class))).thenReturn(Optional.empty());
        when(repository.saveAndFlush(any(Book.class))).thenReturn(savedBook);

        BookDTO result = service.newBook(dto);

        assertNotNull(result);
        assertEquals("Titulo libro", result.getTitle());
        assertEquals("Autor Libro", result.getAuthor());

        //verify(repository).saveAndFlush(book);

    }

    @Test
    void shouldThrowExceptionWhenISBNExists() {
        // Arrange
        when(repository.findByIsbn(mockModel.getIsbn())).thenReturn(Optional.of(mockModel));

        // Act & Assert
        assertThrows(BookException.class, () -> service.newBook(mockDTO));
        verify(repository, never()).saveAndFlush(any(Book.class));
    }

    @Test
    void shouldReturnListOfBooksWhenBooksExist() {
        // Arrange
        List<Book> books = List.of(
                new Book(1L, "Titulo libro 1", "Autor Libro 1", "12345678", "Novela", Boolean.TRUE),
                new Book(1L, "Titulo libro 2", "Autor Libro 2", "12345679", "Novela", Boolean.TRUE)
        );
        when(repository.findAll()).thenReturn(books);

        // Act
        List<BookDTO> result = service.getAllBooks();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Titulo libro 1", result.get(0).getTitle());
        verify(repository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoBooksExist() {
        // Arrange
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<BookDTO> result = service.getAllBooks();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findAll();
    }

    @Test
    void shouldReturnBookWhenIdExists() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(mockModel));

        // Act
        BookDTO result = service.getBook(1L);

        // Assert
        assertNotNull(result);
        assertEquals(mockModel.getId(), result.getId());
        assertEquals(mockModel.getTitle(), result.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenBookNotFound() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BookException.class, () -> service.getBook(1L));
    }
    @Test
    void shouldUpdateBookWhenIdExists() {



        // Arrange
        Book updatedBook = new Book(1L, "Titulo libro", "Autor Libro", "12345678", "Novela", Boolean.TRUE);
        BookDTO updatedBookDTO = new BookDTO(1L, "Titulo libro", "Autor Libro", "12345678", "Novela", Boolean.TRUE);

        when(repository.findById(1L)).thenReturn(Optional.of(mockModel));
        when(repository.saveAndFlush(any(Book.class))).thenReturn(updatedBook);

        // Act
        BookDTO result = service.updateBook(updatedBookDTO, 1L);

        // Assert
        assertNotNull(result);
        assertEquals(updatedBook.getTitle(), result.getTitle());
        assertEquals(updatedBook.getAuthor(), result.getAuthor());
        verify(repository).saveAndFlush(any(Book.class));
    }

    @Test
    void shouldThrowExceptionWhenBookNotFoundForUpdate() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Book updatedBook = new Book();
        BookDTO updatedBookDTO = new BookDTO();

        // Act & Assert
        assertThrows(BookException.class, () -> service.updateBook(updatedBookDTO, 1L));
        verify(repository, never()).saveAndFlush(any(Book.class));
    }

    @Test
    void shouldReturnTrueWhenBookIsDeleted() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(mockModel));

        // Act
        Boolean result = service.deleteBook(1L);

        // Assert
        assertTrue(result);
        verify(repository).deleteById(1L);
    }

    @Test
    void shouldReturnFalseWhenBookNotFoundForDeletion() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Boolean result = service.deleteBook(1L);

        // Assert
        assertFalse(result);
        verify(repository, never()).deleteById(any(Long.class));
    }
}