package io.github.zhyshko.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Table(
//        uniqueConstraints=
//        @UniqueConstraint(columnNames={"external_id"})
//)
public class Base {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique=true, nullable = false)
    @EqualsAndHashCode.Include
    //@JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID externalId;
}
