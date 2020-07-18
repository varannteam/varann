package spbstu.project.varann.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VariationIdListDto {
    private List<VariationID> variationIDList;
}
