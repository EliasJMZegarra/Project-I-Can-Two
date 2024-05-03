package upc.com.pe.trabajo_v1.service;

import jakarta.transaction.Transactional;
import upc.com.pe.trabajo_v1.entities.Rol;

import java.util.List;

public interface RolServiceInterface {
    @Transactional
    Rol registrar(Rol rol);

    List<Rol> listado();

    Rol eliminarRol(Integer codigo) throws Exception;
}
