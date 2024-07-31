package br.com.alura.appmusicas.repository;

import br.com.alura.appmusicas.domain.Artista;
import br.com.alura.appmusicas.domain.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    @Query("SELECT a FROM Artista a WHERE a.nome ILIKE %:nomeArtista% ORDER BY a.nome")
    Optional<Artista> buscarArtista(String nomeArtista);

    @Query("SELECT m FROM Artista a JOIN a.musicas m ORDER BY a.nome")
    List<Musica> listarMusicas();

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a = :artista ORDER BY m.album")
    List<Musica> buscarMusicasPorArtista(Artista artista);
}
