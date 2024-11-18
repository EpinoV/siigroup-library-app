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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public BookDTO newBook(BookDTO book) {
        AtomicReference<BookDTO> dto = new AtomicReference<>();
        Optional.ofNullable(
                        BookMapper.INSTANCE.dtoToEntity(book)
                )
                .ifPresent(etty -> {
                            dto.set(BookMapper.INSTANCE.entityToDTO(repository.saveAndFlush(etty)));
                        }
                );
        return dto.get();
    }

    @Override
    public List<BookDTO> getAllBooks() {
        log.info("getAllBooks");

        return Optional.of(
                        repository.findAll()
                )
                .map(BookMapper.INSTANCE::entityListToDTOList)
                .orElse(new ArrayList<>());
    }

    @Override
    public BookDTO getBook(Long bookId) {
        return Optional.ofNullable(
                        repository.findById(bookId).get()
                )
                .map(BookMapper.INSTANCE::entityToDTO)
                .orElseThrow(() -> new BookException(ConstantsUtil.ERROR_MESSAGE_NO_DATA_FOUND));
    }

    @Override
    public BookDTO getBook(String isbn) {
        return Optional.ofNullable(
                        repository.findByToIsbn(isbn).get()
                )
                .map(BookMapper.INSTANCE::entityToDTO)
                .orElseThrow(() -> new BookException(ConstantsUtil.ERROR_MESSAGE_NO_DATA_FOUND));
    }

    @Override
    public BookDTO updateBook(BookDTO book) {
        Book updateUser = repository.findById(book.getId())
                .orElseThrow(() -> new BookException(ConstantsUtil.ERROR_MESSAGE_NO_DATA_FOUND));

        AtomicReference<BookDTO> dto = new AtomicReference<>();
        Optional.of(
                        updateUser
                )
                .ifPresent(etty ->
                        dto.set(BookMapper.INSTANCE.entityToDTO(repository.saveAndFlush(etty)))
                );
        return dto.get();
    }

    @Override
    public Boolean deleteBook(Long id) {
        return null;
    }
}
