package cl.epv.siigroup.libraryapp.service.impl;

import cl.epv.siigroup.libraryapp.dto.BookDTO;
import cl.epv.siigroup.libraryapp.exceptions.BookException;
import cl.epv.siigroup.libraryapp.mappers.BookMapper;
import cl.epv.siigroup.libraryapp.model.Book;
import cl.epv.siigroup.libraryapp.repository.BookRepository;
import cl.epv.siigroup.libraryapp.service.BookService;
import cl.epv.siigroup.libraryapp.util.ConstantsUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Transactional
    @Override
    public BookDTO newBook(BookDTO book) {
        repository.findByIsbn(book.getIsbn())
                .ifPresent(existingBook -> {
                    throw new BookException(ConstantsUtil.ERROR_MESSAGE_EXISTING_ISBN);
                });
        return BookMapper.INSTANCE.entityToDTO(repository.saveAndFlush(BookMapper.INSTANCE.dtoToEntity(book)));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        log.info("getAllBooks");

        return Optional.of(
                        repository.findAll()
                )
                .map(BookMapper.INSTANCE::entityListToDTOList)
                .orElseThrow(() -> new BookException(ConstantsUtil.ERROR_MESSAGE_NO_DATA_FOUND));
    }

    @Override
    public BookDTO getBook(Long bookId) {

        return BookMapper.INSTANCE.entityToDTO(repository.findById(bookId)
                .orElseThrow(() -> new BookException(ConstantsUtil.ERROR_MESSAGE_BOOK_NOT_FOUND)));
    }

    @Override
    public BookDTO getBook(String isbn) {
        return BookMapper.INSTANCE.entityToDTO(repository.findByIsbn(isbn)
                .orElseThrow(() -> new BookException(ConstantsUtil.ERROR_MESSAGE_BOOK_NOT_FOUND)));
    }

    @Transactional
    @Override
    public BookDTO updateBook(BookDTO book, Long bookId) {
        Book existingBook = repository.findById(bookId)
                .orElseThrow(() -> new BookException(ConstantsUtil.ERROR_MESSAGE_NO_DATA_FOUND));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setCategory(book.getCategory());
        existingBook.setAvailable(book.getAvailable());

        return BookMapper.INSTANCE.entityToDTO(repository.saveAndFlush(existingBook));
    }

    @Transactional
    @Override
    public Boolean deleteBook(Long id) {
        return repository.findById(id)
                .map(etty -> {
                    repository.deleteById(id);
                    return Boolean.TRUE;
                })
                .orElse(Boolean.FALSE);
    }
}
