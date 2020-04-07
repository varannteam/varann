package spbstu.project.varann;

import htsjdk.variant.variantcontext.Allele;
import htsjdk.variant.variantcontext.VariantContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import spbstu.project.varann.parser.Parser;
import spbstu.project.varann.parser.VcfParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VcfParserTest {
  private Path path = Paths.get("src/main/resources/vcf/test.vcf");

  private Parser parser;
  private Stream<VariantContext> parsedData;

  @Before
  public void prepareList() {
    try {
      parser = new VcfParser();
      parsedData = parser.parse(Files.newInputStream(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void posShouldBeParsedCorrect() {
    final Integer[] POSITIONS = {
        14370,
        17330,
        1110696,
        1230237,
        1234567
    };

    var posList = parsedData.map(vc ->
        vc.getStart())
        .collect(Collectors.toList());

    Assert.assertArrayEquals(POSITIONS, posList.toArray());
  }

  @Test
  public void refShouldBeParsedCorrect() {
    final String[] REFS = {
        "G",
        "T",
        "A",
        "T",
        "GTC"
    };

    var refList = parsedData.map(vc ->
        vc.getReference()
            .getDisplayString())
        .collect(Collectors.toList());

    Assert.assertArrayEquals(REFS, refList.toArray());
  }


  @Test
  public void chromShouldBeParsedCorrect() {
    final String CRHOM = "20";

    parsedData.forEach(vc -> Assert.assertEquals(CRHOM, vc.getContig()));
  }

  @Test
  public void altShouldBeParsedCorrect() {
    final String[][] ALTS = {
        {"A"},
        {"A"},
        {"G", "T"},
        {},
        {"G", "GTCT"}
    };

    var vcList = parsedData.collect(Collectors.toList());

    String parsed = "";
    String expected = "";

    for (int i = 0; i < vcList.size(); i++) {
      List<Allele> alts = vcList.get(i).getAlternateAlleles();

      for (int j = 0; j < alts.size(); j++) {
        parsed = alts.get(j).toString();
        expected = ALTS[i][j];

        Assert.assertEquals(expected, parsed);
      }
    }
  }


  @Test
  public void infoShouldBeParsedCorrect() {
    final String [][] info = {
        {"HOMSEQ=.", "DP=14", "NS=3", "AF=0.5", "DB=true"},
        {"H2=true", "DP=11", "NS=3", "AF=0.017"},
        {"AA=T", "DP=10", "NS=2", "AF=[0.333, 0.667]", "DB=true" },
        {"AA=T", "DP=13", "NS=3"},
        {"AA=G", "DP=9", "NS=3"}
    };

    var vcList = parsedData.collect(Collectors.toList());

    String parsed = "";
    String expected = "";

    for (int i = 0; i < vcList.size(); ++i) {
      var commonInfo = vcList.get(i).getCommonInfo().getAttributes();

      for (int j = 0; j < commonInfo.size(); ++j) {
        var list = commonInfo.entrySet().stream().collect(Collectors.toList());

        expected = info[i][j];
        parsed = list.get(j).toString();

        Assert.assertEquals(expected, parsed);
      }
    }
  }
}

