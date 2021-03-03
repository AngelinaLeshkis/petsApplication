package utils;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CheckEntityExistence {

    private static Map<Object, HttpStatus> mapOfEntities = new HashMap<>();

    public static Map<Object, HttpStatus> getMapOfEntities() {
        return mapOfEntities;
    }

    public static void setMapOfEntities(Object obj, HttpStatus httpStatus) {
        mapOfEntities.put(obj, httpStatus);
    }

}
