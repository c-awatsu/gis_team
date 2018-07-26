package answer.repository;

import com.google.inject.Singleton;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.val;
import org.sql2o.Sql2o;
import org.sql2o.converters.Converter;
import org.sql2o.quirks.PostgresQuirks;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Objects;

/**
 * {@link IDBCP}の実装.シングルトンオブジェクト.
 *
 * @author yamakawa
 */
@Singleton
public class DBCP implements IDBCP {

    private final URL url;
    private Sql2o sql2o;
    private HikariDataSource ds;
    private boolean unInitialized;

    public DBCP() {
        url = getClass().getClassLoader().getResource("hikari.properties");
        unInitialized = true;
    }

    private void init() {
        val map = new HashMap<Class, Converter>();
        map.put(LocalDateTime.class, new J8LocalDateTimeConverter());
        map.put(LocalDate.class, new J8LocalDateConverter());
        map.put(LocalTime.class, new J8LocalTimeConverter());

        ds = new HikariDataSource(new HikariConfig(url.getFile()));
        sql2o = new Sql2o(ds, new PostgresQuirks(map));

        unInitialized = false;
    }

    @Override
    public Sql2o getSql2o() {
        if (unInitialized) {
            init();
        }
        return sql2o;
    }

    class J8LocalDateTimeConverter implements Converter<LocalDateTime> {

        @Override
        public LocalDateTime convert(Object ts) {
            if (Objects.nonNull(ts)) {
                return ((Timestamp) ts).toLocalDateTime();
            }
            return null;
        }

        @Override
        public Object toDatabaseParam(LocalDateTime ldt) {
            return Timestamp.valueOf(ldt);
        }
    }

    class J8LocalDateConverter implements Converter<LocalDate> {

        @Override
        public LocalDate convert(Object date) {
            if (Objects.nonNull(date)) {
                return ((Date) date).toLocalDate();
            }
            return null;
        }

        @Override
        public Object toDatabaseParam(LocalDate ld) {
            return Date.valueOf(ld);
        }
    }

    class J8LocalTimeConverter implements Converter<LocalTime> {

        @Override
        public LocalTime convert(Object time) {
            if (Objects.nonNull(time)) {
                return ((Time) time).toLocalTime();
            }
            return null;
        }

        @Override
        public Object toDatabaseParam(LocalTime lt) {
            return Time.valueOf(lt);
        }
    }

}