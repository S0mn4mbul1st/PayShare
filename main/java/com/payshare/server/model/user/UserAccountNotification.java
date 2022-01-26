package com.payshare.server.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class UserAccountNotification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean isReviewed = false;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@NonNull
	private Notification notification;

	@ManyToOne
	@NonNull
	private UserAccount userAccount;

	public void markReviewed() {
		this.isReviewed = true;
	}

	public Object getUserAccount() {
		// TODO Auto-generated method stub
		return this.userAccount;
	}

	public Object getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public Object getNotification() {
		// TODO Auto-generated method stub
		return this.notification;
	}

	public Object isReviewed() {
		// TODO Auto-generated method stub
		return this.isReviewed();
	}
}
