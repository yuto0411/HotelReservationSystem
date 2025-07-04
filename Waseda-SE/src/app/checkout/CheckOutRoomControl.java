/*
 * Copyright(C) 2007-2013 National Institute of Informatics, All rights reserved.
 */
package app.checkout;

import java.util.Date;

import app.AppException;
import app.ManagerFactory;
import domain.payment.PaymentManager;
import domain.payment.PaymentException;
import domain.room.RoomManager;
import domain.room.RoomException;

/**
 * Control class for Check-out Customer
 *
 */
public class CheckOutRoomControl {

	public void checkOut(String roomNumber) throws AppException {
		try {
			//Clear room
   			// RoomManagerを取得し、部屋を空室に戻す
   			// この時、部屋に記録されていた宿泊日が返される
   			RoomManager roomManager = getRoomManager();
   			Date stayingDate = roomManager.removeCustomer(roomNumber);

   			//Consume payment
   			// PaymentManagerを取得し、取得した宿泊日と部屋番号を使って支払いを完了済みにする
   			PaymentManager paymentManager = getPaymentManager();
  			paymentManager.consumePayment(stayingDate, roomNumber);
		}
		catch (RoomException e) {
			AppException exception = new AppException("Failed to check-out", e);
			exception.getDetailMessages().add(e.getMessage());
			exception.getDetailMessages().addAll(e.getDetailMessages());
			throw exception;
		}
		catch (PaymentException e) {
			AppException exception = new AppException("Failed to check-out", e);
			exception.getDetailMessages().add(e.getMessage());
			exception.getDetailMessages().addAll(e.getDetailMessages());
			throw exception;
		}
	}

	private RoomManager getRoomManager() {
		return ManagerFactory.getInstance().getRoomManager();
	}

	private PaymentManager getPaymentManager() {
		return ManagerFactory.getInstance().getPaymentManager();
	}
}
