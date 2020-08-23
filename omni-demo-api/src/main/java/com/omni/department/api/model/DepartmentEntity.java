package com.omni.department.api.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "department")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class DepartmentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer code;
	
	private String name;
	
	private String local;

	private String city;
	
	@Enumerated(EnumType.STRING)
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateEntity state;

	public DepartmentEntity(Integer code, String name, String local, String city, Board board, StateEntity state) {
		super();
		this.code = code;
		this.name = name;
		this.local = local;
		this.city = city;
		this.board = board;
		this.state = state;
	}

}
