package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoEspecialidadService.class)
public class MedicoEspecialidadServiceTest {
    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private MedicoEntity medico = new MedicoEntity();
	private List<EspecialidadEntity> especialidadList = new ArrayList<>();

    @BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
	}

    private void insertData() {
        for (int i = 0; i < 3; i++) {
			EspecialidadEntity especialidadEntity = factory.manufacturePojo(EspecialidadEntity.class);
			entityManager.persist(especialidadEntity);
			especialidadList.add(especialidadEntity);
		}
        medico = factory.manufacturePojo(MedicoEntity.class);
        entityManager.persist(medico);
    }
        
        @Test
        void testAddEspecialidad() throws EntityNotFoundException, IllegalOperationException {
            EspecialidadEntity newEspecialidad = factory.manufacturePojo(EspecialidadEntity.class);
            entityManager.persist(newEspecialidad);

            EspecialidadEntity especialidadEntity = medicoEspecialidadService.addEspecialidad(medico.getId(), newEspecialidad.getId());
            assertNotNull(especialidadEntity);
            


    }
    @Test
    void testAddEspecialidadInvalidMedico() throws EntityNotFoundException, IllegalOperationException {
		assertThrows(EntityNotFoundException.class, ()->{
			EspecialidadEntity especialidad = factory.manufacturePojo(EspecialidadEntity.class);
			entityManager.persist(especialidad);
			medicoEspecialidadService.addEspecialidad(0L, especialidad.getId());
		});
    }

    @Test
    void testAddEspecialidadInvalidEspecialidad() throws EntityNotFoundException, IllegalOperationException {
        assertThrows(EntityNotFoundException.class, ()->{
			MedicoEntity newMedico = factory.manufacturePojo(MedicoEntity.class);
			entityManager.persist(newMedico);
			medicoEspecialidadService.addEspecialidad(newMedico.getId(), 0L);
		});
    }

}
