package com.hostelms.model;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {


	@Column(length = 20)
	private String roomName;
	@Column(length = 20)
	private String roomType;
	@Id
	private int roomId;

}
