package JoaoVFG.com.github.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.entity.RotaEndereco;

@Repository
public interface RotaEnderecoRepository extends JpaRepository<RotaEndereco, Integer>{
	
	
	@Transactional(readOnly = true)
	@Query("SELECT rotaEndereco FROM RotaEndereco rotaEndereco WHERE rotaEndereco.id = :id")
	public RotaEndereco BuscaPorId(@Param("id") Integer id);
	
	@Transactional(readOnly = true)
	@Query("SELECT rotaEndereco FROM RotaEndereco rotaEndereco WHERE rotaEndereco.rota.id = :idRota")
	public List<RotaEndereco> findByRotaId(@Param("idRota") Integer idRota);
}
