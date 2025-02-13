package vehicle.survey;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.jason.vehicle.survey.DataParserUtil;
import org.jason.vehicle.survey.VehicleRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestDataParser {

  private static File file;

  @BeforeAll
  public static void setUp() throws Exception {
    file = new File("src/test/resources/testData.txt");
  }

  @AfterAll
  public static void tearDown() throws Exception {}

  @Test
  public void testReadingFile() throws IOException {

    List<String> allRecords = DataParserUtil.readFile(file);

    Assertions.assertTrue(allRecords.size() == 14);
    Assertions.assertTrue(allRecords.get(0).equals("A98186"));
  }

  @Test
  public void testRemoveUnusedRecords() throws IOException {
    List<String> allRecords = Arrays.asList("A98186", "A98333", "A638379", "B638382", "A638520", "B638523");
    List<String> newRecords = DataParserUtil.removeUnusedRecord(allRecords);

    Assertions.assertTrue(newRecords.size() == 4);
  }

  @Test
  public void testParseIntoRecords() {
    List<String> allRecords = Arrays.asList("A98186", "A98333", "A638379", "B638382", "A638520", "B638523", "A8663", "B8666", "A8800", "B8803");
    List<VehicleRecord> records = DataParserUtil.parseDataIntoRecords(allRecords);

    Assertions.assertTrue(records.size() == 3);
    Assertions.assertEquals(records.get(0).getFirstAxleRecordingTime(), 98186);
    Assertions.assertEquals(records.get(0).getSecordAxleRecordingTime(), 98333);
    Assertions.assertEquals(records.get(0).getDateNumber(), 1);

    Assertions.assertEquals(records.get(2).getDateNumber(), 2);
  }

}
