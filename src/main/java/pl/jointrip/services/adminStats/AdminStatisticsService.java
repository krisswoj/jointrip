package pl.jointrip.services.adminStats;

import pl.jointrip.models.viewModels.adminStatistics.AdminStatisticsViewModel;

public interface AdminStatisticsService {
    AdminStatisticsViewModel fetchStatisticsViewModel();
}
