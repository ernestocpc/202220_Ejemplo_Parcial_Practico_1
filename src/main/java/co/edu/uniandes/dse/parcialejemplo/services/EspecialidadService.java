package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;

/*
 * (10%) Cree la clase correspondiente para la lógica de especialidad. 
 * Implemente unicamente el método createEspecialidad. 
 * Valide que la descripción tenga como mínimo 10 caracteres.
 */
@Service
public class EspecialidadService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws EntityNotFoundException, IllegalOperationException{
        if(especialidad.getDescripcion().length()<10){
            throw new IllegalOperationException("Ta chikita la descrpcion");
        }
    return especialidadRepository.save(especialidad); 
    }
}