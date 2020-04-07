package spbstu.project.varann.parser;

import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFIterator;
import htsjdk.variant.vcf.VCFIteratorBuilder;
import spbstu.project.varann.exception.ParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public class VcfParser implements Parser {
  @Override
  public Stream<VariantContext> parse(InputStream inputStream) throws ParserException {
    Stream<VariantContext> stream = null;

    try {
      VCFIterator vcfIter = new VCFIteratorBuilder().open(inputStream);
      stream = vcfIter.stream();

    } catch (IOException e) {
      throw new ParserException(e);
    }

    return stream;
  }
}
