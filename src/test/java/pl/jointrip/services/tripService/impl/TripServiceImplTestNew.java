package pl.jointrip.services.tripService.impl;

import org.junit.Before;
import org.mockito.Mock;
import pl.jointrip.dao.CommentsRepository;
import pl.jointrip.dao.TripMemberRepository;
import pl.jointrip.dao.TripRepository;

public class TripServiceImplTestNew {

    @Mock
    TripMemberRepository tripMemberRepository;

    @Mock
    CommentsRepository commentsRepository;

    @Mock
    private TripRepository tripRepository;

    @Before
    public void setUp() throws Exception {
    }
}