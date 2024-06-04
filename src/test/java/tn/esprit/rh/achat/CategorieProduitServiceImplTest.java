package tn.esprit.rh.achat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;
import tn.esprit.rh.achat.services.ICategorieProduitService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class CategorieProduitServiceImplTest {

  @Mock
  private CategorieProduitRepository categorieProduitRepository;

  @InjectMocks
  private CategorieProduitServiceImpl categorieProduitService;

  @Test
  public void testRetrieveAllCategorieProduits() {
    // Mock data
    List<CategorieProduit> expectedCategories = Arrays.asList(new CategorieProduit(), new CategorieProduit());
    Mockito.when(categorieProduitRepository.findAll()).thenReturn(expectedCategories);

    // Call the service method
    List<CategorieProduit> actualCategories = categorieProduitService.retrieveAllCategorieProduits();

    // Assert results
    assertEquals(expectedCategories, actualCategories);
  }

  @Test
  public void testAddCategorieProduit() {
    // Mock data
    CategorieProduit newCategorie = new CategorieProduit();

    // Mock behavior
    Mockito.when(categorieProduitRepository.save(newCategorie)).thenReturn(newCategorie);

    // Call the service method
    CategorieProduit savedCategorie = categorieProduitService.addCategorieProduit(newCategorie);

    // Assert results
    assertEquals(newCategorie, savedCategorie);
    Mockito.verify(categorieProduitRepository, Mockito.times(1)).save(newCategorie);
  }

  @Test
  public void testDeleteCategorieProduit() {
    // Mock data
    Long idToDelete = 1L;

    // Call the service method
    categorieProduitService.deleteCategorieProduit(idToDelete);

    // Assert that the repository is called with the correct id
    Mockito.verify(categorieProduitRepository, Mockito.times(1)).deleteById(idToDelete);
  }

  @Test
  public void testUpdateCategorieProduit() {
    // Mock data
    CategorieProduit existingCategorie = new CategorieProduit();
    existingCategorie.setIdCategorieProduit(1L);

    // Mock behavior
    Mockito.when(categorieProduitRepository.save(existingCategorie)).thenReturn(existingCategorie);

    // Call the service method
    CategorieProduit updatedCategorie = categorieProduitService.updateCategorieProduit(existingCategorie);

    // Assert results
    assertEquals(existingCategorie, updatedCategorie);
    Mockito.verify(categorieProduitRepository, Mockito.times(1)).save(existingCategorie);
  }

  @Test
  public void testRetrieveCategorieProduit() {
    // Mock data
    Long idToFind = 2L;
    CategorieProduit expectedCategorie = new CategorieProduit();

    expectedCategorie.setIdCategorieProduit(idToFind);

    // Mock behavior
    Mockito.when(categorieProduitRepository.findById(idToFind)).thenReturn(Optional.of(expectedCategorie));

    // Call the service method
    CategorieProduit retrievedCategorie = categorieProduitService.retrieveCategorieProduit(idToFind);

    // Assert results
    assertEquals(expectedCategorie, retrievedCategorie);
  }

  @Test
  public void testRetrieveCategorieProduit_NotFound() {
    // Mock data
    Long idToFind = 3L;

    // Mock behavior
    Mockito.when(categorieProduitRepository.findById(idToFind)).thenReturn(Optional.empty());

    // Call the service method
    CategorieProduit retrievedCategorie = categorieProduitService.retrieveCategorieProduit(idToFind);

    // Assert results
    assertNull(retrievedCategorie);
  }

}
