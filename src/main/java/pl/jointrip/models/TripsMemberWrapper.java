package pl.jointrip.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TripsMemberWrapper {

    private List<TripMember> tripMemberList;

    public TripsMemberWrapper(List<TripMember> tripMemberList) {
        this.tripMemberList = tripMemberList;
    }

    public void addTripMember(TripMember tripMember){
        this.tripMemberList.add(tripMember);
    }

}
