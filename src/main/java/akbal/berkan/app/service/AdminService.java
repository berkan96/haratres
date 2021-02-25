package akbal.berkan.app.service;

import akbal.berkan.app.entity.Admin;
import akbal.berkan.app.repository.IAdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService{
    private final IAdminRepository m_adminRepository;

    public AdminService(IAdminRepository m_adminRepository) {
        this.m_adminRepository = m_adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin)
    {
        return m_adminRepository.save(admin);
    }
}
