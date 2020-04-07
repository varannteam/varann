package spbstu.project.varann.parser;

import htsjdk.variant.variantcontext.VariantContext;

import java.io.InputStream;
import java.util.stream.Stream;

public interface Parser {
  Stream<VariantContext> parse(InputStream inputStream);
}
