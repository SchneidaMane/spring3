package main.java.core.persistence.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MACHINE")
public class Machine {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "machine")
	private String machine;

}
