package com.woowacourse.pelotonbackend.rider.presentation;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woowacourse.pelotonbackend.member.presentation.dto.MemberResponse;
import com.woowacourse.pelotonbackend.rider.application.RiderService;
import com.woowacourse.pelotonbackend.rider.presentation.dto.RiderCreateRequest;
import com.woowacourse.pelotonbackend.support.annotation.LoginMember;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/riders")
@RequiredArgsConstructor
public class RiderController {
    private final RiderService riderService;

    @PostMapping
    public ResponseEntity<Void> create(@LoginMember MemberResponse member,
        @Valid @RequestBody final RiderCreateRequest riderCreateRequest) {

        return ResponseEntity
            .created(URI.create("/api/riders/" + riderService.create(member, riderCreateRequest)))
            .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiderResponse> find(@PathVariable final Long id) {
        final RiderResponse response = riderService.retrieve(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/races/{raceId}")
    public ResponseEntity<List<RiderResponse>> findRidersByRaceId(@PathVariable final Long raceId) {
        final List<RiderResponse> riders = riderService.retrieveByRaceId(raceId);

        return ResponseEntity.ok(riders);
    }
}
