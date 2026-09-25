import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class Solution {
    // TreeSet handles deduplication and automatic lexicographical sorting
    private TreeSet<String> resultSet = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(resultSet);
    }

    private void dfs(String exp) {
        // Find the first innermost closing brace
        int closingBraceIdx = exp.indexOf('}');
        
        // Base Case: No braces left, it's a completely expanded raw string
        if (closingBraceIdx == -1) {
            resultSet.add(exp);
            return;
        }

        // Find the corresponding opening brace closest to our closing brace
        int openingBraceIdx = exp.lastIndexOf('{', closingBraceIdx);

        // Split the expression into: left prefix + {inner_options} + right suffix
        String prefix = exp.substring(0, openingBraceIdx);
        String suffix = exp.substring(closingBraceIdx + 1);
        String innerOptions = exp.substring(openingBraceIdx + 1, closingBraceIdx);

        // Split inner options by comma and process each option recursively
        String[] options = innerOptions.split(",");
        for (String option : options) {
            dfs(prefix + option + suffix);
        }
    }
}
