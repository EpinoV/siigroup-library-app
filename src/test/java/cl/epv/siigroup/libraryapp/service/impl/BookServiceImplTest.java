package cl.epv.siigroup.libraryapp.service.impl;

import cl.epv.siigroup.libraryapp.dto.BookDTO;
import cl.epv.siigroup.libraryapp.model.Book;
import cl.epv.siigroup.libraryapp.repository.BookRepository;
import cl.epv.siigroup.libraryapp.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository repository;

    //@InjectMocks
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
    void newBook() {
        when(repository.findByIsbn(mockModel.getIsbn())).thenReturn(Optional.empty());
        when(repository.saveAndFlush(mockModel)).thenReturn(mockModel);

        BookDTO dtoResponse = service.newBook(mockDTO);

        assertNotNull(dtoResponse);
        assertEquals(mockModel.getId(), dtoResponse.getId());
        verify(repository).saveAndFlush(mockModel);
    }

    @Test
    void getAllBooks() {
    }

    @Test
    void getBook() {
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
    void testGetBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}