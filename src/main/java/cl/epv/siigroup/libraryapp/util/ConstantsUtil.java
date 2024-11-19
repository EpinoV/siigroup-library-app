package cl.epv.siigroup.libraryapp.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantsUtil {
    public static final String ERROR_MESSAGE_BOOK_NOT_FOUND = "No se encuentra el libro en la base de datos";
    public static final String ERROR_MESSAGE_NO_DATA_FOUND = "No se encuentra el registro en la base de datos";
    public static final String ERROR_MESSAGE_EXISTING_ISBN = "El ISBN ingresado ya se encuentra registrado";

}
