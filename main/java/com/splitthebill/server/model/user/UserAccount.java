package com.splitthebill.server.model.user;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"person"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class UserAccount extends RepresentationModel<UserAccount> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String email;

    @CreationTimestamp
    LocalDateTime created;

    @OneToOne(cascade=CascadeType.ALL)
    Person person;

    @JsonManagedReference
    @OneToMany(mappedBy = "userAccount")
    List<UserAccountNotification> userAccountNotifications;

	public Person getPerson() {
		// TODO Auto-generated method stub
		return this.person;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public void setPerson(Person person2) {
		// TODO Auto-generated method stub
		this.person = person2;
	}
}
