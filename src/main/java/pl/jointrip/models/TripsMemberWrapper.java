package pl.jointrip.models;

import java.util.List;

public class TripsMemberWrapper {

    private List<TripMember> tripMemberList;

    public TripsMemberWrapper(List<TripMember> tripMemberList) {
        this.tripMemberList = tripMemberList;
    }

    public void addTripMember(TripMember tripMember){
        this.tripMemberList.add(tripMember);
    }

    public List<TripMember> getTripMemberList() {
        return tripMemberList;
    }

    public void setTripMemberList(List<TripMember> tripMemberList) {
        this.tripMemberList = tripMemberList;
    }
}
