package Homework.Exam1;

import java.time.LocalTime;
import java.util.Arrays;

public class Logger {
    public static void printVisitInfo(Subscription subscription, String requestedZone) {
        System.out.println("Абонемент типа \"" + subscription.getType() + "\" клиента " +
                subscription.getOwner().getOwnerName() + " " + subscription.getOwner().getOwnerSurname() +
                " отмечен в зоне " + requestedZone + " в " + LocalTime.now().withSecond(0).withNano(0));
    }

    public static void wrongZoneNotification(Subscription subscription, String requestedZone) {
        System.out.println("Абонемент типа \"" + subscription.getType() + "\" клиента " +
                subscription.getOwner().getOwnerName() + " " + subscription.getOwner().getOwnerSurname() +
                " не может быть использован в зоне " + requestedZone);
    }

    public static void subscriptionExpired(Subscription subscription) {
        System.out.println("Срок действия абонемента клиента " +
                subscription.getOwner().getOwnerName() + " " + subscription.getOwner().getOwnerSurname() +
                " истек");
    }

    public static void wrongVisitTime(Subscription subscription) {
        LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);
        System.out.println("Абонемент типа \"" + subscription.getType() + "\" клиента " +
                subscription.getOwner().getOwnerName() + " " + subscription.getOwner().getOwnerSurname() +
                " может быть использован только с 8:00 до 16:00. Текущее время: " +
                currentTime);
    }

    public static void noFreePlaces(String requestedZone) {
        System.out.println("В зоне " + requestedZone + " нет свободных мест.");
    }

//    public static void printGroupInfo(Subscription[]... zones) {
//        for (Subscription[] zone : zones) {
//            System.out.println("Fitness{" +
//                        Arrays.toString(zone));
//        }
//    }


}
