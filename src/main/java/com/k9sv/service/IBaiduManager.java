package com.k9sv.service;

import com.k9sv.domain.dto.Area;

public interface IBaiduManager extends IBaseManager {

	Area getArea(String ip);
}
