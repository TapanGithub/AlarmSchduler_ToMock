package ex.alarm;

import ex.alarm.driver.AlarmAlert;
import ex.os.service.TimeService;
import ex.alarm.ScheduleDay;

public class AlarmScheduler {

	private int day = -1;
	private int minute = -1;
	private AlarmAlert alarmAlert;
	private TimeService timeService;

	public AlarmScheduler(AlarmAlert alarmAlert, TimeService timeService) {
		this.alarmAlert = alarmAlert;
		this.timeService = timeService;
	}
	public void wakeUp() {
		if (isVerifiedSchedule())alarmAlert.startAlarm();
		
	}
	public void addSchedule(int day, int minute) {
		this.day = day;
		this.minute = minute;
	}
	private boolean isVerifiedSchedule() {
		return ((timeService.getCurrentDay() >= ScheduleDay.WEEKSTARTDAY) && (timeService
				.getCurrentDay() < ScheduleDay.WEEKDAY) && (timeService
				.getCurrentDay() <= day)&&(this.minute == timeService.getCurrentMinute()));
	}
}