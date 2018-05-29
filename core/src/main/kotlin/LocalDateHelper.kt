import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

/**
 * @Author: zhengye.zhang
 * @Description:
 * @Date: 2018/5/29 下午5:01
 */
object LocalDateHelper{

    private val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

    private val DATE_PATTERN = "yyyy-MM-dd"

    private val DATE_WITHOUT_SEPARATOR_PATTERN = "yyyyMMdd"


    /**
     * 获取当前时间
     *
     * @return
     */
    fun now(): Date {
        return localDateTime2Date(LocalDateTime.now())
    }

    /**
     * 获取1970-01-01 00:00:00的日期
     *
     * @return
     */
    fun ofDefault(): Date {
        return of(1970, 1, 1)
    }


    /**
     * 根据年月日 时分秒 构建日期
     *
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    fun of(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int, second: Int): Date {
        return localDateTime2Date(LocalDateTime.of(year, month, dayOfMonth, hour, minute, second))
    }


    /**
     * 根据年月日 构建日期
     *
     * @param year
     * @param month
     * @param dayOfMonth
     * @return
     */
    fun of(year: Int, month: Int, dayOfMonth: Int): Date {
        return localDate2Date(LocalDate.of(year, month, dayOfMonth))
    }

    /**
     * 获取给定日期当月的最后一天
     *
     * @param localDate
     * @return
     */
    private fun getLastDayOfMonth(localDate: LocalDate): LocalDate {
        return localDate.with(TemporalAdjusters.lastDayOfMonth())
    }

    /**
     * 获取给定日期当月的最后一天
     *
     * @param date
     * @return
     */
    fun getLastDayOfMonth(date: Date): Date {
        return localDate2Date(getLastDayOfMonth(date2localDate(date)))
    }

    /**
     * 获取给定日期当月的第一天
     *
     * @param localDate
     * @return
     */
    private fun getFirstDayOfMonth(localDate: LocalDate): LocalDate {
        return localDate.with(TemporalAdjusters.firstDayOfMonth())
    }

    /**
     * 获取给定日期当月的第一天
     *
     * @param date
     * @return
     */
    fun getFirstDayOfMonth(date: Date): Date {
        return localDate2Date(getFirstDayOfMonth(date2localDate(date)))
    }

    /**
     * 获取当月的第一天
     *
     * @return
     */
    fun getFirstDayOfMonth(): Date {
        return localDate2Date(getFirstDayOfMonth(LocalDate.now()))
    }

    /**
     * 获取当月的最后一天
     *
     * @return
     */
    fun getLastDayOfMonth(): Date {
        return localDate2Date(getLastDayOfMonth(LocalDate.now()))
    }


    /**
     * 获取给定日期当月的最后一天,如果是当月则返回当日时间
     *
     * @param localDate
     * @return
     */
    private fun getLastOrCurrentDayOfDate(localDate: LocalDate): LocalDate {
        val now = LocalDate.now()
        return if (now.year == localDate.year && now.month == localDate.month) {
            now
        } else getLastDayOfMonth(localDate)
    }

    /**
     * 获取给定日期当月的最后一天,如果是当月则返回当日时间
     *
     * @param date
     * @return
     */
    fun getLastOrCurrentDayOfDate(date: Date): Date {
        return localDate2Date(getLastOrCurrentDayOfDate(date2localDate(date)))
    }

    /**
     * 获取给定日期前n月的最后一天
     *
     * @param localDate
     * @param months
     * @return
     */
    private fun getLastDayOfLocalDateMinusMonths(localDate: LocalDate, months: Long): LocalDate {
        return getLastDayOfMonth(localDate).minusMonths(months)
    }

    /**
     * 获取给定日期前n月的最后一天g
     *
     * @param date
     * @param months
     * @return
     */
    fun getLastDayOfLocalDateMinusMonths(date: Date, months: Long): Date {
        return localDate2Date(getLastDayOfLocalDateMinusMonths(date2localDate(date), months))
    }

