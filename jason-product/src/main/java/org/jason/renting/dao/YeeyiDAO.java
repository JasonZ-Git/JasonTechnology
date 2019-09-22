package org.jason.renting.dao;

import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class YeeyiDAO extends AbstractDao {

  public List<RentRecordDO> getRentRecords() {
    return getSession().createCriteria(RentRecordDO.class).list();
  }

  public void save(List<RentRecordDO> rentRecords) {
    
    for (RentRecordDO current : rentRecords) {
      getSession().saveOrUpdate(current);
    }
    
    rentRecords.stream().forEach(getSession()::persist);
  }
  
  public List<RentRecordDO> getExising(List<RentRecordDO> records) {
    Criteria cr =  getSession().createCriteria(RentRecordDO.class);
    
    List<String>pageUrls = records.stream().map(item -> item.getPageUrl()).collect(Collectors.toList());
    
    cr.add(Restrictions.in("pageUrl", pageUrls));
    
    return cr.list();
    
  }

}
