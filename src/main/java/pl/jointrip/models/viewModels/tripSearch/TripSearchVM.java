package pl.jointrip.models.viewModels.tripSearch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TripSearchVM {

    private String tripTitle;
    private String tripCountry;
    private String tripCity;

}
