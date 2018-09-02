package ss.parser.scheduler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Duration;

@Getter
@Setter
public abstract class AdConfigImpl implements AdConfig {
    private Duration rate;
    private URL url;
    private String regex;
    private String replace;
    private Expression expression;

    @Component
    @ConfigurationPropertiesBinding
    static class ExpressionConverter implements Converter<Object, Expression> {
        @Override
        public Expression convert(Object source) {
            return new SpelExpressionParser().parseExpression(source.toString());
        }
    }
}