package com.fargotest.core.errorhandling;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder implements ErrorDecoder {
    
    @Override
    public Exception decode(String s, Response response) {

        switch(response.status()) {
            case 400:
                return new Exception("Bad Request Through Feign Client");
            case 401:
                return new Exception("Unauthorized Request Through Feign Client");
            case 404:
                return new Exception("Unidentified Request Through Feign Client");
            default:
                return new Exception("Common Feign Exception");
        }
    }
}
