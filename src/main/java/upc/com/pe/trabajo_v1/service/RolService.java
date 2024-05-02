package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Rol;
import upc.com.pe.trabajo_v1.entities.Usuario;
import upc.com.pe.trabajo_v1.repository.RolRepository;

import java.util.List;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    @Transactional

    public Rol registrar(Rol rol){return rolRepository.save(rol);}

    public List<Rol> listado(){
        return rolRepository.findAll();
    }

    public Rol eliminarRol(Integer codigo) throws Exception{
        Rol rol = rolRepository.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
        rolRepository.delete(rol);
        return rol;
    }
}
