package com.chatapp.backend.model;

import com.chatapp.backend.enumeration.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  //  @ElementCollection(targetClass=UserRole.class)
//  @Enumerated(EnumType.STRING)
//  @CollectionTable(name="person_interest")
//  @Column(name="interest")
//  private Collection<UserRole> userRoles;
  @Column(name = "user_role")
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  @Column(name = "locked")
  private Boolean locked;

  @Column(name = "enabled")
  private Boolean enabled;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    AppUser appUser = (AppUser) o;
    return id != null && Objects.equals(id, appUser.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
