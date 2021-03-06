package com.woowacourse.pelotonbackend.rider.domain;

import java.util.List;

import org.springframework.data.jdbc.core.convert.EntityRowMapper;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public class RiderRepositoryImpl implements RiderCustomRepository {
    private final NamedParameterJdbcOperations operations;
    private final EntityRowMapper<Rider> rowMapper;

    public RiderRepositoryImpl(
        final NamedParameterJdbcOperations operations,
        final RelationalMappingContext mappingContext,
        final JdbcConverter jdbcConverter) {
        this.operations = operations;
        this.rowMapper = new EntityRowMapper<>(
            (RelationalPersistentEntity<Rider>)mappingContext.getRequiredPersistentEntity(Rider.class),
            jdbcConverter
        );
    }

    @Override
    public List<Rider> findRidersByRaceId(final Long raceId) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("raceId", raceId);

        return operations.query(findByRiderId(), parameterSource, this.rowMapper);
    }

    private static String findByRiderId() {
        return new StringBuilder()
            .append("SELECT * FROM RIDER ")
            .append("WHERE RIDER.RACE_ID = :raceId")
            .toString();
    }
}
