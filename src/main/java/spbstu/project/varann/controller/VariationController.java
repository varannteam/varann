package spbstu.project.varann.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import spbstu.project.varann.domain.Variation;
import spbstu.project.varann.domain.VariationID;
import org.springframework.web.bind.annotation.*;
import spbstu.project.varann.domain.VariationIdListDto;
import spbstu.project.varann.domain.VariationListDto;
import spbstu.project.varann.service.VariationService;
import org.springframework.web.multipart.MultipartFile;
import spbstu.project.varann.exception.AnnotationException;

import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("variation")
public class VariationController {
    private final VariationService variationService;

    @PostMapping
    @PreAuthorize("hasAuthority('POST')")
    public void storeVariation(@RequestParam("file") MultipartFile file) {
        try {
            variationService.store(file.getInputStream());
        } catch (Exception e) {
            throw new AnnotationException("Annotations extraction has failed.", e);
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ANNOTATE')")
    public Variation annotateVariation(@RequestBody VariationID variationID) {
        return variationService.annotate(variationID);
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('ANNOTATE')")
    public ResponseEntity<VariationListDto> annotateVariationList(@RequestBody VariationIdListDto variationIdListDto) {
        try {
            var variationList = variationService.annotate(variationIdListDto.getVariationIDList());
            var variationListDto = new VariationListDto(variationList);

            return ResponseEntity.ok(variationListDto);
        } catch (Exception e) {
            throw new AnnotationException("Annotations extraction has failed.", e);
        }
    }
}
