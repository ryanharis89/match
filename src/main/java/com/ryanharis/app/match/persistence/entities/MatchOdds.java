package com.ryanharis.app.match.persistence.entities;

import com.ryanharis.app.match.common.models.enums.Specifier;
import com.ryanharis.app.match.persistence.converter.SpecifierConverter;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MATCH_ODDS")
@Access(value = AccessType.FIELD)
public class MatchOdds implements IDomainObject {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  @JoinColumn(name = "MATCH_ID", nullable = false)
  private Match match;
  @Column(name = "SPECIFIER")
  @Convert(converter = SpecifierConverter.class)
  private Specifier specifier;
  @Column(name = "ODD")
  private BigDecimal odd;
}