    /**
     * 获取给定日期后n月的最后一天
     *
     * @param localDate
     * @param months
     * @return
     */
    private fun getLastDayOfLocalDatePlusMonths(localDate: LocalDate, months: Long): LocalDate {
        return getLastDayOfMonth(localDate).plusMonths(months)
    }

    /**
     * 获取给定日期后n月的最后一天
     *
     * @param date
     * @param months
     * @return
     */
    fun getLastDayOfLocalDatePlusMonths(date: Date, months: Long): Date {
        return localDate2Date(getLastDayOfLocalDatePlusMonths(date2localDate(date), months))
    }

    /**
     * 获取给定日期的最后一天的前n天
     *
     * @param localDate
     * @param days
     * @return
     */
    private fun getLastDayOfLocalDateMinusDays(localDate: LocalDate, days: Long): LocalDate {
        return getLastDayOfMonth(localDate).minusDays(days)
    }

    /**
     * 获取给定日期的最后一天的前n天
     *
     * @param date
     * @param days
     * @return
     */
    fun getLastDayOfLocalDateMinusDays(date: Date, days: Long): Date {
        return localDate2Date(getLastDayOfLocalDateMinusDays(date2localDate(date), days))
    }

    /**
     * 获取给定日期后n月的最后一天
     *
     * @param localDate
     * @param days
     * @return
     */
    private fun getLastDayOfLocalDatePlusDays(localDate: LocalDate, days: Long): LocalDate {
        return getLastDayOfMonth(localDate).plusDays(days)
    }

    /**
     * 获取给定日期后n月的最后一天
     *
     * @param date
     * @param days
     * @return
     */
    fun getLastDayOfLocalDatePlusDays(date: Date, days: Long): Date {
        return localDate2Date(getLastDayOfLocalDatePlusDays(date2localDate(date), days))
    }


    /**
     * 得到当前日期n小时后的日期
     *
     * @param date
     * @param hours
     * @return
     */
    fun plusHours(date: Date, hours: Long): Date {
        return localDateTime2Date(date2localDateTime(date).plusHours(hours))
    }


    /**
     * 得到当前日期n小时前的日期
     *
     * @param date
     * @param hours
     * @return
     */
    fun minusHours(date: Date, hours: Long): Date {
        return plusHours(date, -hours)
    }

    /**
     * 得到当前日期n天后的日期
     *
     * @param date
     * @param days
     * @return
     */
    fun plusDays(date: Date, days: Long): Date {
        return localDateTime2Date(date2localDateTime(date).plusDays(days))
    }

    /**
     * 得到当前日期n天前的日期
     *
     * @param date
     * @param days
     * @return
     */
    fun minusDays(date: Date, days: Long): Date {
        return plusDays(date, -days)
    }

    /**
     * 得到当前日期n月后的日期
     *
     * @param date
     * @param months
     * @return
     */
    fun plusMonths(date: Date, months: Long): Date {
        return localDateTime2Date(date2localDateTime(date).plusMonths(months))
    }

    /**
     * 得到当前日期n月前的日期
     *
     * @param date
     * @param months
     * @return
     */
    fun minusMonths(date: Date, months: Long): Date {
        return plusMonths(date, -months)
    }


    /**
     * 得到传入时间为周几
     *
     * @param localDate
     * @return
     */
    private fun getDayOfWeek(localDate: LocalDate): Int {
        return localDate.dayOfWeek.value
    }

    /**
     * 得到传入时间为周几
     *
     * @param date
     * @return
     */
    fun getDayOfWeek(date: Date): Int {
        return getDayOfWeek(date2localDate(date))
    }


    /**
     * 得到现在为周几
     *
     * @return
     */
    fun getDayOfWeek(): Int {
        return getDayOfWeek(LocalDate.now())
    }

    /**
     * 判断是否为默认时间1970-01-01 00:00:00
     *
     * @param date
     * @return
     */
    fun isDateEqualDefaultTime(date: Date): Boolean {
        return secondsBetweenStartAndEnd(date, ofDefault()) == 0L
    }

