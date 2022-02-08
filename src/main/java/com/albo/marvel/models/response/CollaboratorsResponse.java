package com.albo.marvel.models.response;

import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class CollaboratorsResponse {
    
    private Date last_sync;
    private Set<String> editors;
    private Set<String> writers;
    private Set<String> colorists;

    public CollaboratorsResponse() {
    }
    
    public CollaboratorsResponse(Date last_sync){
        this.last_sync = last_sync;
        editors = new HashSet<String>();
        writers = new HashSet<String>();
        colorists = new HashSet<String>();
    }
    
}
