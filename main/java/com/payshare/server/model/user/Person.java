package com.payshare.server.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.payshare.server.model.Currency;
import com.payshare.server.model.expense.GroupExpense;
import com.payshare.server.model.expense.OwnExpense;
import com.payshare.server.model.expense.PersonGroupExpense;
import com.payshare.server.model.expense.scheduled.group.ScheduledPersonGroupExpense;
import com.payshare.server.model.expense.scheduled.own.ScheduledOwnExpense;
import lombok.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
public class Person extends RepresentationModel<Person> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private UserAccount userAccount;

    @OneToMany(mappedBy = "person1")
    private List<Friendship> friendships = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<PersonGroup> personGroups = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<OwnExpense> ownExpenses = new ArrayList<>();

    @OneToMany(mappedBy = "creditor")
    private List<GroupExpense> groupExpenses = new ArrayList<>();

    @OneToMany(mappedBy = "debtor")
    private List<PersonGroupExpense> personGroupExpenses = new ArrayList<>();

    @OneToMany(mappedBy = "debtor")
    private List<ScheduledPersonGroupExpense> scheduledPersonGroupExpenses = new ArrayList<>();

    @OneToMany(mappedBy = "person")
    private List<ScheduledOwnExpense> scheduledOwnExpenses = new ArrayList<>();

    @ManyToOne
    private Currency preferredCurrency;

    @ElementCollection
    @CollectionTable(name = "person_balance")
    @MapKeyJoinColumn(name = "currency_id")
    @Column(name = "balance")
    private Map<Currency, BigDecimal> balances = new HashMap<>();

    public Person(UserAccount userAccount, String name) {
        this.userAccount = userAccount;
        this.name = name;
    }

    public boolean isMemberOfGroup(Long groupId) {
        return getPersonGroups().stream().anyMatch(x -> x.getGroup().getId().equals(groupId));
    }

    public Collection<PersonGroup> getPersonGroups() {
        return this.personGroups;
    }

    public void addOwnExpense(OwnExpense ownExpense) {
        ownExpenses.add(ownExpense);
    }

    public Map<Currency, BigDecimal> getBalancesM() {
        Map<Currency, BigDecimal> balanceMap = new HashMap<>();
        balanceMap.put(preferredCurrency, BigDecimal.ZERO);
        return personGroups.stream()
                .map(getBalancesM())
                .reduce(balanceMap, (all, current) -> {
                    current.forEach(
                            (currency, balance) -> all.put(currency, all.getOrDefault(currency, BigDecimal.ZERO)
                                    .add(current.get(currency))));
                    return all;
                });
    }

    public Currency getPreferredCurrency() {
        return this.preferredCurrency;
    }

    public String getName() {
        return this.name;
    }

    public Object getOwnExpenses() {
        // TODO Auto-generated method stub
        return this.ownExpenses;
    }

    public Long getId() {
        // TODO Auto-generated method stub
        return this.id;
    }

    public List<Friendship> getFriendships() {
        // TODO Auto-generated method stub
        return this.friendships;
    }

    public UserAccount getUserAccount() {
        // TODO Auto-generated method stub
        return this.userAccount;
    }

    public void setPreferredCurrency(Currency orElseThrow) {
        // TODO Auto-generated method stub
        this.preferredCurrency = orElseThrow;
    }

    public void setName(String name2) {
        // TODO Auto-generated method stub
        this.name = name2;
    }

    public Object getBalances() {
        // TODO Auto-generated method stub
        return this.balances;
    }
}
