package JoaoVFG.com.github.repositories;

import java.util.LinkedList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.domain.Cidade;
import JoaoVFG.com.github.domain.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	//Busca de cidades por nome
	@Transactional(readOnly = true)
	public Optional<LinkedList<Cidade>> findByNomeContains(String nome);
	
	@Transactional(readOnly = true)
	public Cidade findBynome(String nome);
	
	//Busca de Cidades por id_estado
	@Transactional(readOnly = true)
	public Optional<LinkedList<Cidade>> findByEstado(Estado estado);
	
	//Busca de Cidades por nome e Id_estado
	@Transactional(readOnly = true)
	@Query("SELECT cidade FROM Cidade cidade WHERE cidade.estado.id = :estadoId AND cidade.nome like :cidadeNome")
	public Optional<LinkedList<Cidade>> findCidadesEstadoIdNomeCidades(@Param("estadoId")Integer estadoId, @Param("cidadeNome")String cidadeNome);
	
	
	//Busca Auxiliar cidades por nome e sigla do estado
	@Transactional(readOnly = true)
	@Nullable
	@Query("SELECT cidade FROM Cidade cidade WHERE cidade.estado.id = :estadoId AND cidade.nome like :cidadeNome")
	public Cidade findCidadesEstadoIdNomeCidadesAux(@Nullable@Param("estadoId")Integer estadoId, @Nullable@Param("cidadeNome")String cidadeNome);
	
}
