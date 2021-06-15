package com.rene.library.resources;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponse {

	
    public static ResponseEntity<Object> handleResponse(HttpStatus status, String message, Object data ) {
    
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("status", status);
        map.put("message", message);
        map.put("data", data);

        
        return new ResponseEntity<Object>(map, status);
        
    }
    
}
