package br.com.alura.appmusicas.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMusica;
    private String album;
    @ManyToOne
    private Artista artista;

    public Musica(){}

    public Musica(String nomeMusica, String album, Artista artista){
        this.nomeMusica = nomeMusica;
        this.album = album;
        this.artista = artista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "nomeMusica='" + nomeMusica + '\'' +
                ", Album='" + album + '\'' +
                '}';
    }
}
