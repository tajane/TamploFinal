# Root logger option
log4j.rootLogger=DEBUG, file

# Direct log messages to a log file
# configuration to print into file
log4j.appender.file=org.apache.log4j.RollingFileAppender
log4j.appender.file.MaxFileSize=1MB
log4j.appender.file.MaxBackupIndex=10
# Define the layout for file appender
log4j.appender.file.layout=org.apache.log4j.PatternLayout
#log4j.appender.file.layout.ConversionPattern=[%t] %-5p %c %x - %m%n
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
# Set the name of the file
log4j.appender.file.File=C:\\log\\logging.log
# Set the append to false, overwrite
log4j.appender.file.Append=false


<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-simple</artifactId>
  <version>1.6.2</version>
</dependency>
