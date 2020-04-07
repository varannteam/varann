package spbstu.project.varann;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(VariationID.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Variation {
    @Id
    private String chrom;

    @Id
    private int pos;

    @Id
    private String ref;

    @Id
    private String alt;

    private String info;
}
