package pl.jointrip.services.indexStats.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jointrip.dao.TripRepository;
import pl.jointrip.dao.UserRepository;
import pl.jointrip.models.viewModels.indexStats.IndexStatisticsViewModel;
import pl.jointrip.services.indexStats.IndexStatisticsService;

@Service
public class IndexStatisticsServiceImpl implements IndexStatisticsService{

    @Autowired
    TripRepository tripRepository;
    @Autowired
    UserRepository userRepository;

    public IndexStatisticsViewModel fetchIndexStatisticsViewModel(){
        IndexStatisticsViewModel indexStatisticsViewModel = new IndexStatisticsViewModel();

        indexStatisticsViewModel.setActiveUsersCount(userRepository.countByActive(1));
        indexStatisticsViewModel.setFinishedTripsCount(tripRepository.countByTripStatus(3));
        indexStatisticsViewModel.setDestinationsCount(tripRepository.tripCountryCount());

        return indexStatisticsViewModel;

    }

}
