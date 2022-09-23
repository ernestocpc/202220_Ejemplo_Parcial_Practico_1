package co.edu.uniandes.dse.parcialejemplo.services;
/*
 * (10%) Cree la clase correspondiente para la lógica de la asociación entre médico y especialidad. 
 * Implemente unicamente el método addEspecialidad. 
 * Este método recibe como parámetro el id del médico, el id de la especidad y le agrega la especialidad al médico. 
 * Valide que tanto el médico como la especialidad existan.
 */

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;

public class MedicoEspecialidadService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional
    public EspecialidadEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException, IllegalOperationException{
        Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);
        if (medicoEntity.isEmpty()) throw new EntityNotFoundException("No se encontro medico");

        Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);
        if(especialidadEntity.isEmpty()) throw new EntityNotFoundException("No se encontro especialidad");

        medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
        return especialidadEntity.get();
    }
}
