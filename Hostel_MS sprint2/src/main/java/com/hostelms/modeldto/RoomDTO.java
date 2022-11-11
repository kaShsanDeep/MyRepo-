package com.hostelms.modeldto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

	@NotNull(message = "Room ID not null!!!")
	private int roomId;
	@NotNull(message = "Room Name not null!!!")
	private String roomName;
	@NotNull(message = "Room Type not null!!!")
	private String roomType;

}
