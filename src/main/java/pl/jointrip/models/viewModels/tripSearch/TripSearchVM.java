package pl.jointrip.models.viewModels.tripSearch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class TripSearchVM {

    private String tripTitle;
    private String tripCountry;
    private String tripCity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tripStartDateMin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tripStartDateMax;
    private Integer tripPriceMin;
    private Integer tripPriceMax;


}
