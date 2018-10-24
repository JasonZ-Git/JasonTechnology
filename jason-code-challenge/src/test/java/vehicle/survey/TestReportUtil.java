package vehicle.survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jason.vehicle.survey.ReportUtil;
import org.jason.vehicle.survey.VehicleRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TestReportUtil {

  private List<VehicleRecord> records;
  
  @Before
  public void setUp() throws Exception {
    this.records = new ArrayList<VehicleRecord>();
    VehicleRecord record1 = new VehicleRecord(98186, 98333, 1, "A");
    VehicleRecord record2 = new VehicleRecord(499718, 499886, 1, "A");
    VehicleRecord record3 = new VehicleRecord(638382, 638523, 1, "B");
    VehicleRecord record4 = new VehicleRecord(1016488, 1016648, 1, "A");
    this.records.add(record1);
    this.records.add(record2);
    this.records.add(record3);
    this.records.add(record4);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetRecordsInOneDirection() throws IOException {

    List<VehicleRecord> aRecords = ReportUtil.getRecordsOfOneDirection(this.records, "A");

    Assert.assertTrue(aRecords.size() == 3);

    List<VehicleRecord> bRecords = ReportUtil.getRecordsOfOneDirection(this.records, "B");

    Assert.assertTrue(bRecords.size() == 1);
  }
  
  @Test
  public void testCarNumberInADiection() {
    int number = ReportUtil.runCarNumberReportOfaDirection(this.records, "A");
    
    Assert.assertEquals(number, 3);
  }

}
