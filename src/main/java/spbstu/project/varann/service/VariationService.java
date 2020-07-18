package spbstu.project.varann.service;

import htsjdk.variant.variantcontext.VariantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spbstu.project.varann.domain.Variation;
import spbstu.project.varann.domain.VariationID;
import spbstu.project.varann.VariationRepository;
import spbstu.project.varann.parser.VcfParser;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VariationService {
    private final VariationRepository repository;
    private final VcfParser parser;

    public Variation annotate(VariationID variationID) {
        return repository.findById(variationID).get();
    }

    public List<Variation> annotate(List<VariationID> variationIDS) {
        return variationIDS.stream()
                .map(varId -> annotate(varId))
                .collect(Collectors.toList());
    }

    public List<Variation> store(InputStream inputStream) {
        List<Variation> variations = parser.parse(inputStream)
                .map(this::toVariation)
                .collect(Collectors.toList());

        return repository.saveAll(variations);
    }

    private Variation toVariation(VariantContext variantContext) {
        return Variation.builder()
                .chrom(variantContext.getContig())
                .pos(variantContext.getStart())
                .ref(variantContext.getReference().getDisplayString())
                .alt(variantContext.getAlternateAlleles().toString())
                .info(variantContext.getCommonInfo().getAttributes().toString())
                .build();
    }
}
