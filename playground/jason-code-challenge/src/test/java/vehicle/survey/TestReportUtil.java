package vehicle.survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jason.vehicle.survey.ReportUtil;
import org.jason.vehicle.survey.VehicleRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestReportUtil {

  private static List<VehicleRecord> records;

  @BeforeAll
  public static void setUp() throws Exception {
    records = new ArrayList<VehicleRecord>();
    VehicleRecord record1 = new VehicleRecord(98186, 98333, 1, "A");
    VehicleRecord record2 = new VehicleRecord(499718, 499886, 1, "A");
    VehicleRecord record3 = new VehicleRecord(638382, 638523, 1, "B");
    VehicleRecord record4 = new VehicleRecord(1016488, 1016648, 1, "A");
    records.add(record1);
    records.add(record2);
    records.add(record3);
    records.add(record4);
  }

  @AfterAll
  public static void tearDown() throws Exception {}

  @Test
  public void testGetRecordsInOneDirection() throws IOException {

    List<VehicleRecord> aRecords = ReportUtil.getRecordsOfOneDirection(records, "A");

    Assertions.assertTrue(aRecords.size() == 3);

    List<VehicleRecord> bRecords = ReportUtil.getRecordsOfOneDirection(records, "B");

    Assertions.assertTrue(bRecords.size() == 1);
  }

  @Test
  public void testCarNumberInADiection() {
    int number = ReportUtil.runCarNumberReportOfaDirection(records, "A");

    Assertions.assertEquals(number, 3);
  }

}
