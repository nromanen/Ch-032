package com.softserveinc.orphanagemenu.service;

import java.util.Date;
import java.util.List;

import com.softserveinc.orphanagemenu.dto.ReportProductQuantitiesDto;

public interface DailyMenuReportService {
	List<ReportProductQuantitiesDto> buildReports(Date date);
}
