package com.edaisong.entity.req;

public class ModifyVehicleReq {
	private Integer clienterId;
	private String clienterName;
	
	private int vehicleId; 
	private String vehicleName;
	public int getClienterId() {
		return clienterId;
	}

	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}

	public String getClienterName() {
		return clienterName;
	}

	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
}
