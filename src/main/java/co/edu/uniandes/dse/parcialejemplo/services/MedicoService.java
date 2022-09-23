package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;


/*
 * (10%) Cree la clase correspondiente para la lógica de médico. 
 * Implemente unicamente el método createMedicos. 
 * Valide que el registro médico inicie solo con los caracteres "RM" (e.g., RM1745).
 */
@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Transactional
    public MedicoEntity createMedico(MedicoEntity medico) throws EntityNotFoundException, IllegalOperationException{
        if (!medico.getRegistroMedico().startsWith("RM")) {
            throw new IllegalOperationException("No empieza con RM");
        }
    return medicoRepository.save(medico); 
    }

}