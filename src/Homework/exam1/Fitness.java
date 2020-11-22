package Homework.exam1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;


public class Fitness {
    /*Фитнес содержит информацию об абонементах:
     - которые зарегистрированы в данный момент в тренажерном зале;
     - абонементах, которые зарегистрированы в бассейне;
     - абонементах, которые зарегистрированы на групповых занятиях.*/
    protected final Subscription[] gym;
    protected final Subscription[] pool;
    protected final Subscription[] group;
    private final String keyOneTime = "one-time",
                         keyDayPass = "day pass",
                         keyFullTime = "full",
                         keyGym = "gym",
                         keyPool = "pool",
                         keyGroup = "group";
    private int countGym, countPool, countGroup;
    private final LocalTime expirationTimeOfDayPass = LocalTime.of(16, 0);

    public Fitness(int numberOfVisitors) {
        if (numberOfVisitors < 0) throw new IllegalArgumentException("Количество посетителей должно быть больше 0.");
        gym = new Subscription[numberOfVisitors];
        pool = new Subscription[numberOfVisitors];
        group = new Subscription[numberOfVisitors];
    }

    private boolean fitnessIsOpen() {
        LocalTime now = LocalTime.now();
        if (now.isAfter(LocalTime.of(8, 0)) && now.isBefore(LocalTime.of(22, 0)))
            return true;
        else {
            System.out.println("Зал закрыт.");
            close();
            return false;
        }
    }

    public boolean checkOfUse(Subscription verifiedSubscription, Subscription[]... zones) {
        Objects.requireNonNull(verifiedSubscription, "Не задан проверяемый абонемент.");
        for (Subscription[] zone : zones) {
            for (Subscription subscription : zone) {
                if (verifiedSubscription.equals(subscription)) {
                    System.out.println("Абонемент типа \"" + subscription.getType() + "\" клиента " +
                            subscription.getOwner().getOwnerName() + " " + subscription.getOwner().getOwnerSurname() +
                            " уже зарегистрирован.");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean typeCheck(String key, Subscription subscription) {
        Objects.requireNonNull(key, "Не задан тип абонемента для проверки.");
        return key.equalsIgnoreCase(subscription.getType());
    }


    private boolean checkAvailability(Subscription[] subscriptions) {
        for (Subscription subscription : subscriptions) {
            if (subscription == null) return false;
        }
        return true;
    }
    private boolean zoneCheck(String key, String requestedZone) {
        return key.equalsIgnoreCase(requestedZone);
    }

    public void close() {
        Arrays.fill(gym, null);
        Arrays.fill(pool, null);
        Arrays.fill(group, null);
        System.out.println("Фитнес-зал закрыт, группы пусты.");
    }


    public void addSubscriptionToFitness(Subscription subscription, String requestedZone) {
        /*
        one-time : gym + pool         8 - 22.
        day pass : gym + group        8 - 16.
        full time: gym + pool + group 8 - 22.
         */
        LocalTime currentTime = LocalTime.now();
        LocalDate today = LocalDate.now();
        if (!fitnessIsOpen()) return;
        if (checkOfUse(subscription, gym, pool, group)) return;
        if (!subscription.getExpirationDate().isAfter(today)) {
            Logger.subscriptionExpired(subscription);
            return;
        }

        if (typeCheck(keyOneTime, subscription)) {
            if (zoneCheck(keyGym, requestedZone)) {
                if (checkAvailability(gym)) {
                    Logger.noFreePlaces(requestedZone);
                } else {
                    gym[countGym++] = subscription;
                    Logger.printVisitInfo(subscription, requestedZone);
                }
            }
            else if (zoneCheck(keyPool, requestedZone)) {
                if (checkAvailability(pool)) {
                    Logger.noFreePlaces(requestedZone);
                } else {
                    pool[countPool++] = subscription;
                    Logger.printVisitInfo(subscription, requestedZone);
                }
            }
            else Logger.wrongZoneNotification(subscription, requestedZone);
        }


        else if (typeCheck(keyDayPass, subscription)) {
            if (!currentTime.isBefore(expirationTimeOfDayPass)) {
                Logger.wrongVisitTime(subscription);
            }
            else {
                if (zoneCheck(keyGym, requestedZone)) {
                    if (checkAvailability(gym)) {
                        Logger.noFreePlaces(requestedZone);
                    } else {
                        gym[countGym++] = subscription;
                        Logger.printVisitInfo(subscription, requestedZone);
                    }
                }
                else if (zoneCheck(keyGroup, requestedZone)) {
                    if (checkAvailability(group)) {
                        Logger.noFreePlaces(requestedZone);
                    } else {
                        group[countGroup++] = subscription;
                        Logger.printVisitInfo(subscription, requestedZone);
                    }
                }
                else Logger.wrongZoneNotification(subscription, requestedZone);
            }
        }


        else if (typeCheck(keyFullTime, subscription)) {
            if (zoneCheck(keyGym, requestedZone)) {
                if (checkAvailability(gym)) {
                    Logger.noFreePlaces(requestedZone);
                } else {
                    gym[countGym++] = subscription;
                    Logger.printVisitInfo(subscription, requestedZone);
                }
            }
            else if (zoneCheck(keyPool, requestedZone)) {
                if (checkAvailability(pool)) {
                    Logger.noFreePlaces(requestedZone);
                } else {
                    pool[countPool++] = subscription;
                    Logger.printVisitInfo(subscription, requestedZone);
                }
            }
            else if (zoneCheck(keyGroup, requestedZone)) {
                if (checkAvailability(group)) {
                    Logger.noFreePlaces(requestedZone);
                } else {
                    group[countGroup++] = subscription;
                    Logger.printVisitInfo(subscription, requestedZone);
                }
            }
            else Logger.wrongZoneNotification(subscription, requestedZone);
        }


        else System.out.println("Некорректно указан тип абонемента.");
    }

    @Override
    public String toString() {
        return "Fitness{" +
                "gym=" + Arrays.toString(gym) +
                ", pool=" + Arrays.toString(pool) +
                ", group=" + Arrays.toString(group) +
                '}';
    }
}
