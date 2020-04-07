package spbstu.project.varann;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariationRepository extends JpaRepository<Variation, VariationID> {
    Variation findByChromAndPosAndRefAndAlt(String chrom, int pos, String ref, String alt);
}
