package utils;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class HttpHeaderUtil {

    public static HttpHeaders createHeaders(HttpHeaders httpHeaders, String authenticationInfo) {
        httpHeaders.set("Authorization", authenticationInfo);
        return httpHeaders;
    }

}
