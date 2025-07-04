/*
 * Copyright(C) 2007-2013 National Institute of Informatics, All rights reserved.
 */
package app.cancel; // キャンセル機能用の新しいパッケージ

import java.util.Date;

import app.AppException;
import app.ManagerFactory;
import domain.reservation.ReservationManager;
import domain.reservation.ReservationException;
import domain.room.RoomManager;
import domain.room.RoomException;

public class CancelReservationControl {


    public void cancelReservation(String reservationNumber) throws AppException {
        // キャンセルのため、空室数の変化量は+1となる
        int availableQtyOfChange = 1;

        try {
            // 1. 予約を検索し、ステータスを更新する
            // ここでは、既存のconsumeReservationメソッドを利用して予約を使用済みにし、
            // 同時に次のステップで必要な宿泊日を取得する
            ReservationManager reservationManager = getReservationManager();
            Date stayingDate = reservationManager.consumeReservation(reservationNumber);

            // 2. 空室在庫を1つ増やす
            RoomManager roomManager = getRoomManager();
            roomManager.updateRoomAvailableQty(stayingDate, availableQtyOfChange);
        }
        catch (ReservationException e) {
            // 予約が見つからない、または既に処理済みの場合に発生する可能性がある
            AppException exception = new AppException("予約のキャンセルに失敗しました", e);
            exception.getDetailMessages().add(e.getMessage());
            exception.getDetailMessages().addAll(e.getDetailMessages());
            throw exception;
        }
        catch (RoomException e) {
            // 在庫の更新に失敗した場合に発生する可能性がある
            AppException exception = new AppException("予約のキャンセルに失敗しました", e);
            exception.getDetailMessages().add(e.getMessage());
            exception.getDetailMessages().addAll(e.getDetailMessages());
            throw exception;
        }
    }

    private RoomManager getRoomManager() {
        return ManagerFactory.getInstance().getRoomManager();
    }

    private ReservationManager getReservationManager() {
        return ManagerFactory.getInstance().getReservationManager();
    }
}
