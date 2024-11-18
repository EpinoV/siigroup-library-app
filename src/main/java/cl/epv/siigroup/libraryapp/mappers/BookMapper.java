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
     *
     * @param entity
     * @return
     */
/*    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phones", source = "phones", ignore = true)*/
    BookDTO entityToDTO(Book entity);

    /**
     *
     * @param dto
     * @return
     */
/*    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phones", source = "phones", ignore = true)*/
    Book dtoToEntity(BookDTO dto);

    /**
     *
     * @param entityList
     * @return
     */
    List<BookDTO> entityListToDTOList(List<Book> entityList);

    /**
     *
     * @param dtoList
     * @return
     */
    List<Book> dtoListToEntityList(List<BookDTO> dtoList);

}
