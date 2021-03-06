package com.woowacourse.pelotonbackend.rider.application;

import java.util.List;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woowacourse.pelotonbackend.common.exception.RiderNotFoundException;
import com.woowacourse.pelotonbackend.member.domain.Member;
import com.woowacourse.pelotonbackend.member.presentation.dto.MemberResponse;
import com.woowacourse.pelotonbackend.rider.domain.Rider;
import com.woowacourse.pelotonbackend.rider.domain.RiderRepository;
import com.woowacourse.pelotonbackend.rider.presentation.RiderResponse;
import com.woowacourse.pelotonbackend.rider.presentation.dto.RiderCreateRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class RiderService {
    private final RiderRepository riderRepository;

    public Long create(final MemberResponse member, final RiderCreateRequest riderCreateRequest) {
        final Rider riderWithoutId = Rider.builder()
            .memberId(AggregateReference.to(member.getId()))
            .raceId(AggregateReference.to(riderCreateRequest.getRaceId()))
            .build();

        return riderRepository.save(riderWithoutId).getId();
    }

    @Transactional(readOnly = true)
    public RiderResponse retrieve(final Long riderId) {
        final Rider rider = riderRepository.findById(riderId)
            .orElseThrow(() -> new RiderNotFoundException(riderId));

        return RiderResponse.of(rider);
    }

    @Transactional(readOnly = true)
    public List<RiderResponse> retrieveByRaceId(final Long raceId) {
        final List<Rider> riders = riderRepository.findRidersByRaceId(raceId);

        return RiderResponse.listOf(riders);
    }
}
