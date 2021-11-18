package com.accela.challenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Address implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id; // will be set when persisting

  private String street;
  private String city;
  private String state;
  private String postalCode;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  private Person person;
}
