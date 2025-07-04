/*
 * Copyright(C) 2007-2013 National Institute of Informatics, All rights reserved.
 */
package app.cancel; // キャンセル機能用の新しいパッケージ

import app.AppException;

/**
 * 予約キャンセル用のフォームクラス
 * Form class for Cancel Reservation
 *
 */
public class CancelReservationForm {

	// 対応するControlクラスのインスタンスを保持
	private CancelReservationControl cancelReservationControl = new CancelReservationControl();

	// CUIなどから渡される予約番号を保持
	private String reservationNumber;

	/**
	 * キャンセル処理を実行する
	 * @throws AppException キャンセル処理中にエラーが発生した場合
	 */
	public void submitCancellation() throws AppException {
		// 保持しているControlオブジェクトを取得する
		CancelReservationControl control = getCancelReservationControl();
		// ControlオブジェクトのcancelReservationメソッドを、このフォームが持つreservationNumberを引数にして呼び出す
		control.cancelReservation(reservationNumber);
	}

	// Controlオブジェクトを取得するためのプライベートメソッド
	private CancelReservationControl getCancelReservationControl() {
		return cancelReservationControl;
	}

	// reservationNumberのゲッター
	public String getReservationNumber() {
		return reservationNumber;
	}

	// reservationNumberのセッター
	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

}
