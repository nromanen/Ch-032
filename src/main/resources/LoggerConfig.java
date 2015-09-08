public class LoggerConfig {

	private static Properties logProperties = new Properties();
	private static String logFile;
	
	public Logger(String logfile) {
		this.logFile = logfile;
	}
	
}
