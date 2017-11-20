package com.k9sv.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.k9sv.domain.pojo.Company;
import com.k9sv.service.ICompanyManager;
import com.k9sv.service.IUserManager;

@Component
public class CompanyCache {

	
	public static Set<Company> PetShop = new HashSet<Company> ();
	
	public static Set<Company> PetFactory = new HashSet<Company>();
	
	@Autowired
	private ICompanyManager companyManager;
	
	@Autowired
	IUserManager userManager;
	
	@Scheduled(fixedRate = 1000 * 60 * 60 * 5)
	public void scheduleRun() {
		List<Company> _list = companyManager.find();
		for(Company company : _list){
			deleteCompanyCache(company);
			if(company.getType() == 1){
				PetFactory.add(company);
			}else{
				PetShop.add(company);
			}
		}
	}
	public static void updateCompanyCache(Company company){
		deleteCompanyCache(company);
		if(company.getType() == 1){
			PetFactory.add(company);
		}else{
			PetShop.add(company);
		}
	}
	
	public static Set<Company> getConpanys(int type){
		if(type == 1){
			return PetFactory;
		}else{
			return PetShop;
		}
	}
	private static void deleteCompanyCache(Company company){
		if(PetFactory.contains(company)){
			PetFactory.remove(company);
		}
		if(PetShop.contains(company)){
			PetShop.remove(company);
		}
	}
	
}
