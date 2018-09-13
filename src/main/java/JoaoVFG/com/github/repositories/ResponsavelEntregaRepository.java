package JoaoVFG.com.github.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.entity.ResponsavelEntregaCepRota;

@Repository
public interface ResponsavelEntregaRepository extends JpaRepository<ResponsavelEntregaCepRota, Integer>{

	@Transactional(readOnly = true)
	@Query("SELECT responsavelEntrega FROM ResponsavelEntregaCepRota responsavelEntrega WHERE responsavelEntrega.id = :id")
	public ResponsavelEntregaCepRota buscaPorId(@Param("id") Integer id);
	
	@Transactional(readOnly = true)
	@Query("SELECT responsavelEntrega FROM ResponsavelEntregaCepRota responsavelEntrega WHERE responsavelEntrega.rota.id = :idRota ")
	public List<ResponsavelEntregaCepRota> findByIdRota(@Param("idRota") Integer idRota);
}
