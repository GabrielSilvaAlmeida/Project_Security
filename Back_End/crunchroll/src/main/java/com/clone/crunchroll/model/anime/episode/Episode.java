package com.clone.crunchroll.model.anime.episode;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Episode {

    private String nomeEp;
    private String numeroEp;
    private String descricaoEp;
    private String videoEp;
    
}
