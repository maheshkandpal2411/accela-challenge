package com.accela.challenge.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id; // will be set when persisting

  private @EqualsAndHashCode.Include String firstName;
  private @EqualsAndHashCode.Include String lastName;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @JoinTable(name="person_addresses",
    joinColumns = @JoinColumn(name = "fk_person", foreignKey = @ForeignKey(
      name = "fk_person_addresses_person")),
    inverseJoinColumns = @JoinColumn(name = "fk_address",
      foreignKey = @ForeignKey(name = "fk_person_addresses_address")),
    uniqueConstraints = @UniqueConstraint(name = "uk_person_addresses_address",
      columnNames = { "fk_address" }))
  private  List<Address> addresses;
}
