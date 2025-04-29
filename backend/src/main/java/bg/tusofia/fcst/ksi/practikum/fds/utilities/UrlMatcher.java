package bg.tusofia.fcst.ksi.practikum.fds.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlMatcher {
    @Data
    @AllArgsConstructor
    public static class UrlComponent {
        String idName;
        Long idValue;
    }

    private static final Pattern PATH_VARIABLE_PATTERN = Pattern.compile("\\{([^/}]+)}");
    
    public static List<UrlComponent> extractPathVariables(String urlPattern, String actualUrl) {
        List<UrlComponent> extractedVariables = new ArrayList<>();

        // Split both strings by "/" and compare structure
        String[] patternParts = urlPattern.split("/");
        String[] urlParts = actualUrl.split("/");

        if (patternParts.length > urlParts.length) {
            throw new IllegalArgumentException("URL pattern must define at most as many segments as there are present in the URL");
        }

        for (int i = 0; i < patternParts.length; i++) {
            Matcher matcher = PATH_VARIABLE_PATTERN.matcher(patternParts[i]);
            if (matcher.matches()) {
                String variableName = matcher.group(1);
                String valueStr = urlParts[i];
                try {
                    Long value = Long.parseLong(valueStr);
                    extractedVariables.add(new UrlComponent(variableName, value));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Path variable '" + variableName + "' must be a number.");
                }
            } else if (!patternParts[i].equals(urlParts[i])) {
                throw new IllegalArgumentException("Static URL segment mismatch at position " + i + ": expected '" +
                        patternParts[i] + "', got '" + urlParts[i] + "'");
            }
        }

        return extractedVariables;
    }
}
