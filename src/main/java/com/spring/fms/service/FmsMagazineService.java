package com.spring.fms.service;

import java.util.List;

import com.spring.fms.model.Magazine;

public interface FmsMagazineService {
	List<Magazine> findAllMagazine();
	Magazine findMagazineById(long id);
	Magazine saveMagazine(Magazine magazine);

}
