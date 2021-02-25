package akbal.berkan.app.repository;

import akbal.berkan.app.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {
}
