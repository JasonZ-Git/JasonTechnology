package org.jason.renting.services;
import java.util.List;
import org.jason.renting.dao.RentRecordDO;
import org.jason.renting.dao.YeeyiDAO;
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
    
    public void saveRentRecords(List<RentRecordDO> rentRecords){
      this.yeeyiDAO.save(rentRecords);
    }

}
