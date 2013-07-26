package ex.alarm;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ex.alarm.AlarmScheduler;
import ex.os.service.TimeService;
import ex.alarm.driver.*;

public class AlarmSchedulerTest {

	AlarmScheduler alarmScheduler;
	AlarmAlert alarmAlert;
	TimeService timeService;
	//Required Only For Test case Code
	private static final int SUNDAY = 0;
	private static final int MONDAY = 1;
	private static final int TUESDAY = 2;
	private static final int WEDNESDAY = 3;
	private static final int THURSDAY = 4;
	private static final int FRIDAY = 5;
	private static final int SATURDAY = 6;
	private static final int WEEKDAY = 7;
	
	@Before
	public void setUp() throws Exception {
		timeService = mock(TimeService.class);
		alarmAlert = mock(AlarmAlert.class);
		alarmScheduler = new AlarmScheduler(alarmAlert, timeService);
	}

	@After
	public void tearDown() throws Exception {
		timeService = null;
		alarmAlert = null;
		alarmScheduler = null;
	}

	private void thenAlarmShoudAlert() {
		verify(alarmAlert).startAlarm();
	}

	private void whent(int day, int minute) {
		when(timeService.getCurrentDay()).thenReturn(day);
		when(timeService.getCurrentMinute()).thenReturn(minute);
		alarmScheduler.wakeUp();
	}

	private void givenScheduleIsAddedAs(int day, int minute) {
		alarmScheduler.addSchedule(day, minute);
	}

	private void thenAlarmShoudNotAlert() {
		verify(alarmAlert, times(0)).startAlarm();
	}

	@Test
	public void noAlarmIfNoSchedule() {
		alarmScheduler.wakeUp();
		thenAlarmShoudNotAlert();
	}

	@Test
	public void alarmIfScheduled() throws Exception {
		givenScheduleIsAddedAs(MONDAY, 10 * 60);
		whent(MONDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void noAlarmIfScheduledButItsNotTheTimeYet() throws Exception {
		givenScheduleIsAddedAs(MONDAY, 10 * 60);
		whent(MONDAY, 9 * 60);
		thenAlarmShoudNotAlert();
	}

	@Test
	public void alarmIfScheduledAllWorkingDaysAndDayIsAnyDay() throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(MONDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void noAlarmIfScheduledAllWorkingDaysAndDayIsAnyDayAndItsNotTime()
			throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(MONDAY, 9 * 60);
		thenAlarmShoudNotAlert();
	}

	@Test
	public void alarmIfScheduledAllDays() throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(MONDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void alarmIfScheduledAllDaysAndDayIsSunday() throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(SUNDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void alarmIfScheduledAllDaysAndDayIsSaturday() throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(SATURDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void noAlarmIfScheduledAllDaysAndItsNotTime() throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(FRIDAY, 8 * 60);
		thenAlarmShoudNotAlert();
	}

	@Test
	public void alarmIfScheduledAllWorkingDaysAndDayIsMonday() throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(MONDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void alarmIfScheduledAllWorkingDaysAndDayIsTuesday()
			throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(TUESDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void alarmIfScheduledAllWorkingDaysAndDayIsWednesday()
			throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(WEDNESDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void alarmIfScheduledAllWorkingDaysAndDayIsThursday()
			throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(THURSDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void noAlarmIfScheduledAllWorkingDaysAndDayIsThursdayItsNotTime()
			throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(THURSDAY, 9 * 60);
		thenAlarmShoudNotAlert();
	}

	@Test
	public void alarmIfScheduledAllWorkingDaysAndDayIsFriday() throws Exception {
		givenScheduleIsAddedAs(WEEKDAY, 10 * 60);
		whent(FRIDAY, 10 * 60);
		thenAlarmShoudAlert();
	}

	@Test
	public void noalarmIfScheduledeDayAndDayIsdifferent() throws Exception {
		givenScheduleIsAddedAs(MONDAY, 10 * 60);
		whent(TUESDAY, 10 * 60);
		thenAlarmShoudNotAlert();
	}
	
	
	
}
