package com.splitthebill.server.model.user;

import com.splitthebill.server.dto.BasicUserAccountCreateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class BasicUserAccount extends UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO isConfirmed? -> createPerson()

    @Column(unique = true)
    private String username;

    private String password;

    public BasicUserAccount(BasicUserAccountCreateDto accountCreateDto) {
        this.username = accountCreateDto.username;
        this.password = accountCreateDto.password;
        this.email = accountCreateDto.email;
    }

    @Builder
    public BasicUserAccount(Long id, String email, LocalDateTime created, Person person,
                            List<UserAccountNotification> userAccountNotifications,
                            String username, String password){
        setEmail(email);
        setPerson(person);
        setUserAccountNotifications(userAccountNotifications);
        setCreated(created);
        setId(id);
        setUsername(username);
        setPassword(password);
    }

	private void setPassword(String password2) {
		this.password = password2;
	}

	private void setUsername(String username2) {
		this.username = username2;
	}

	private void setId(Long id2) {
		this.id = id2;
	}

	private void setCreated(LocalDateTime created) {
		this.created = created;
		
	}

	private void setUserAccountNotifications(List<UserAccountNotification> userAccountNotifications) {
		this.userAccountNotifications = userAccountNotifications;
		
	}

	private void setPerson(Person person) {
		this.person = person;
		
	}

	private void setEmail(String email) {
		this.email = email;
		
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
}
