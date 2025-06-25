package com.ryanharis.app.match.persistence.entities;

import com.ryanharis.app.match.common.models.enums.Sport;
import com.ryanharis.app.match.persistence.converter.SportConverter;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "MATCH")
@Access(value = AccessType.FIELD)
public class Match implements IDomainObject {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "MATCH_DATE")
  // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private String matchDate;
  @Column(name = "MATCH_TIME")
  // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  private String matchTime;
  @Column(name = "TEAM_A")
  private String teamA;
  @Column(name = "TEAM_B")
  private String teamB;
  @Column(name = "SPORT")
  @Convert(converter = SportConverter.class)
  private Sport sport;
  @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @ToString.Exclude
  private List<MatchOdds> odds;
}