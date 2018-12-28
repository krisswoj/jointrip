package pl.jointrip.models.viewModels.indexStats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IndexStatisticsViewModel {

    private long destinationsCount;
    private long finishedTripsCount;
    private long activeUsersCount;

}
