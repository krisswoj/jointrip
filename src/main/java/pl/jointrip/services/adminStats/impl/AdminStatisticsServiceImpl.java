package pl.jointrip.services.adminStats.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.viewModels.adminStatistics.AdminStatisticsViewModel;
import pl.jointrip.services.adminStats.AdminStatisticsService;

@Service
public class AdminStatisticsServiceImpl implements AdminStatisticsService{
    @Autowired
    TripRepository tripRepository;
    @Autowired
    UserRepository userRepository;

    public AdminStatisticsViewModel fetchStatisticsViewModel(){
        AdminStatisticsViewModel viewModel = new AdminStatisticsViewModel();
        viewModel.setActiveTripsCount(tripRepository.countByTripStatus(1));
        viewModel.setBlockedTripsCount(tripRepository.countByTripStatus(2));
        viewModel.setWaitingTripsCount(tripRepository.countByTripStatus(0));
        viewModel.setActiveUsersCount(userRepository.countByActive(1));
        viewModel.setBlockedUsersCount(userRepository.countByActive(2));
        viewModel.setWaitingUsersCount(userRepository.countByActive(0));

        return viewModel;
    }
}
