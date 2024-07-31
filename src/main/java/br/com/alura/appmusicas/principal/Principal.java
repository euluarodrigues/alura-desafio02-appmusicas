package br.com.alura.appmusicas.principal;

import br.com.alura.appmusicas.domain.Artista;
import br.com.alura.appmusicas.domain.Musica;
import br.com.alura.appmusicas.domain.Tipo;
import br.com.alura.appmusicas.repository.ArtistaRepository;
import br.com.alura.appmusicas.service.ConsultaChatGPT;

import java.util.*;

public class Principal {
    private ArtistaRepository repository;
    private Scanner s = new Scanner(System.in);
    private List<Artista> listaArtistas = new ArrayList<>();
    private List<Musica> listaMusicas = new ArrayList<>();

    public Principal(ArtistaRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Cadastrar artista
                    2 - Cadastrar música
                    3 - Listar músicas
                    4 - Buscar músicas por artista
                    5 - Pesquisar perfil de um artista
                                        
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = s.nextInt();
            s.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5: bioArtista();
                    break;
            }
        }
    }

    private void cadastrarArtista() {
        System.out.println("Qual é o nome do artista: ");
        var artistaDigitado = s.nextLine();
        System.out.println("Qual é o tipo do artista (solo, dupla ou banda)");
        var tipoDigitado = s.nextLine();
        Tipo tipo = Tipo.fromString(tipoDigitado);
        Artista artista = new Artista(artistaDigitado, tipo);
        repository.save(artista);
        System.out.println(artista);
    }

    private void listarArtistas(){
        listaArtistas = repository.findAll();
        System.out.println("Artistas salvos: ");
        listaArtistas.stream()
                .sorted(Comparator.comparing(Artista::getNome))
                .forEach(artista -> System.out.println("-" + artista.getNome()));
    }

    private void cadastrarMusicas() {
        listarArtistas();
        System.out.println("Escolha um artista pelo nome para cadastrar as músicas: ");
        var nomeArtista = s.nextLine();
        Optional<Artista> artistaBuscado = repository.buscarArtista(nomeArtista);
        if (artistaBuscado.isPresent()) {
            System.out.println("Escreva o nome da música: ");
            var nomeMusica = s.nextLine();
            System.out.println("Escreva o nome do álbum: ");
            var nomeAlbum = s.nextLine();
            var artistaEncontrado = artistaBuscado.get();
            Musica musica = new Musica(nomeMusica, nomeAlbum, artistaEncontrado);
            listaMusicas.add(musica);
            artistaEncontrado.setMusicas(listaMusicas);
            repository.save(artistaEncontrado);
        } else {
            System.out.println("Artista não encontrado");
        }
    }

    private void listarMusicas() {
        listaMusicas = repository.listarMusicas();
        listaMusicas.forEach(m -> System.out.println("Artista: " + m.getArtista().getNome() +
                ", Música: " + m.getNomeMusica() +
                ", Álbum: " + m.getAlbum()));
    }

    private void buscarMusicasPorArtista() {
        listarArtistas();
        System.out.println("Escolha um artista pelo nome para buscar as músicas: ");
        var nomeArtista = s.nextLine();
        Optional<Artista> artistaBuscado = repository.buscarArtista(nomeArtista);
        if (artistaBuscado.isPresent()) {
            var artistaEncontrado = artistaBuscado.get();
            listaMusicas = repository.buscarMusicasPorArtista(artistaEncontrado);
            listaMusicas.forEach(m -> System.out.println("Artista: " + m.getArtista().getNome() +
                    ", Música: " + m.getNomeMusica() +
                    ", Álbum: " + m.getAlbum()));
        } else {
            System.out.println("Artista não encontrado");
        }
    }

    public void bioArtista() {
        listarArtistas();
        System.out.println("Escolha um artista pelo nome para buscar as músicas: ");
        var nomeArtista = s.nextLine();
        System.out.println(ConsultaChatGPT.obterBioArtista(nomeArtista));
    }
}