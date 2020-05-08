package spbstu.project.varann.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spbstu.project.varann.domain.Variation;
import spbstu.project.varann.domain.VariationID;
import spbstu.project.varann.exception.AnnotationException;
import spbstu.project.varann.service.VariationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("variation")
public class VariationController {
  private final VariationService variationService;

  @PostMapping
  public void storeVariation(@RequestParam("file") MultipartFile file) {
    try {
      variationService.store(file.getInputStream());
    } catch (Exception e) {
      throw new AnnotationException("Annotations extraction has failed.", e);
    }
  }

  @GetMapping
  public Variation annotateVariation(@RequestBody VariationID variationID) {
    return variationService.annotate(variationID);
  }
}