    /**
     * 得到两个日期的相差的秒数
     *
     * @param begin
     * @param end
     * @return
     */
    private fun secondsBetweenStartAndEnd(begin: LocalDateTime, end: LocalDateTime): Long {
        return Duration.between(begin, end).seconds
    }

    /**
     * 得到两个日期的相差的秒数
     *
     * @param begin
     * @param end
     * @return
     */
    fun secondsBetweenStartAndEnd(begin: Date, end: Date): Long {
        return secondsBetweenStartAndEnd(date2localDateTime(begin), date2localDateTime(end))
    }

    /**
     * 得到两个日期的相差的天数
     *
     * @param begin
     * @param end
     * @return
     */
    private fun daysBetweenStartAndEnd(begin: LocalDate, end: LocalDate): Int {
        return Period.between(begin, end).days
    }

    /**
     * 得到两个日期的相差的月数
     *
     * @param begin
     * @param end
     * @return
     */
    private fun monthsBetweenStartAndEnd(begin: LocalDate, end: LocalDate): Int {
        return Period.between(begin, end).months
    }

    /**
     * 得到两个日期的相差的年数
     *
     * @param begin
     * @param end
     * @return
     */
    private fun yearsBetweenStartAndEnd(begin: LocalDate, end: LocalDate): Int {
        return Period.between(begin, end).years
    }


    /**
     * 得到两个日期的相差的天数
     *
     * @param begin
     * @param end
     * @return
     */
    fun daysBetweenStartAndEnd(begin: Date, end: Date): Int {
        return daysBetweenStartAndEnd(date2localDate(begin), date2localDate(end))
    }

    /**
     * 得到两个日期的相差的月数
     *
     * @param begin
     * @param end
     * @return
     */
    fun monthsBetweenStartAndEnd(begin: Date, end: Date): Int {
        return monthsBetweenStartAndEnd(date2localDate(begin), date2localDate(end))
    }

    /**
     * 得到两个日期的相差的年数
     *
     * @param begin
     * @param end
     * @return
     */
    fun yearsBetweenStartAndEnd(begin: Date, end: Date): Int {
        return yearsBetweenStartAndEnd(date2localDate(begin), date2localDate(end))
    }

    /**
     * 格式化为对应pattern
     *
     * @param localDate
     * @param pattern
     * @return
     */
    private fun formatWithPattern(localDate: LocalDate, pattern: String): String {
        return DateTimeFormatter.ofPattern(pattern).format(localDate)
    }

    /**
     * 格式化为对应pattern
     *
     * @param date
     * @param pattern
     * @return
     */
    fun formatWithPattern(date: Date, pattern: String): String {
        return formatWithPattern(date2localDate(date), pattern)
    }

    /**
     * 格式化为yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    fun formatWithDateTimePattern(date: Date): String {
        return formatWithPattern(date, DATE_TIME_PATTERN)
    }

    /**
     * 格式化为yyyy-MM-dd
     *
     * @param date
     * @return
     */
    fun formatWithDatePattern(date: Date): String {
        return formatWithPattern(date, DATE_PATTERN)
    }

    /**
     * 格式化为yyyyMMdd
     *
     * @param date
     * @return
     */
    fun formatWithDateNoseparatorPattern(date: Date): String {
        return formatWithPattern(date, DATE_WITHOUT_SEPARATOR_PATTERN)
    }


    /*
    public static LocalDateTime parseWithPattern(String date, String pattern){
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }*/

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime
     * @return
     */
    private fun localDateTime2Date(localDateTime: LocalDateTime): Date {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return
     */
    private fun date2localDateTime(date: Date): LocalDateTime {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
    }

    /**
     * LocalDate转 Date
     *
     * @param localDate
     * @return
     */
    private fun localDate2Date(localDate: LocalDate): Date {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
    }

    /**
     * Date 转 LocalDate
     *
     * @param date
     * @return
     */
    private fun date2localDate(date: Date): LocalDate {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }
}

fun main(args: Array<String>) {

}