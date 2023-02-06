#Declare loggers
status = error
name= Log4j2PropertiesConfig
appenders=a_console, a_rolling
rootLogger.level=info
rootLogger.appenderRefs = ar_console,ar_rolling
rootLogger.appenderRef.ar_console.ref = StdoutAppender
rootLogger.appenderRef.ar_rolling.ref= RollingAppender

#Console Logger
appender.a_console.type = Console
appender.a_console.name = StdoutAppender
appender.a_console.layout.type = PatternLayout
appender.a_console.layout.pattern = [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n

#Rolling Logger
appender.a_rolling.type = RollingFile
appender.a_rolling.name = RollingAppender
appender.a_rolling.layout.pattern = [%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n
appender.a_rolling.fileName=log4j2/log4j2-test-automation.log
appender.a_rolling.filePattern=log4j2-sample-%d{yyyy-MM-dd}.log
appender.a_rolling.layout.type = PatternLayout
appender.a_rolling.policies.type = Policies
appender.a_rolling.policies.time.type = TimeBasedTriggeringPolicy
appender.a_rolling.policies.time.interval = 1

# To change log file every day
appender.a_rolling.policies.time.modulate = true
# To change log file after 10MB size
appender.a_rolling.policies.size.type = SizeBasedTriggeringPolicy
appender.a_rolling.policies.size.size=10MB
appender.a_rolling.strategy.type = DefaultRolloverStrategy
appender.a_rolling.strategy.max = 20



##########################

package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    //Initialize Log4j instance
    private static final Logger Log =  LogManager.getLogger(Log.class);

    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}
