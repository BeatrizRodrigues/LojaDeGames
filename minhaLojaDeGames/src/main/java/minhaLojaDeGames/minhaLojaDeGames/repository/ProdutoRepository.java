package minhaLojaDeGames.minhaLojaDeGames.repository;

import minhaLojaDeGames.minhaLojaDeGames.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {
	public List<Produto> findAllByTituloContainingIgnoreCase(String titulo);
}
