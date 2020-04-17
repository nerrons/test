package cs3211.project;

import java.time.Duration;
import java.util.regex.Pattern;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

public class TimeConverter implements IStringConverter<Duration> {
    @Override
    public Duration convert(String value) throws ParameterException {
        // Duration.parse expects a string in the format "PnDTnHnMn.nS"
        if (!Pattern.matches("(\\d+[HhMm])|(\\d+(\\.\\d+)?[Ss])", value)) {
            throw new ParameterException("Parameter time must be in the format nHnMn.nS");
        }
        return Duration.parse("PT" + value);
    }
}
