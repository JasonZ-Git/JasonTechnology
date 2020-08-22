package vehicle.survey;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jason.vehicle.survey.DataParserUtil;
import org.jason.vehicle.survey.VehicleRecord;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataParser {

    private File file;

    @Before
    public void setUp() throws Exception {
        this.file = new File("src/test/resources/testData.txt");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReadingFile() throws IOException {

        List<String> allRecords = DataParserUtil.readFile(this.file);

        Assert.assertTrue(allRecords.size() == 14);
        Assert.assertTrue(allRecords.get(0).equals("A98186"));
    }

    @Test
    public void testRemoveUnusedRecords() throws IOException {
        List<String> allRecords = Arrays.asList("A98186", "A98333", "A638379", "B638382", "A638520", "B638523");
        List<String> newRecords = DataParserUtil.removeUnusedRecord(allRecords);

        Assert.assertTrue(newRecords.size() == 4);
    }

    @Test
    public void testParseIntoRecords() {
        List<String> allRecords = Arrays.asList("A98186", "A98333", "A638379", "B638382", "A638520", "B638523", "A8663", "B8666", "A8800", "B8803");
        List<VehicleRecord> records = DataParserUtil.parseDataIntoRecords(allRecords);

        Assert.assertTrue(records.size() == 3);
        Assert.assertEquals("", records.get(0).getFirstAxleRecordingTime(), 98186);
        Assert.assertEquals("", records.get(0).getSecordAxleRecordingTime(), 98333);
        Assert.assertEquals("", records.get(0).getDateNumber(), 1);

        Assert.assertEquals("", records.get(2).getDateNumber(), 2);
    }

}
