package JoaoVFG.com.github.service.route;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import JoaoVFG.com.github.entity.ResponsavelEntrega;
import JoaoVFG.com.github.repositories.ResponsavelEntregaRepository;
import JoaoVFG.com.github.services.exception.DataIntegrityException;
import JoaoVFG.com.github.services.exception.ObjectNotFoundException;

@Service
public class ResponsavelEntregaService {

	@Autowired
	ResponsavelEntregaRepository responsavelEntregaRepository;

	public ResponsavelEntrega findById(Integer id) {
		Optional<ResponsavelEntrega> responsavelEntrega = responsavelEntregaRepository.findById(id);
		return responsavelEntrega.orElseThrow(() -> new ObjectNotFoundException(
				"Responsável não encontrado! Id: " + id + ". Tipo: " + ResponsavelEntrega.class.getName()));
	}
	
	public List<ResponsavelEntrega> findByIdRota(Integer idRota){
		List<ResponsavelEntrega> listResponsavelEntrega = responsavelEntregaRepository.findByIdRota(idRota);
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
