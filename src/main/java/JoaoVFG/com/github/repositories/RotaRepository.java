package JoaoVFG.com.github.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import JoaoVFG.com.github.entity.Rota;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Integer>{

	@Transactional(readOnly = true)
	@Query("SELECT rota FROM Rota rota WHERE rota.id = :id")
	public Rota buscaPorId(@Param("id") Integer id);
	
	@Transactional(readOnly = true)
	@Query("SELECT rota FROM Rota rota WHERE rota.empresa.id = :idEmpresa")
	public List<Rota> findByIdEmpresa(@Param("idEmpresa") Integer idEmpresa);
	
	@Transactional(readOnly = true)
	@Query("SELECT rota FROM Rota rota WHERE rota.user.id = :idUser")
	public List<Rota> findByIdUser(@Param("idUser") Integer idUser);
}
