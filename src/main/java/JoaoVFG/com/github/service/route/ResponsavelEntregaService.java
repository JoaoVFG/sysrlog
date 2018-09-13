package JoaoVFG.com.github.service.route;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.entity.ResponsavelEntregaCepRota;
import JoaoVFG.com.github.repositories.ResponsavelEntregaRepository;
import JoaoVFG.com.github.services.exception.DataIntegrityException;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

@Service
public class ResponsavelEntregaService {

	@Autowired
	ResponsavelEntregaRepository responsavelEntregaRepository;

	public ResponsavelEntregaCepRota findById(Integer id) {
		Optional<ResponsavelEntregaCepRota> responsavelEntregaCepRota = responsavelEntregaRepository.findById(id);
		return responsavelEntregaCepRota.orElseThrow(() -> new ObjectNotFoundException(
				"Responsável não encontrado! Id: " + id + ". Tipo: " + ResponsavelEntregaCepRota.class.getName()));
	}
	
	public List<ResponsavelEntregaCepRota> findByIdRota(Integer idRota){
		List<ResponsavelEntregaCepRota> listResponsavelEntrega = responsavelEntregaRepository.findByIdRota(idRota);
		return listResponsavelEntrega;
	}
	
	public void deletaResponsavelEntrega(Integer id) {
		findById(id);
		try {
			responsavelEntregaRepository.deleteById(id);
		} catch (Exception e) {
			throw new DataIntegrityException("NAO E POSSIVEL EXCLUIR ESSE RESPONSÁVEL POR ENTREGA.");
		}
	}
}
