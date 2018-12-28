package pl.jointrip.models.viewModels.adminStatistics;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AdminStatisticsViewModel {

    private long activeTripsCount;
    private long activeUsersCount;
    private long blockedTripsCount;
    private long blockedUsersCount;
    private long waitingTripsCount;
    private long waitingUsersCount;

}
