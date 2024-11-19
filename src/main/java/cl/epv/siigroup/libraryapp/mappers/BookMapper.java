package cl.epv.siigroup.libraryapp.mappers;

import cl.epv.siigroup.libraryapp.dto.BookDTO;
import cl.epv.siigroup.libraryapp.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    /**
     * Método que mapea un objeto del tipo Entidad (BD) a Objeto DTO
     * @param entity
     * @return
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "isbn", source = "isbn")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "available", source = "available")
    BookDTO entityToDTO(Book entity);

    /**
     *Método que mapea un objeto del tipo DTO a Objeto Entidad (DB)
     * @param dto
     * @return
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "isbn", source = "isbn")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "available", source = "available")
    Book dtoToEntity(BookDTO dto);

    /**
     * Método que mapea una lista de objetos del tipo Entidad (BD) a lista de objetos DTO
     * @param entityList
     * @return
     */
    List<BookDTO> entityListToDTOList(List<Book> entityList);

    /**
     * Método que mapea una lista de objetos del tipo DTO a lista de objetos Entidad (DB)
     * @param dtoList
     * @return
     */
    List<Book> dtoListToEntityList(List<BookDTO> dtoList);

}
