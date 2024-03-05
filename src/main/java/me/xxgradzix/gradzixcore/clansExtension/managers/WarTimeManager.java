package me.xxgradzix.gradzixcore.clansExtension.managers;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarScheduleEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.WarScheduleEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WarTimeManager {

    private static final JavaPlugin plugin = Gradzix_Core.getInstance();

    private final WarScheduleEntityManager warScheduleEntityManager;
    private final WarManager warManager;

    public WarTimeManager(WarScheduleEntityManager warScheduleEntityManager, WarManager warManager) {
        this.warScheduleEntityManager = warScheduleEntityManager;
        this.warManager = warManager;
    }

    public void scheduleWarStart(LocalDateTime warStart, LocalDateTime warEnd) throws IllegalArgumentException {

        assertDates(warStart, warEnd);

        Calendar calendar = createCalendar(warStart);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        long delay = calendar.getTimeInMillis() - System.currentTimeMillis();

        Bukkit.broadcastMessage("Zacznie sie za " + delay / 1000 + " sekund");

        executor.schedule(new WarStartTask(warStart, warEnd), delay, TimeUnit.MILLISECONDS);
    }

    private void scheduleWarEnd(LocalDateTime warStart, LocalDateTime warEnd) throws IllegalArgumentException {

        assertDates(warStart, warEnd);

        Calendar calendar = createCalendar(warEnd);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        long delay = calendar.getTimeInMillis() - System.currentTimeMillis();

        Bukkit.broadcastMessage("Zakonczy sie za " + delay / 1000 + " sekund");


        executor.schedule(new WarEndTask(), delay, TimeUnit.MILLISECONDS);
    }

    public void scheduleOnEnable() {

        Optional<WarScheduleEntity> warScheduleEntityOptional = warScheduleEntityManager.getWarScheduleEntity();

        if(!warScheduleEntityOptional.isPresent()) return;

        WarScheduleEntity warScheduleEntity = warScheduleEntityOptional.get();

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime warStart = warScheduleEntity.getWarStart();
        LocalDateTime warEnd = warScheduleEntity.getWarEnd();

        if(warStart.isAfter(warEnd)){
            warScheduleEntityManager.deleteWarSchedules();
            return;
        }

        if(now.isAfter(warEnd)) {
            warScheduleEntityManager.deleteWarSchedules();
            return;
        }

        if(now.isAfter(warStart) && now.isBefore(warEnd)) {
            Bukkit.broadcastMessage(Messages.WARS_ARE_ACTIVE);
            ClansExtension.ARE_WARS_ACTIVE = true;

            scheduleWarEnd(warStart, warEnd);
            return;
        }

        if(now.isBefore(warStart)) {
            scheduleWarStart(warStart, warEnd);
            return;
        }



    }

    class WarStartTask implements Runnable {
        private final LocalDateTime warStart;
        private final LocalDateTime warEnd;
        public WarStartTask(LocalDateTime warStart, LocalDateTime warEnd) {
            this.warStart = warStart;
            this.warEnd = warEnd;
        }
        @Override
        public void run() {
            Bukkit.broadcastMessage("Start run");
            Bukkit.broadcastMessage(Messages.WARS_ARE_ACTIVE);
            ClansExtension.ARE_WARS_ACTIVE = true;
            scheduleWarEnd(warStart, warEnd);
            warManager.startWars();
        }
    }

    class WarEndTask implements Runnable {
//        private final LocalDateTime warStart;
//        private final LocalDateTime warEnd;
//
//        public WarEndTask(LocalDateTime warStart, LocalDateTime warEnd) {
//            this.warStart = warStart;
//            this.warEnd = warEnd;
//        }
        @Override
        public void run() {
            Bukkit.broadcastMessage("End run");
            Bukkit.broadcastMessage(Messages.WARS_ENDED);
            ClansExtension.ARE_WARS_ACTIVE = false;
            warManager.endWars();
        }
    }

    private Calendar createCalendar(LocalDateTime dateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, dateTime.getYear());
        calendar.set(Calendar.MONTH, dateTime.getMonthValue() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dateTime.getDayOfMonth());
        calendar.set(Calendar.HOUR_OF_DAY, dateTime.getHour());
        calendar.set(Calendar.MINUTE, dateTime.getMinute());
        return calendar;
    }

    private void assertDates(LocalDateTime warStart, LocalDateTime warEnd) {
        if(warStart.isAfter(warEnd)) throw new IllegalArgumentException("War start date cannot be after war end date");
        LocalDateTime now = LocalDateTime.now();
        if(warEnd.isBefore(now)) throw new IllegalArgumentException("War end date cannot be before current date");
    }

}
