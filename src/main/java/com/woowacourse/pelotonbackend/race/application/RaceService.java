package com.woowacourse.pelotonbackend.race.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woowacourse.pelotonbackend.common.exception.RaceNotFoundException;
import com.woowacourse.pelotonbackend.race.domain.Race;
import com.woowacourse.pelotonbackend.race.domain.RaceCategory;
import com.woowacourse.pelotonbackend.race.domain.RaceRepository;
import com.woowacourse.pelotonbackend.race.presentation.dto.RaceCreateRequest;
import com.woowacourse.pelotonbackend.race.presentation.dto.RaceRetrieveResponse;
import com.woowacourse.pelotonbackend.race.presentation.dto.RaceUpdateRequest;
import com.woowacourse.pelotonbackend.support.RandomGenerator;
import com.woowacourse.pelotonbackend.vo.ImageUrl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class RaceService {
    private final RaceRepository raceRepository;
    private final RandomGenerator randomGenerator;

    public Long create(final RaceCreateRequest request) {
        final RaceCategory category = request.getCategory();
        final ImageUrl randomCertification = category.getRandomCertification(randomGenerator);
        final ImageUrl randomThumbnail = category.getRandomThumbnail(randomGenerator);
        final Race savedRace = raceRepository.save(request.toEntity(randomCertification, randomThumbnail));

        return savedRace.getId();
    }

    @Transactional(readOnly = true)
    public RaceRetrieveResponse retrieve(final Long raceId) {
        final Race race = raceRepository.findById(raceId)
            .orElseThrow(() -> new RaceNotFoundException(raceId));

        return RaceRetrieveResponse.of(race);
    }

    public void update(final Long raceId, final RaceUpdateRequest request) {
        final Race race = raceRepository.findById(raceId)
            .orElseThrow(() -> new RaceNotFoundException(raceId));

        final Race raceUpdated = request.toEntity(race);
        raceRepository.save(raceUpdated);
    }

    public void delete(final Long raceId) {
        raceRepository.deleteById(raceId);
    }
}
