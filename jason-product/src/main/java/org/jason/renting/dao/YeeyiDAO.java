package org.jason.renting.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class YeeyiDAO extends AbstractDao {

  public List<RentRecordDO> getRentRecords() {
    return getSession().createCriteria(RentRecordDO.class).list();
  }

  public void save(List<RentRecordDO> rentRecords) {
    rentRecords.stream().forEach(getSession()::persist);
  }

}
