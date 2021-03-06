package com.woowacourse.pelotonbackend.member.domain;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Embedded;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.woowacourse.pelotonbackend.vo.Cash;
import com.woowacourse.pelotonbackend.vo.ImageUrl;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.With;

@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@Getter
public class Member {
    @Id @With(value = AccessLevel.PACKAGE)
    private final Long id;

    @NotNull
    private final Long kakaoId;

    @Embedded(prefix = "PROFILE_", onEmpty = Embedded.OnEmpty.USE_EMPTY)
    @Valid @JsonProperty("profile")
    private final ImageUrl profile;

    @NotBlank
    private final String name;

    @NotBlank @Email
    private final String email;

    @Embedded.Empty @Valid
    private final Cash cash;

    @NotNull
    private final Role role;

    @CreatedDate @PastOrPresent
    @With(AccessLevel.PACKAGE)
    private final LocalDateTime createdAt;

    @LastModifiedDate @PastOrPresent
    @With(AccessLevel.PACKAGE)
    private final LocalDateTime updatedAt;

    public Member update(final String name) {
        return this.toBuilder()
            .name(name)
            .build();
    }

    public Member update(final Cash cash) {
        return this.toBuilder()
            .cash(cash)
            .build();
    }
}
