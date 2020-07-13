package spbstu.project.varann.controller;

import lombok.RequiredArgsConstructor;
import spbstu.project.varann.domain.Variation;
import spbstu.project.varann.domain.VariationID;
import org.springframework.web.bind.annotation.*;
import spbstu.project.varann.service.VariationService;
import org.springframework.web.multipart.MultipartFile;
import spbstu.project.varann.exception.AnnotationException;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("variation")
public class VariationController {
  private final VariationService variationService;

  @PostMapping
  @PreAuthorize("hasAuthority(ApplicationAuthority.POST.getAuthority())")
  public void storeVariation(@RequestParam("file") MultipartFile file) {
    try {
      variationService.store(file.getInputStream());

    } catch (Exception e) {
      throw new AnnotationException("Annotations extraction has failed.", e);
    }
  }

  @GetMapping
  @PreAuthorize("hasAuthority(ApplicationAuthority.ANNOTATE.getAuthority())")
  public Variation annotateVariation(@RequestBody VariationID variationID) {
    return variationService.annotate(variationID);
  }
}
