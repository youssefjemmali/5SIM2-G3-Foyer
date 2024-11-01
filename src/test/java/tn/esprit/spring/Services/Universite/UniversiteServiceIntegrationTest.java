// package tn.esprit.spring.Services.Universite;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.testcontainers.containers.MySQLContainer;
// import org.testcontainers.junit.jupiter.Container;
// import org.testcontainers.junit.jupiter.Testcontainers;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;
// import tn.esprit.spring.DAO.Entities.TypeUniversite;
// import tn.esprit.spring.DAO.Entities.Universite;

// import static org.junit.jupiter.api.Assertions.*;

// import java.util.List;

// @Testcontainers
// @SpringBootTest // Pour démarrer le contexte de l'application Spring
// @ActiveProfiles("test") // Pour activer le profil de test
// public class UniversiteServiceIntegrationTest {

//     @Container
//     public MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:5.7")
//             .withDatabaseName("testdb")
//             .withUsername("root")
//             .withPassword("root")
//             .withReuse(true);

//     @Autowired
//     private UniversiteService universiteService;

//     @BeforeEach
//     public void setUp() {
//         Universite universite = new Universite();
//         universite.setNomUniversite("Test University");
//         universiteService.addOrUpdate(universite); 
//     }

//     @Test
//     public void testAddOrUpdate() {
//         Universite universite = new Universite(1L, "ISET RADES", "Rades, Ben Arous, Tunis, Tunisie", TypeUniversite.PUBLIC, null, null);
//         Universite result = universiteService.addOrUpdate(universite);
        
//         System.out.println("Result from addOrUpdate: " + result);
//         // Log the state of the database or the list of universities
//         List<Universite> allUniversities = universiteService.findAll();
//         System.out.println("All universities: " + allUniversities);
        
//         assertNotNull(result);
//         assertEquals(universite.getNomUniversite(), result.getNomUniversite());
//     }
    
//     @Test
//     public void testGetAll() {
//         Universite universite1 = new Universite(1L, "ISET RADES", "Rades, Ben Arous, Tunis, Tunisie", TypeUniversite.PUBLIC, null, null);
//         Universite universite2 = new Universite(2L, "ISET TUNIS", "Tunis", TypeUniversite.PRIVE, null, null);
        
//         universiteService.addOrUpdate(universite1);
//         universiteService.addOrUpdate(universite2);
        
//         List<Universite> result = universiteService.findAll();
        
//         System.out.println("Result from findAll: " + result);
        
//         assertNotNull(result);
//         assertEquals(2, result.size());
//         assertTrue(result.contains(universite1));
//         assertTrue(result.contains(universite2));
//     }

// }
