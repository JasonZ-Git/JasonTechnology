package org.jason.spider.yeeyi;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jason.spider.yeeyi.dao.RentRecordDO;
import org.jason.spider.yeeyi.dao.YeeyiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author jason.zhang
 * @version 1.0
 */
@Service("yeeyiService")
@Transactional
public class YeeyiServiceImpl /* implements YeeyiService */ {
	
    @Autowired
    private YeeyiDAO yeeyiDAO;

    public List<RentRecordDO> getRentRecords() {
        return this.yeeyiDAO.getRentRecords();
    }
    
    public int saveRentRecords(List<RentRecordDO> rentRecords){
      
      List<RentRecordDO> existingRecords = yeeyiDAO.getExising(rentRecords);
      
      Set<String> existingUrls = existingRecords.stream().map(item -> item.getPageUrl()).collect(Collectors.toSet());
      
      List<RentRecordDO> newRecords = rentRecords.stream().filter(item -> !existingUrls.contains(item.getPageUrl())).collect(Collectors.toList());
      
      this.yeeyiDAO.save(newRecords);
      
      return newRecords.size();
      
    }

}
