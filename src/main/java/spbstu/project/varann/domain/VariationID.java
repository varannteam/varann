package spbstu.project.varann.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VariationID implements Serializable {
    private String chrom;
    private int pos;
    private String ref;
    private String alt;
}
