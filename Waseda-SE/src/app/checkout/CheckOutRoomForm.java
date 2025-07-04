/*
 * Copyright(C) 2007-2013 National Institute of Informatics, All rights reserved.
 */
package app.checkout;

import app.AppException;

/**
 * Form class for Check-out Customer
 * 
 */
public class CheckOutRoomForm {

	private CheckOutRoomControl checkOutRoomControl = new CheckOutRoomControl();

	private CheckOutRoomControl getCheckOutRoomControl() {
		return checkOutRoomControl;
	}

	private String roomNumber;

	public void checkOut() throws AppException {
		// 保持しているControlオブジェクトを取得する
  		CheckOutRoomControl checkOutRoomControl = getCheckOutRoomControl();
  		// ControlオブジェクトのcheckOutメソッドを、このフォームが持つroomNumberを引数にして呼び出す
  		checkOutRoomControl.checkOut(roomNumber);
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

}
