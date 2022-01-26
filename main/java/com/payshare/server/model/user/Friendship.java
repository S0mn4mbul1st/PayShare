package com.payshare.server.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JsonIgnore
	@NonNull
	private Person person1;

	@ManyToOne
	@NonNull
	private Person person2;

	@NonNull
	private boolean confirmed;

	private LocalDateTime date;

	public Friendship(Person issuer, Person receiver, boolean b) {
		// TODO Auto-generated constructor stub
		this.person1 = issuer;
		this.person2 = receiver;
		this.confirmed = b;
	}

	public boolean relatesToSamePeopleAs(Friendship other) {
		return person1.equals(other.person2) && person2.equals(other.person1);
	}

	public Object getPerson1() {
		return this.person1;
	}

	public Object getPerson2() {
		return this.person2;
	}

	public boolean isConfirmed() {
		return this.isConfirmed();
	}

	public Long getId() {
		return this.id;
	}

	public void setConfirmed(boolean b) {
		// TODO Auto-generated method stub
		this.confirmed = b;
	}
}
