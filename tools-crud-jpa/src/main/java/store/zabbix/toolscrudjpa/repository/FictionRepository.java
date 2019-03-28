package store.zabbix.toolscrudjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.zabbix.common.jpa.entity.Fiction;

@Repository
public interface FictionRepository extends JpaRepository<Fiction,Integer> {

}
