package spbstu.project.varann;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VariationRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VariationRepository variationRepository;

    @Test
    public void whenFindByChromAndPosAndRefAndAlt_thenReturnVariation() {
        Variation variationFirst = new Variation("20", 14370, "G", "A", "Some info");
        entityManager.persist(variationFirst);
        entityManager.flush();

        Variation variationFound = variationRepository.findByChromAndPosAndRefAndAlt(variationFirst.getChrom(), variationFirst.getPos(), variationFirst.getRef(), variationFirst.getAlt());

        assertThat(variationFound.getChrom())
                .isEqualTo(variationFirst.getChrom());
    }
}
