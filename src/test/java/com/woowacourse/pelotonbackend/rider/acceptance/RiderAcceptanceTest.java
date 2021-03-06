package com.woowacourse.pelotonbackend.rider.acceptance;

import static com.woowacourse.pelotonbackend.rider.domain.RiderFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.woowacourse.pelotonbackend.member.domain.MemberFixture;
import com.woowacourse.pelotonbackend.rider.domain.RiderFixture;
import com.woowacourse.pelotonbackend.rider.presentation.RiderResponse;
import com.woowacourse.pelotonbackend.rider.presentation.dto.RiderCreateRequest;
import com.woowacourse.pelotonbackend.support.AcceptanceTest;
import com.woowacourse.pelotonbackend.support.dto.JwtTokenResponse;

public class RiderAcceptanceTest extends AcceptanceTest {
    /*
     * Feature: Rider 관리
     *
     * Scenario: Rider를 관리한다.
     * Given: Member가 생성이 되어있다.(해당 멤버의 토큰이 발급되어 있다.)
     *        Race가 생성이 되어있다.
     * When: Member가 Race에 입장을 요청한다.(Rider 생성 요청)
     * Then: 새로운 Rider가 생성된다.
     *
     * Given: Rider가 존재한다.
     * When: 라이더를 조회한다.
     * then: 라이더를 반환한다.
     *
     * Given: Race에 참여중인 멤버들이 존재한다.
     * When: Race에 참여중인 Rider들을 조회한다.
     * then: 참여중인 Rider들이 반환된다.
     *
     */
    @DisplayName("Rider 관리 기능")
    @Test
    void manageRider() {
        final JwtTokenResponse tokenResponse = loginMember(
            MemberFixture.createRequest(MemberFixture.KAKAO_ID, MemberFixture.EMAIL, MemberFixture.NAME));
        final String resource = fetchCreate(tokenResponse);
        fetchFind(resource, tokenResponse);

        fetchCreateMembers(tokenResponse);
        fetchFindRidersByRaceId(TEST_RACE_ID, tokenResponse);
    }

    private void fetchCreateMembers(final JwtTokenResponse tokenResponse) {
        for (int i = 0; i < RIDER_NUMBER; i++) {
            fetchCreate(tokenResponse);
        }
    }

    private void fetchFindRidersByRaceId(final Long raceId, final JwtTokenResponse tokenResponse) {
        final List<RiderResponse> riders = given()
            .header(createTokenHeader(tokenResponse))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/api/riders/races/" + raceId)
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .body().jsonPath().getList("riders", RiderResponse.class);

        assertThat(riders.size()).isEqualTo(RIDER_NUMBER + 1);
    }

    private RiderResponse fetchFind(final String resource, JwtTokenResponse tokenResponse) {
        return given()
            .header(createTokenHeader(tokenResponse))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(resource)
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(RiderResponse.class);
    }

    private String fetchCreate(JwtTokenResponse tokenResponse) {
        final RiderCreateRequest riderCreateRequest = RiderFixture.createMockRequest();

        return given()
            .header(createTokenHeader(tokenResponse))
            .body(riderCreateRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/api/riders")
            .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .header("Location");
    }
}
